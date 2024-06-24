package com.example.myvideo.ui.myHome.MyBooks;

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
import com.example.myvideo.adapters.BooksRecyclerAdapter;
import com.example.myvideo.databinding.FragmentMyBooksBaseBinding;
import com.example.myvideo.models.BookModel;
import com.example.myvideo.ui.myHome.MyBooks.viewer.BaseViewerFragment;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MyBooksBaseFragment extends Fragment {

    FragmentMyBooksBaseBinding binding;
    BooksRecyclerAdapter adapter = new BooksRecyclerAdapter();
    MyBooksViewModel viewModel;
    BottomNavigationView nav;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_books_base, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMyBooksBaseBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(MyBooksViewModel.class);

        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);

        binding.bar.setVisibility(View.VISIBLE);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
        getBooks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getBooks(){

        viewModel.getBooks();

        viewModel.Books.observe(getViewLifecycleOwner(), new Observer<ArrayList<BookModel>>() {
            @Override
            public void onChanged(ArrayList<BookModel> bookModels) {

                adapter.setList(bookModels);
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

        adapter.setOnItemClick(new BooksRecyclerAdapter.OnItemClick() {
            @Override
            public void OnClick(BookModel book) {

                SharedModel.setSelected_book(book);
                //Toast.makeText(getContext(), ""+book.getBook_title(), Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new BaseViewerFragment(),"bv").addToBackStack("bv").commit();
            }
        });

    }
}