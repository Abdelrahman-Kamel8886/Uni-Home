package com.example.myvideo.ui.myHome.MyCourses;

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
import com.example.myvideo.databinding.FragmentMyHomeCoursesBinding;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.ui.Explore.Course.CourseFragment;
import com.example.myvideo.ui.myHome.MyCourses.Courseviewer.CourseBaseViewerFragment;
import com.example.myvideo.utils.SharedModel;

import java.util.ArrayList;


public class MyHomeCoursesFragment extends Fragment {


    FragmentMyHomeCoursesBinding binding;
    CoursesRecyclerAdapter adapter = new CoursesRecyclerAdapter();
    CoursesRecyclerAdapter adapter2 = new CoursesRecyclerAdapter();
    MyCoursesViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_home_courses, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMyHomeCoursesBinding.bind(view);

        binding.txt1.setVisibility(View.GONE);
        binding.txt2.setVisibility(View.GONE);
        binding.txt3.setVisibility(View.GONE);
        binding.txt4.setVisibility(View.GONE);

        viewModel = new ViewModelProvider(this).get(MyCoursesViewModel.class);
        getCourses();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getCourses(){

        viewModel.getCourses();

        viewModel.Courses.observe(getViewLifecycleOwner(), new Observer<ArrayList<CourseModel>>() {
            @Override
            public void onChanged(ArrayList<CourseModel> courseModels) {

                ArrayList<CourseModel> list1 = new ArrayList<>();

                if (courseModels.size()>=4){
                    list1.addAll(courseModels.subList(0,4));
                }
                else {
                    list1.addAll(courseModels);
                }

                adapter.setList(list1);

                if (list1.size() > 0){
                    binding.recycler1.setAdapter(adapter);
                    binding.txt1.setVisibility(View.VISIBLE);
                    binding.txt3.setVisibility(View.VISIBLE);
                }
                else {
                    binding.recycler1.setVisibility(View.GONE);
                }


                getCourses2();
            }
        });
    }

    private void getCourses2(){

        viewModel.getSuggestedCourses();

        viewModel.SuggestedCourseModels.observe(getViewLifecycleOwner(), new Observer<ArrayList<CourseModel>>() {
            @Override
            public void onChanged(ArrayList<CourseModel> courseModels) {

                ArrayList<CourseModel> list2 = new ArrayList<>();

                if (courseModels.size()>=4){
                    list2.addAll(courseModels.subList(0,4));
                }
                else {
                    list2.addAll(courseModels);
                }




                if (list2.size()>0){
                    adapter2.setList(list2);
                    binding.recycler2.setAdapter(adapter2);
                    binding.txt2.setVisibility(View.VISIBLE);
                    binding.txt4.setVisibility(View.VISIBLE);
                }
                else {
                    binding.recycler2.setVisibility(View.GONE);
                }
                onClicks();
            }
        });
    }

    private void onClicks(){

        adapter.setOnItemClick(new CoursesRecyclerAdapter.OnItemClick() {
            @Override
            public void OnClick(CourseModel course) {
                SharedModel.setSelected_course(course);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new CourseBaseViewerFragment(),"bc").addToBackStack("bc").commit();
            }
        });

        adapter2.setOnItemClick(new CoursesRecyclerAdapter.OnItemClick() {
            @Override
            public void OnClick(CourseModel course) {
                SharedModel.setSelected_course(course);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new CourseFragment(),"c").addToBackStack("c").commit();

            }
        });

        binding.txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MyCoursesBaseFragment(),"mmc").addToBackStack("mmc").commit();
            }
        });

        binding.txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new SuggestsCoursesFragment(),"msc").addToBackStack("msc").commit();


            }
        });

    }




}