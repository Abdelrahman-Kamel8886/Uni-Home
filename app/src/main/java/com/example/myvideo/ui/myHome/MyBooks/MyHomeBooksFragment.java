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
import com.example.myvideo.databinding.FragmentMyHomeBooksBinding;
import com.example.myvideo.models.BookModel;
import com.example.myvideo.ui.Explore.Book.BookFragment;
import com.example.myvideo.ui.myHome.MyBooks.viewer.BaseViewerFragment;
import com.example.myvideo.ui.myHome.MyCourses.MyCoursesBaseFragment;
import com.example.myvideo.utils.SharedModel;

import java.util.ArrayList;


public class MyHomeBooksFragment extends Fragment {


    FragmentMyHomeBooksBinding binding;
    BooksRecyclerAdapter adapter = new BooksRecyclerAdapter();
    BooksRecyclerAdapter adapter2 = new BooksRecyclerAdapter();
    MyBooksViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_home_books, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMyHomeBooksBinding.bind(view);

        viewModel = new ViewModelProvider(this).get(MyBooksViewModel.class);

        binding.txt1.setVisibility(View.GONE);
        binding.txt2.setVisibility(View.GONE);
        binding.txt3.setVisibility(View.GONE);
        binding.txt4.setVisibility(View.GONE);

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

                ArrayList<BookModel> list1 = new ArrayList<>();

                if (bookModels.size()>=4){
                    list1.addAll(bookModels.subList(0,4));
                }
                else {
                    list1.addAll(bookModels);
                }


                adapter.setList(list1);
                binding.recycler1.setAdapter(adapter);

                if (list1.size()>0){
                    binding.txt1.setVisibility(View.VISIBLE);
                    binding.txt3.setVisibility(View.VISIBLE);
                }

                getBooks2();
            }
        });
    }

    private void getBooks2(){

        viewModel.getBooks2();

        viewModel.Books2.observe(getViewLifecycleOwner(), new Observer<ArrayList<BookModel>>() {
            @Override
            public void onChanged(ArrayList<BookModel> bookModels) {

                ArrayList<BookModel> list1 = new ArrayList<>();

                if (bookModels.size()>=4){
                    list1.addAll(bookModels.subList(0,4));
                }
                else {
                    list1.addAll(bookModels);
                }



                adapter2.setList(list1);
                binding.recycler2.setAdapter(adapter2);

                if (list1.size()>0){
                    binding.txt2.setVisibility(View.VISIBLE);
                    binding.txt4.setVisibility(View.VISIBLE);
                }


                onClicks();
            }
        });
    }

    private void onClicks(){


        adapter.setOnItemClick(new BooksRecyclerAdapter.OnItemClick() {
            @Override
            public void OnClick(BookModel book) {

                SharedModel.setSelected_book(book);
                //Toast.makeText(getContext(), ""+book.getBook_title(), Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new BaseViewerFragment(),"bv").addToBackStack("bv").commit();
            }
        });

        adapter2.setOnItemClick(new BooksRecyclerAdapter.OnItemClick() {
            @Override
            public void OnClick(BookModel book) {
                SharedModel.setSelected_book(book);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new BookFragment(),"bb")
                        .addToBackStack("bb").commit();
            }
        });

        binding.txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MyBooksBaseFragment(),"mmb").addToBackStack("mmb").commit();
            }
        });

        binding.txt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new SuggestedBooksFragment(),"msb").addToBackStack("msb").commit();
            }
        });


    }

}