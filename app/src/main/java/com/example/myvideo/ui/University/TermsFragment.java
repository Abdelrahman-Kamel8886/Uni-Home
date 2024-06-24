package com.example.myvideo.ui.University;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myvideo.R;
import com.example.myvideo.adapters.TermsRecyclerAdapter;
import com.example.myvideo.databinding.FragmentTermsBinding;
import com.example.myvideo.models.UniversityModel;
import com.example.myvideo.ui.University.Courses.DegreeCoursesFragment;
import com.example.myvideo.ui.University.Department.DepartmentsFragment;
import com.example.myvideo.utils.SharedModel;

import java.util.ArrayList;


public class TermsFragment extends Fragment {
    FragmentTermsBinding binding;
    TermsRecyclerAdapter adapter = new TermsRecyclerAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_terms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentTermsBinding.bind(view);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        show();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void show(){
        adapter.setList((ArrayList<UniversityModel.Grades.Departments.Terms>) SharedModel.getSelected_department().getTerms());
        binding.recycler.setAdapter(adapter);

        adapter.setOnItemClick(new TermsRecyclerAdapter.OnItemClick() {
            @Override
            public void OnClick(UniversityModel.Grades.Departments.Terms term) {
                SharedModel.setSelected_term(term);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.home_frame , new DegreeCoursesFragment(),"bc").addToBackStack("bc").commit();

            }
        });

    }
}