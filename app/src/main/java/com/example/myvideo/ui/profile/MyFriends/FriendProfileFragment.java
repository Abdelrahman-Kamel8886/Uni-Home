package com.example.myvideo.ui.profile.MyFriends;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentFriendProfileBinding;
import com.example.myvideo.ui.profile.Chat.BaseChatFragment;
import com.example.myvideo.ui.profile.Chat.FriendsChatFragment;
import com.example.myvideo.utils.SharedModel;


public class FriendProfileFragment extends Fragment {

    FragmentFriendProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_friend_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentFriendProfileBinding.bind(view);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        binding.txt.setText(SharedModel.getSelected_user().getUsername());
        binding.dateText.setText(SharedModel.getSelected_user().getBirth());
        binding.phoneText.setText(SharedModel.getSelected_user().getPhone());
        binding.username.setText(SharedModel.getSelected_user().getUsername());
        binding.mail.setText(SharedModel.getSelected_user().getEmail());

        Glide.with(getContext())
                .load(SharedModel.getSelected_user().getImage())
                .into(binding.img);


        binding.chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new FriendsChatFragment(),"chat")
                        .addToBackStack("chat").commit();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}