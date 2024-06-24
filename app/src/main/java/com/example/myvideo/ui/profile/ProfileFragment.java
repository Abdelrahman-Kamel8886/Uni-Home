package com.example.myvideo.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentProfileBinding;
import com.example.myvideo.models.ModelAuthCache;
import com.example.myvideo.ui.auth.login.LoginFragment;
import com.example.myvideo.ui.profile.Account.FacultyInfo.FacultyInfoFragment;
import com.example.myvideo.ui.profile.Account.myprofile.MyProfileFragment;
import com.example.myvideo.ui.profile.Account.securitysettings.SecurityFragment;
import com.example.myvideo.ui.profile.MyFriends.MyFriendsFragment;
import com.example.myvideo.ui.profile.MyPosts.MyPostsFragment;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {



    FragmentProfileBinding binding;
    ArrayList<ModelAuthCache> caches = new ArrayList<>();
    BottomNavigationView nav;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentProfileBinding.bind(view);
        nav = requireActivity().findViewById(R.id.nav);
        nav.setVisibility(View.VISIBLE);

        binding.username.setText(SharedModel.getUsername());
        binding.mail.setText(SharedModel.getMail());
        binding.phoneText.setText(SharedModel.getPhone());
        binding.dateText.setText(SharedModel.getBirth());

        Glide.with(getContext())
                .load(SharedModel.getImage())
                .into(binding.img);

        onClicks();

        caches.add(new ModelAuthCache(SharedModel.getUsername(), SharedModel.getId(), SharedModel.getPhone() , SharedModel.getMail()));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void onClicks(){

        binding.settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new SecurityFragment(),"sc")
                        .addToBackStack("sc").commit();
            }
        });

        binding.postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MyPostsFragment(),"p")
                        .addToBackStack("p").commit();
            }
        });

        binding.friendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MyFriendsFragment(),"f")
                        .addToBackStack("f").commit();
            }
        });



        binding.logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                SharedModel.delete(caches.get(0));
                SharedModel.cache = false;
                //FragmentManager fm = getActivity().getSupportFragmentManager();
                //for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                   // fm.popBackStack();
                //}
                Toast.makeText(getContext(), "Logged Out Successfully", Toast.LENGTH_SHORT).show();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame , new LoginFragment()).commit();
            }
        });
    }
}