package com.example.myvideo.ui.University.MyUniversity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myvideo.R;
import com.example.myvideo.adapters.CoursesRecyclerAdapter;
import com.example.myvideo.databinding.FragmentMyUniversityBinding;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.ui.chat.ChatFragment;
import com.example.myvideo.ui.myHome.MyCourses.Courseviewer.CourseBaseViewerFragment;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MyUniversityFragment extends Fragment {

    FragmentMyUniversityBinding binding;
    BottomNavigationView nav;
    MyUniversityViewModel viewModel;
    CoursesRecyclerAdapter adapter = new CoursesRecyclerAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_university, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMyUniversityBinding.bind(view);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);
        viewModel = new ViewModelProvider(this).get(MyUniversityViewModel.class);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        getData();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getData(){
        viewModel.getinfo();
        viewModel.Courses.observe(getViewLifecycleOwner(), new Observer<ArrayList<CourseModel>>() {
            @Override
            public void onChanged(ArrayList<CourseModel> courseModels) {
                adapter.setList(courseModels);
                binding.recycler.setAdapter(adapter);

                adapter.setOnItemClick(new CoursesRecyclerAdapter.OnItemClick() {
                    @Override
                    public void OnClick(CourseModel course) {
                        SharedModel.setSelected_course(course);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new CourseBaseViewerFragment(),"bc").addToBackStack("bc").commit();
                    }
                });

            }
        });

        binding.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new ChatFragment(),"bc").addToBackStack("bc").commit();

            }
        });
    }
}