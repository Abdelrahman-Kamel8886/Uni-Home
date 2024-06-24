package com.example.myvideo.ui.myHome.MyCourses.Courseviewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentCourseinfoBinding;
import com.example.myvideo.utils.SharedModel;


public class CourseinfoFragment extends Fragment {

    FragmentCourseinfoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courseinfo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentCourseinfoBinding.bind(view);

        binding.txt3.setText(SharedModel.getSelected_course().getTitle());
        binding.author.setText(SharedModel.getSelected_course().getInstructor());
        binding.about.setText(SharedModel.getSelected_course().getIntro());
        binding.lang.setText(SharedModel.getSelected_course().getLanguage());

        Glide.with(getContext())
                .load(SharedModel.getSelected_course().getImage())
                .into(binding.img);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}