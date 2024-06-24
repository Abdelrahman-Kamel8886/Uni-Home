package com.example.myvideo.ui.myHome.MyCourses.Courseviewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myvideo.R;
import com.example.myvideo.adapters.PagerAdabter;
import com.example.myvideo.databinding.FragmentCourseBaseViewerBinding;
import com.example.myvideo.models.PagerModelClass;
import com.example.myvideo.utils.SharedModel;

import java.util.ArrayList;


public class CourseBaseViewerFragment extends Fragment {

    FragmentCourseBaseViewerBinding binding;
    PagerAdabter adapter;

    ArrayList<PagerModelClass> model = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course_base_viewer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentCourseBaseViewerBinding.bind(view);


        adapter = new PagerAdabter(getChildFragmentManager());

        model.clear();

        model.add(new PagerModelClass(new CourseinfoFragment(), "Course Details "));
        model.add(new PagerModelClass(new VideosFragment(), "Videos"));
        model.add(new PagerModelClass(new PdfsFragment(), "Pdfs"));



        adapter.setData(model);
        binding.Pager.setAdapter(adapter);
        binding.tablayout.setupWithViewPager(binding.Pager);
        binding.toolbar.setTitle(SharedModel.getSelected_course().getTitle());
        binding.toolbar.setVisibility(View.GONE);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}