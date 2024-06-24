package com.example.myvideo.ui.profile.Chat;

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
import com.example.myvideo.adapters.UsersRecyclerAdapter;
import com.example.myvideo.databinding.FragmentBaseChatBinding;
import com.example.myvideo.models.RegModel;
import com.example.myvideo.ui.profile.MyFriends.MyFriendsViewModel;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class BaseChatFragment extends Fragment {

    FragmentBaseChatBinding binding;
    BottomNavigationView nav;
    MyFriendsViewModel viewModel;

    UsersRecyclerAdapter adapter = new UsersRecyclerAdapter();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentBaseChatBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(MyFriendsViewModel.class);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);
        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getData(){

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        viewModel.getFriends();
        viewModel.list.observe(getViewLifecycleOwner(), new Observer<ArrayList<RegModel>>() {
            @Override
            public void onChanged(ArrayList<RegModel> regModels) {
                adapter.setList(regModels);
                binding.recycler.setAdapter(adapter);

                adapter.setOnItemClick(new UsersRecyclerAdapter.OnItemClick() {
                    @Override
                    public void OnClick(RegModel user) {
                        SharedModel.setSelected_user(user);
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new FriendsChatFragment() ,"Fchat")
                                .addToBackStack("Fchat").commit();
                    }
                });
            }
        });
    }

}