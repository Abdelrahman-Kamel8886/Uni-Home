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
import com.example.myvideo.adapters.AdapterRecyclerChat;
import com.example.myvideo.adapters.AdapterRecyclerChat2;
import com.example.myvideo.databinding.FragmentFriendsChatBinding;
import com.example.myvideo.models.ModelChat;
import com.example.myvideo.ui.chat.ChatViewModel;
import com.example.myvideo.utils.SharedModel;

import java.util.ArrayList;


public class FriendsChatFragment extends Fragment {

    FragmentFriendsChatBinding binding;
    FriendsChatViewModel viewModel;
    AdapterRecyclerChat2 adapter = new AdapterRecyclerChat2();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentFriendsChatBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(FriendsChatViewModel.class);
        getData();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getData(){
        binding.txt.setText(SharedModel.getSelected_user().getUsername());
        viewModel.getMassages();
        viewModel.list.observe(getViewLifecycleOwner(), new Observer<ArrayList<ModelChat>>() {
            @Override
            public void onChanged(ArrayList<ModelChat> modelChats) {
                adapter.setmMessageList(modelChats);
                binding.recyclerChat.setAdapter(adapter);
            }
        });
        onClicks();
    }
    private void onClicks(){

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        binding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Validation();


            }
        });
    }

    private void Validation(){
        String msg = binding.msgEdit.getText().toString().trim();

        if (!msg.isEmpty()){
            ModelChat model = new ModelChat();
            model.setMessage(msg);
            model.setSender(SharedModel.getUsername());

            viewModel.SendMessage(model);
            viewModel.success.observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    binding.msgEdit.setText("");
                }
            });
        }

    }

}