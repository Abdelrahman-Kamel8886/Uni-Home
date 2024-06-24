package com.example.myvideo.ui.myHome.MyBooks;

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
import com.example.myvideo.adapters.BooksRecyclerAdapter;
import com.example.myvideo.databinding.FragmentSuggestedBooksBinding;
import com.example.myvideo.models.BookModel;
import com.example.myvideo.ui.Explore.Book.BookFragment;
import com.example.myvideo.ui.myHome.MyCourses.MyCoursesViewModel;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class SuggestedBooksFragment extends Fragment {

    FragmentSuggestedBooksBinding binding;
    BooksRecyclerAdapter booksadapter = new BooksRecyclerAdapter();
    BottomNavigationView nav;
    MyBooksViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_suggested_books, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSuggestedBooksBinding.bind(view);

        viewModel = new ViewModelProvider(this).get(MyBooksViewModel.class);

        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        getBoooks();
    }
    private void getBoooks(){

        viewModel.getBooks2();
        viewModel.Books2.observe(getViewLifecycleOwner(), new Observer<ArrayList<BookModel>>() {
            @Override
            public void onChanged(ArrayList<BookModel> bookModels) {

                booksadapter.setList(bookModels);
                binding.recycler.setAdapter(booksadapter);

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}