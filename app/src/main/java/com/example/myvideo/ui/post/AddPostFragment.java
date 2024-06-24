package com.example.myvideo.ui.post;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentAddPostBinding;
import com.example.myvideo.models.PostModel;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class AddPostFragment extends Fragment {

    FragmentAddPostBinding binding;
    PostViewModel viewModel;
    Uri uri;
    BottomNavigationView nav;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentAddPostBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(PostViewModel.class);

        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);

        load();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding =null;
    }

    private void load(){
        Glide.with(getContext())
                .load(SharedModel.getImage())
                .into(binding.img);

        binding.writer.setText(SharedModel.getUsername());

        onClicks();
    }

    private void onClicks(){
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        binding.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openfile();
            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                val();
            }
        });
    }

    private void val(){
        String post = binding.commentEdit.getText().toString().trim();
        PostModel newPost = new PostModel();

        newPost.setPost(post);
        newPost.setImg(SharedModel.getImage());
        newPost.setWriter(SharedModel.getUsername());
        newPost.setDate("");
        newPost.setTime("");

        if (post.isEmpty()){
            binding.commentEdit.setError("Required");
        }
        else if (uri==null){
            binding.bar.setVisibility(View.VISIBLE);
            newPost.setPhoto("");
            Sendwithout(newPost);
        }
        else {
            binding.bar.setVisibility(View.VISIBLE);
            viewModel.uploadFile(uri , newPost);
            viewModel.success.observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    binding.bar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    requireActivity().onBackPressed();
                }
            });
        }
    }

    private void openfile(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            uri = data.getData();
            binding.addTxt.setText(SharedModel.getPathFromUri(getContext(),uri));
        }

    }

    private void Sendwithout(PostModel model){
        viewModel.sendWithout(model);
        viewModel.success.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.bar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();
            }
        });
    }

}