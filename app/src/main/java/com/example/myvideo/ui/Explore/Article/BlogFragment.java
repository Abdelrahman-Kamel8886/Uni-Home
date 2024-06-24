package com.example.myvideo.ui.Explore.Article;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myvideo.R;
import com.example.myvideo.adapters.ArticlesRecyclerAdapter;
import com.example.myvideo.adapters.PagerAdabter;
import com.example.myvideo.adapters.PostsRecyclerAdapter;
import com.example.myvideo.databinding.FragmentBlogBinding;
import com.example.myvideo.models.PagerModelClass;
import com.example.myvideo.ui.Explore.CommunityAtriclesFragment;
import com.example.myvideo.ui.Explore.CommunityPostsFragment;
import com.example.myvideo.ui.Explore.ExploreViewModel;
import com.example.myvideo.ui.profile.Chat.BaseChatFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class BlogFragment extends Fragment {


    FragmentBlogBinding binding;
    BottomNavigationView nav;
    ExploreViewModel viewModel;
    ArticlesRecyclerAdapter articlesRecyclerAdapter = new ArticlesRecyclerAdapter();
    PostsRecyclerAdapter postsRecyclerAdapter = new PostsRecyclerAdapter();




    PagerAdabter adapter;
    ArrayList<PagerModelClass> model = new ArrayList<>();




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentBlogBinding.bind(view);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.VISIBLE);
        viewModel = new ViewModelProvider(this).get(ExploreViewModel.class);

        adapter = new PagerAdabter(getChildFragmentManager());

        model.clear();

        model.add(new PagerModelClass(new CommunityPostsFragment(), "Posts"));
        model.add(new PagerModelClass(new CommunityAtriclesFragment(), "Articles"));



        adapter.setData(model);
        binding.Pager.setAdapter(adapter);
        binding.tablayout.setupWithViewPager(binding.Pager);

        binding.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new BaseChatFragment() ,"chat")
                        .addToBackStack("chat").commit();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}
