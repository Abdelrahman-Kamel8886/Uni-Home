package com.example.myvideo.ui.University.base;

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
import com.example.myvideo.databinding.FragmentUniversityBaseBinding;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.ui.Explore.Course.CourseFragment;
import com.example.myvideo.ui.RoadMap.RoadmapBaseFragment;
import com.example.myvideo.ui.University.AllDegree.AllDegreesFragment;
import com.example.myvideo.ui.University.MyUniversity.MyUniversityFragment;
import com.example.myvideo.ui.chat.ChatFragment;
import com.example.myvideo.ui.myHome.MyBooks.MyBooksBaseFragment;
import com.example.myvideo.ui.myHome.MyCourses.Courseviewer.CourseBaseViewerFragment;
import com.example.myvideo.ui.profile.Account.FacultyInfo.FacultyInfoFragment;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class UniversityBaseFragment extends Fragment {

    FragmentUniversityBaseBinding binding;
    UniversityBaseViewModel viewModel;
    BottomNavigationView nav;
    CoursesRecyclerAdapter adapter = new CoursesRecyclerAdapter();
    CoursesRecyclerAdapter adapter2 = new CoursesRecyclerAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_university_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentUniversityBaseBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(UniversityBaseViewModel.class);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.VISIBLE);

        binding.txt11.setVisibility(View.GONE);
        binding.txt21.setVisibility(View.GONE);

        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void onClicks(){

        binding.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new ChatFragment(),"chat")
                        .addToBackStack("chat").commit();
            }
        });

        binding.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new FacultyInfoFragment(),"info")
                        .addToBackStack("info").commit();
            }
        });

    }

    private void getData(){
        viewModel.getinfo();
        viewModel.Courses1.observe(getViewLifecycleOwner(), new Observer<ArrayList<CourseModel>>() {
            @Override
            public void onChanged(ArrayList<CourseModel> courseModels) {

                adapter.setList(courseModels);
                if (courseModels.size() > 0){
                    binding.recycler1.setAdapter(adapter);
                    binding.txt11.setVisibility(View.VISIBLE);
                }

                adapter.setOnItemClick(new CoursesRecyclerAdapter.OnItemClick() {
                    @Override
                    public void OnClick(CourseModel course) {
                        SharedModel.setSelected_course(course);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new CourseBaseViewerFragment(),"c").addToBackStack("c").commit();
                    }
                });

            }
        });

        viewModel.Courses2.observe(getViewLifecycleOwner(), new Observer<ArrayList<CourseModel>>() {
            @Override
            public void onChanged(ArrayList<CourseModel> courseModels) {
                adapter2.setList(courseModels);

                if (courseModels.size() > 0){
                    binding.recycler2.setAdapter(adapter2);
                    binding.txt21.setVisibility(View.VISIBLE);
                }

                adapter2.setOnItemClick(new CoursesRecyclerAdapter.OnItemClick() {
                    @Override
                    public void OnClick(CourseModel course) {
                        SharedModel.setSelected_course(course);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new  CourseBaseViewerFragment(),"c").addToBackStack("c").commit();
                    }
                });
            }
        });

        onClicks();

    }

}