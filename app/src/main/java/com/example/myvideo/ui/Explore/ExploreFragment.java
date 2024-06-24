package com.example.myvideo.ui.Explore;

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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myvideo.R;
import com.example.myvideo.adapters.BooksRecyclerAdapter;
import com.example.myvideo.adapters.CoursesRecyclerAdapter;
import com.example.myvideo.adapters.CoursesRecyclerAdapter2;
import com.example.myvideo.databinding.FragmentExploreBinding;
import com.example.myvideo.models.BookModel;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.ui.Explore.Book.BookFragment;
import com.example.myvideo.ui.Explore.Course.CourseFragment;
import com.example.myvideo.ui.myHome.MyCourses.MyCoursesViewModel;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class ExploreFragment extends Fragment {

    FragmentExploreBinding binding;
    BottomNavigationView nav;
    ExploreViewModel viewModel;
    BooksRecyclerAdapter booksadapter = new BooksRecyclerAdapter();
    CoursesRecyclerAdapter2 coursesadapter = new CoursesRecyclerAdapter2();

    MyCoursesViewModel myCoursesViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentExploreBinding.bind(view);

        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.VISIBLE);


        viewModel = new ViewModelProvider(this).get(ExploreViewModel.class);
        myCoursesViewModel = new ViewModelProvider(this).get(MyCoursesViewModel.class);

        binding.txt.setText(SharedModel.getExplore_cat());

        if (SharedModel.getExplore_item().equals("book")){
            getBoooks();
        }
        else if (SharedModel.getExplore_item().equals("course")){
            getCourses();
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        viewModel = null;
    }



    private void getBoooks(){

        viewModel.getBooks();
        viewModel.Books.observe(getViewLifecycleOwner(), new Observer<ArrayList<BookModel>>() {
            @Override
            public void onChanged(ArrayList<BookModel> bookModels) {

                booksadapter.setList(bookModels);
                binding.recycler.setAdapter(booksadapter);

            }
        });

        viewModel.empty.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.empty.setVisibility(View.VISIBLE);
            }
        });

        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                booksadapter.getFilter().filter(newText);
                return false;
            }
        });

        booksadapter.setOnItemClick(new BooksRecyclerAdapter.OnItemClick() {
            @Override
            public void OnClick(BookModel book) {

                SharedModel.setSelected_book(book);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new BookFragment(),"bb")
                        .addToBackStack("bb").commit();
            }
        });
    }
    private void getCourses(){

        GridLayoutManager layoutManager = (GridLayoutManager) binding.recycler.getLayoutManager();
        layoutManager.setSpanCount(1);
        binding.recycler.setLayoutManager(layoutManager);


        viewModel.getCourses();

        viewModel.Courses.observe(getViewLifecycleOwner(), new Observer<ArrayList<CourseModel>>() {
            @Override
            public void onChanged(ArrayList<CourseModel> courseModels) {
                coursesadapter.setList(courseModels);
                binding.recycler.setAdapter(coursesadapter);
            }
        });

        viewModel.empty.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.empty.setVisibility(View.VISIBLE);
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