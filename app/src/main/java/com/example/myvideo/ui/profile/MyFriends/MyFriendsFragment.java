package com.example.myvideo.ui.profile.MyFriends;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myvideo.R;
import com.example.myvideo.adapters.UsersRecyclerAdapter;
import com.example.myvideo.databinding.FragmentMyFriendsBinding;
import com.example.myvideo.models.RegModel;
import com.example.myvideo.ui.profile.Chat.BaseChatFragment;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;


public class MyFriendsFragment extends Fragment {

    FragmentMyFriendsBinding binding;
    BottomNavigationView nav;

    Button confirm;
    EditText userid;
    ProgressBar progressBar;
    BottomSheetDialog bottomSheetDialog;
    MyFriendsViewModel viewModel;

    UsersRecyclerAdapter adapter = new UsersRecyclerAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_friends, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMyFriendsBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(MyFriendsViewModel.class);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.GONE);onClicks();

        binding.myid.setText(SharedModel.getId());
        getData();



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getData(){
        onClicks();
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
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new FriendProfileFragment() ,"fp")
                                .addToBackStack("fp").commit();
                    }
                });

            }
        });
    }

    private void onClicks(){

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        binding.copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager cp =(ClipboardManager) view.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cp.setText(SharedModel.getId());
                Toast.makeText(getContext(), "Copied ", Toast.LENGTH_SHORT).show();

            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });
    }

    private void showdialog(){
        bottomSheetDialog = new BottomSheetDialog(getContext() , R.style.BottomSheetTheme);
        bottomSheetDialog.setContentView(R.layout.adjust);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        bottomSheetDialog.show();

        confirm = bottomSheetDialog.findViewById(R.id.confirm_button);
        progressBar = bottomSheetDialog.findViewById(R.id.bar);
        userid = bottomSheetDialog.findViewById(R.id.id_edit);


        onClicks2();
    }

    private void onClicks2() {
        progressBar.setVisibility(View.GONE);




        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Validation();
            }
        });


    }

    private void Validation() {


        String id = userid.getText().toString().trim();

        if (id.isEmpty()) {
            userid.setError("required");
        }


        else{
            progressBar.setVisibility(View.VISIBLE);
            SendData(id);

        }
    }
    private void SendData(String id){

        viewModel.check(id);
        viewModel.done.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });

        viewModel.failed.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getContext(), "User Id is not valid", Toast.LENGTH_SHORT).show();
                bottomSheetDialog.dismiss();
            }
        });


    }



}