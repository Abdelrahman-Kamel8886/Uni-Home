package com.example.myvideo.ui.Explore;

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
import com.example.myvideo.adapters.ArticlesRecyclerAdapter;
import com.example.myvideo.adapters.CatsRecyclerAdapter;
import com.example.myvideo.databinding.FragmentCategBinding;
import com.example.myvideo.models.ArticleModel;
import com.example.myvideo.ui.Explore.Article.ArticleFragment;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class CategFragment extends Fragment {

    FragmentCategBinding binding;
    BottomNavigationView nav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_categ, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentCategBinding.bind(view);
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


    }


    private void getCoursesCats(){
        binding.books.setBackgroundColor(getResources().getColor(R.color.white));
        binding.Courses.setBackgroundColor(getResources().getColor(R.color.color1));
        binding.articles.setBackgroundColor(getResources().getColor(R.color.white));

        binding.books.setTextColor(getResources().getColor(R.color.black));
        binding.articles.setTextColor(getResources().getColor(R.color.black));
        binding.Courses.setTextColor(getResources().getColor(R.color.white));

        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame3 , new CoursesCatsFragment() ,"ccat")
                .addToBackStack("ccat").commit();
    }

    private void getBooksCats(){

        binding.books.setBackgroundColor(getResources().getColor(R.color.color1));
        binding.Courses.setBackgroundColor(getResources().getColor(R.color.white));
        binding.articles.setBackgroundColor(getResources().getColor(R.color.white));

        binding.books.setTextColor(getResources().getColor(R.color.white));
        binding.articles.setTextColor(getResources().getColor(R.color.black));
        binding.Courses.setTextColor(getResources().getColor(R.color.black));

        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame3 , new BooksCatsFragment() ,"bcat")
                .addToBackStack("bcat").commit();


    }

}