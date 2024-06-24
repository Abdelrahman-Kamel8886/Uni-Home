package com.example.myvideo.ui.myHome.MyCourses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myvideo.R;
import com.example.myvideo.adapters.CoursesRecyclerAdapter;
import com.example.myvideo.adapters.CoursesRecyclerAdapter2;
import com.example.myvideo.databinding.FragmentMyCoursesBaseBinding;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.ui.myHome.MyCourses.Courseviewer.CourseBaseViewerFragment;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MyCoursesBaseFragment extends Fragment {


    FragmentMyCoursesBaseBinding binding;
    CoursesRecyclerAdapter2 adapter = new CoursesRecyclerAdapter2();
    MyCoursesViewModel viewModel;
    BottomNavigationView nav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_courses_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMyCoursesBaseBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(MyCoursesViewModel.class);

        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);

        binding.bar.setVisibility(View.VISIBLE);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        getCourses();
    }


    private void getCourses(){

        viewModel.getCourses();

        viewModel.Courses.observe(getViewLifecycleOwner(), new Observer<ArrayList<CourseModel>>() {
            @Override
            public void onChanged(ArrayList<CourseModel> courseModels) {
                adapter.setList(courseModels);
                binding.recycler.setAdapter(adapter);
                onClicks();
            }
        });
    }

    private void onClicks(){
        binding.bar.setVisibility(View.GONE);

        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        adapter.setOnItemClick(new CoursesRecyclerAdapter2.OnItemClick() {
            @Override
            public void OnClick(CourseModel course) {
                SharedModel.setSelected_course(course);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new CourseBaseViewerFragment(),"bc").addToBackStack("bc").commit();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}