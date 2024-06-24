package com.example.myvideo.ui.auth.reg.collage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentSelectCollageBinding;
import com.example.myvideo.ui.University.AllDegree.AllDegreesFragment;

public class SelectCollageFragment extends Fragment {

    FragmentSelectCollageBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_collage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSelectCollageBinding.bind(view);

        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.select_frame,new AllDegreesFragment()).commit();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}