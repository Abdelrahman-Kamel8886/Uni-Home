package com.example.myvideo.ui.Explore;

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

import com.example.myvideo.R;
import com.example.myvideo.adapters.CatsRecyclerAdapter;
import com.example.myvideo.databinding.FragmentCoursesCatsBinding;
import com.example.myvideo.ui.myHome.MyCourses.MyHomeCoursesFragment;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class CoursesCatsFragment extends Fragment {

    FragmentCoursesCatsBinding binding;
    BottomNavigationView nav;
    ExploreViewModel viewModel;

    CatsRecyclerAdapter adapter = new CatsRecyclerAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_courses_cats, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentCoursesCatsBinding.bind(view);

        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.VISIBLE);
        viewModel = new ViewModelProvider(this).get(ExploreViewModel.class);

        getCoursesCats();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getCoursesCats(){

        viewModel.getCourses_Cats();

        viewModel.catslist.observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                adapter.setList(strings);
                binding.recycler.setAdapter(adapter);

                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.courses_cat_frame , new MyHomeCoursesFragment(),"mhbc")
                        .commit();

                adapter.setOnItemClick(new CatsRecyclerAdapter.OnItemClick() {
                    @Override
                    public void OnClick(String category) {
                        SharedModel.setExplore_item("course");
                        SharedModel.setExplore_cat(category);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.courses_cat_frame , new ExploreFragment() ,"e")
                                .addToBackStack("e").commit();
                    }
                });
            }
        });

    }
}