package com.example.myvideo.ui.profile.Account.myprofile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentMyProfileBinding;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MyProfileFragment extends Fragment {

    FragmentMyProfileBinding binding;
    BottomNavigationView nav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMyProfileBinding.bind(view);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);


        binding.birthTxt.setText(SharedModel.getBirth());
        binding.phoneTxt.setText(SharedModel.getPhone());
        binding.userTxt.setText(SharedModel.getUsername());
        binding.mailTxt.setText(SharedModel.getMail());

        Glide.with(getContext())
                .load(SharedModel.getImage())
                .into(binding.img);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}