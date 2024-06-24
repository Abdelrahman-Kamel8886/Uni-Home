package com.example.myvideo.ui.myHome.MyCourses;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myvideo.R;
import com.example.myvideo.adapters.CoursesRecyclerAdapter;
import com.example.myvideo.adapters.CoursesRecyclerAdapter2;
import com.example.myvideo.databinding.FragmentSuggestsCoursesBinding;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.ui.Explore.Course.CourseFragment;
import com.example.myvideo.ui.Explore.ExploreViewModel;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class SuggestsCoursesFragment extends Fragment {

    FragmentSuggestsCoursesBinding binding;
    MyCoursesViewModel viewModel;
    CoursesRecyclerAdapter2 coursesadapter = new CoursesRecyclerAdapter2();
    BottomNavigationView nav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suggests_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding  = FragmentSuggestsCoursesBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(MyCoursesViewModel.class);

        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        binding.bar.setVisibility(View.VISIBLE);
        getCourses();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getCourses(){
        viewModel.getSuggestedCourses();

        viewModel.SuggestedCourseModels.observe(getViewLifecycleOwner(), new Observer<ArrayList<CourseModel>>() {
            @Override
            public void onChanged(ArrayList<CourseModel> courseModels) {
                coursesadapter.setList(courseModels);
                binding.recycler.setAdapter(coursesadapter);
                binding.bar.setVisibility(View.GONE);
            }
        });



        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                coursesadapter.getFilter().filter(newText);
                return false;
            }
        });

        coursesadapter.setOnItemClick(new CoursesRecyclerAdapter2.OnItemClick() {
            @Override
            public void OnClick(CourseModel course) {
                SharedModel.setSelected_course(course);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new CourseFragment(),"c").addToBackStack("c").commit();
            }
        });
    }
}