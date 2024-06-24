package com.example.myvideo.ui.post;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.myvideo.R;
import com.example.myvideo.adapters.CommentsRecyclerAdapter;
import com.example.myvideo.databinding.FragmentPostBinding;
import com.example.myvideo.models.CommentModel;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.core.utilities.Validation;

import java.util.ArrayList;


public class PostFragment extends Fragment {

    FragmentPostBinding binding;
    PostViewModel viewModel;
    CommentsRecyclerAdapter adapter = new CommentsRecyclerAdapter();
    BottomNavigationView nav;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentPostBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(PostViewModel.class);

        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);
        load();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void load(){

        Glide.with(getContext())
                .load(SharedModel.getImage())
                .into(binding.img2);

        Glide.with(getContext())
                .load(SharedModel.getSelected_post().getImg())
                .into(binding.img);


        if (SharedModel.getSelected_post().getPhoto().isEmpty()){
            binding.card1.setVisibility(View.GONE);
        }
        else {
            Glide.with(getContext())
                    .load(SharedModel.getSelected_post().getPhoto())
                    .into(binding.photo);
        }

        binding.writer.setText(SharedModel.getSelected_post().getWriter());
        binding.overTxt.setText(SharedModel.getSelected_post().getPost());

        viewModel.getComments();
        viewModel.list.observe(getViewLifecycleOwner(), new Observer<ArrayList<CommentModel>>() {
            @Override
            public void onChanged(ArrayList<CommentModel> commentModels) {
               show(commentModels);

            }
        });

        onClicks();
    }

    private void onClicks(){
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validation();
            }
        });



    }

    private void Validation(){
        String comment = binding.commentEdit.getText().toString().trim();

        if (comment.isEmpty()){
            binding.commentEdit.setError("Required");
        }
        else{
            viewModel.sendComment(comment);
            binding.commentEdit.setText("");
        }

    }

    private void show(ArrayList<CommentModel> commentModels){
        adapter.setList(commentModels);
        binding.recycler.setAdapter(adapter);
    }
}