package com.example.myvideo.ui.Explore.Book;

import android.os.Bundle;
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
import com.example.myvideo.databinding.FragmentBookBinding;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BookFragment extends Fragment {


    FragmentBookBinding binding;
    BottomNavigationView nav;

    ExploreBookViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentBookBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(ExploreBookViewModel.class);

        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);



        binding.toolbar.setTitle(SharedModel.getSelected_book().getBook_title());
        binding.author.setText(SharedModel.getSelected_book().getBook_author());
        binding.about.setText(SharedModel.getSelected_book().getBook_overview());
        Glide.with(getContext())
                .load(SharedModel.getSelected_book().getImglink())
                .into(binding.img);

        binding.addBtn.setVisibility(View.GONE);

        getData();



    }

    private void getData(){

        viewModel.Check(SharedModel.getSelected_book());

        viewModel.added.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                if (integer==1){
                    binding.addBtn.setText("This book has already been added");
                    binding.addBtn.setBackgroundColor(getResources().getColor(R.color.color22));
                    binding.addBtn.setClickable(false);
                    binding.addBtn.setVisibility(View.VISIBLE);
                }
                else{
                    binding.addBtn.setVisibility(View.VISIBLE);
                    binding.addBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            binding.addBtn.setText("");
                            binding.addBtn.setClickable(false);
                            binding.bar.setVisibility(View.VISIBLE);
                            addBook();
                        }
                    });
                }
            }
        });





    }
    private void addBook(){

        viewModel.addBook(SharedModel.getSelected_book());

        viewModel.done.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.bar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Book added Successfully", Toast.LENGTH_SHORT).show();
                requireActivity().onBackPressed();
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}