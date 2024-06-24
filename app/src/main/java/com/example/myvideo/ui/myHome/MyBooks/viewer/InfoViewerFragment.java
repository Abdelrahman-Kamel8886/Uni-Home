package com.example.myvideo.ui.myHome.MyBooks.viewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentInfoViewerBinding;
import com.example.myvideo.utils.SharedModel;


public class InfoViewerFragment extends Fragment {

    FragmentInfoViewerBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_viewer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentInfoViewerBinding.bind(view);
        binding.txt.setText(SharedModel.getSelected_book().getBook_title());
        binding.author.setText(SharedModel.getSelected_book().getBook_author());
        binding.about.setText(SharedModel.getSelected_book().getBook_overview());
        Glide.with(getContext())
                .load(SharedModel.getSelected_book().getImglink())
                .into(binding.img);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}