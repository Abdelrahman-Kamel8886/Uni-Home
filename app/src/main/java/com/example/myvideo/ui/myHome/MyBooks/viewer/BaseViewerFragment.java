package com.example.myvideo.ui.myHome.MyBooks.viewer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myvideo.R;
import com.example.myvideo.adapters.PagerAdabter;
import com.example.myvideo.databinding.FragmentBaseViewerBinding;
import com.example.myvideo.models.PagerModelClass;
import com.example.myvideo.utils.SharedModel;

import java.util.ArrayList;


public class BaseViewerFragment extends Fragment {

    FragmentBaseViewerBinding binding;
    PagerAdabter adapter;

    ArrayList<PagerModelClass> model = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_viewer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentBaseViewerBinding.bind(view);


        adapter = new PagerAdabter(getChildFragmentManager());

        model.clear();

        model.add(new PagerModelClass(new InfoViewerFragment(), "Book Details "));
        model.add(new PagerModelClass(new ViewerFragment(), "Read"));



        adapter.setData(model);
        binding.Pager.setAdapter(adapter);
        binding.tablayout.setupWithViewPager(binding.Pager);
        binding.toolbar.setTitle(SharedModel.getSelected_book().getBook_title());
        binding.toolbar.setVisibility(View.GONE);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}