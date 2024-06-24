package com.example.myvideo.ui.University.Courses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myvideo.R;
import com.example.myvideo.adapters.CoursesRecyclerAdapter;
import com.example.myvideo.databinding.FragmentDegreeCoursesBinding;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.ui.myHome.MyCourses.Courseviewer.CourseBaseViewerFragment;
import com.example.myvideo.utils.SharedModel;

import java.util.ArrayList;


public class DegreeCoursesFragment extends Fragment {

    CoursesRecyclerAdapter adapter = new CoursesRecyclerAdapter();
    FragmentDegreeCoursesBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_degree_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentDegreeCoursesBinding.bind(view);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void show(){
        adapter.setList((ArrayList<CourseModel>) SharedModel.getSelected_term().getCourses());
        binding.recycler.setAdapter(adapter);

        adapter.setOnItemClick(new CoursesRecyclerAdapter.OnItemClick() {
            @Override
            public void OnClick(CourseModel course) {
                SharedModel.setSelected_course(course);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.home_frame , new CourseBaseViewerFragment(),"bc").addToBackStack("bc").commit();
            }
        });
    }
}