package com.example.myvideo.ui.Explore.Course;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.myvideo.R;
import com.example.myvideo.adapters.VideosRecyclerAdapter;
import com.example.myvideo.databinding.FragmentCourseBinding;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class CourseFragment extends Fragment {

    FragmentCourseBinding binding;
    BottomNavigationView nav;

    VideosRecyclerAdapter adapter = new VideosRecyclerAdapter();

    ExploreCourseViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentCourseBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(ExploreCourseViewModel.class);

        binding.addBtn.setVisibility(View.GONE);


        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);

        binding.toolbar.setTitle(SharedModel.getSelected_course().getTitle());
        binding.author.setText(SharedModel.getSelected_course().getInstructor());
        binding.about.setText(SharedModel.getSelected_course().getIntro());
        binding.lang.setText(SharedModel.getSelected_course().getLanguage());

        Glide.with(getContext())
                .load(SharedModel.getSelected_course().getImage())
                .into(binding.img);

        Log.e("TAG", "onViewCreated: " + SharedModel.getSelected_course().getVideos());

        adapter.setList((ArrayList<CourseModel.Videos>) SharedModel.getSelected_course().getVideos());
        binding.recyclerc.setAdapter(adapter);

        getData();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void getData() {

        viewModel.Check(SharedModel.getSelected_course());

        viewModel.added.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                if (integer == 1) {
                    binding.addBtn.setText("This Course has already been added");
                    binding.addBtn.setBackgroundColor(getResources().getColor(R.color.color22));
                    binding.addBtn.setClickable(false);
                    binding.addBtn.setVisibility(View.VISIBLE);
                } else {
                    binding.addBtn.setVisibility(View.VISIBLE);
                    binding.addBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.addBtn.setText("");
                            binding.addBtn.setClickable(false);
                            binding.bar.setVisibility(View.VISIBLE);
                            addCourse();
                        }
                    });
                }
            }
        });
    }
    private void addCourse(){

        viewModel.addCourse(SharedModel.getSelected_course());

        viewModel.done.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.bar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Course added Successfully", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();
            }
        });



    }
}