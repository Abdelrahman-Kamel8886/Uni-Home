package com.example.myvideo.ui.profile.MyPosts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.myvideo.R;
import com.example.myvideo.adapters.PostsRecyclerAdapter;
import com.example.myvideo.databinding.FragmentMyPostsBinding;
import com.example.myvideo.models.PostModel;
import com.example.myvideo.ui.post.AddPostFragment;
import com.example.myvideo.ui.post.PostFragment;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MyPostsFragment extends Fragment {

    FragmentMyPostsBinding binding;
    BottomNavigationView nav;
    MyPostsViewModel viewModel;
    PostsRecyclerAdapter postsRecyclerAdapter = new PostsRecyclerAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMyPostsBinding.bind(view);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);
        viewModel =new ViewModelProvider(this).get(MyPostsViewModel.class);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        Glide.with(getContext())
                .load(SharedModel.getImage())
                .into(binding.img);

        binding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new AddPostFragment(),"addp")
                        .addToBackStack("addp").commit();
            }
        });

        getPosts();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getPosts(){
        viewModel.getPosts();

        viewModel.posts.observe(getViewLifecycleOwner(), new Observer<ArrayList<PostModel>>() {
            @Override
            public void onChanged(ArrayList<PostModel> postModels) {
                postsRecyclerAdapter.setList(postModels);
                binding.recycler.setAdapter(postsRecyclerAdapter);

                postsRecyclerAdapter.setOnItemClick(new PostsRecyclerAdapter.OnItemClick() {
                    @Override
                    public void OnClick(PostModel post) {
                        SharedModel.setSelected_post(post);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new PostFragment(),"p")
                                .addToBackStack("p").commit();
                    }
                });
            }
        });

    }
}