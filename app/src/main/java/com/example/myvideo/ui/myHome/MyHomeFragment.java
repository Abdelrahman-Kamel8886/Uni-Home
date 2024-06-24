package com.example.myvideo.ui.myHome;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentMyHomeBinding;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.models.UniversityModel;
import com.example.myvideo.ui.Explore.BooksCatsFragment;
import com.example.myvideo.ui.Explore.CoursesCatsFragment;
import com.example.myvideo.ui.RoadMap.RoadmapBaseFragment;
import com.example.myvideo.ui.University.MyUniversity.MyUniversityFragment;
import com.example.myvideo.ui.myHome.MyBooks.MyBooksBaseFragment;
import com.example.myvideo.ui.myHome.MyCourses.MyCoursesBaseFragment;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MyHomeFragment extends Fragment {


    FragmentMyHomeBinding binding;
    BottomNavigationView nav;

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMyHomeBinding.bind(view);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.VISIBLE);



        if (SharedModel.getExplore_item() == "book"){
            getBooksCats();
        }
        else{
            getCoursesCats();
        }
        onClicks();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onClicks(){

        binding.Courses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getCoursesCats();
            }
        });

        binding.books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBooksCats();
            }
        });

        binding.road.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new RoadmapBaseFragment(),"road")
                        .addToBackStack("road").commit();
            }
        });


    }


    private void getCoursesCats(){

        binding.books.setBackgroundColor(getResources().getColor(R.color.colornew2));
        binding.Courses.setBackgroundColor(getResources().getColor(R.color.purple));

        binding.books.setTextColor(getResources().getColor(R.color.black));
        binding.Courses.setTextColor(getResources().getColor(R.color.white));

        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myhome_frame , new CoursesCatsFragment() ,"ccat")
                .commit();
    }

    private void getBooksCats(){

        binding.books.setBackgroundColor(getResources().getColor(R.color.purple));
        binding.Courses.setBackgroundColor(getResources().getColor(R.color.colornew2));

        binding.books.setTextColor(getResources().getColor(R.color.white));
        binding.Courses.setTextColor(getResources().getColor(R.color.black));

        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myhome_frame , new BooksCatsFragment() ,"bcat")
                .commit();


    }





}