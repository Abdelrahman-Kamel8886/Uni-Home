package com.example.myvideo.ui.baseHome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myvideo.R;
import com.example.myvideo.databinding.FragmentHomeBinding;
import com.example.myvideo.models.ModelAuthCache;
import com.example.myvideo.models.RegModel;
import com.example.myvideo.ui.Explore.Article.BlogFragment;
import com.example.myvideo.ui.Explore.CategFragment;
import com.example.myvideo.ui.University.MyUniversity.MyUniversityFragment;
import com.example.myvideo.ui.University.base.UniversityBaseFragment;
import com.example.myvideo.ui.myHome.MyHomeFragment;
import com.example.myvideo.ui.profile.ProfileFragment;
import com.example.myvideo.utils.SharedModel;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    ArrayList<ModelAuthCache> caches = new ArrayList<>();

    HomeViewModel viewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentHomeBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding.nav.setSelectedItemId(R.id.home_nav);
        binding.nav.setVisibility(View.GONE);
        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void getData() {


        viewModel.getData();
        viewModel.model.observe(getViewLifecycleOwner(), new Observer<RegModel>() {
            @Override
            public void onChanged(RegModel regModel) {
                SharedModel.setUsername(regModel.getUsername());
                SharedModel.setPhone(regModel.getPhone());
                SharedModel.setMail(regModel.getEmail());
                SharedModel.setBirth(regModel.getBirth());
                SharedModel.setImage(regModel.getImage());

                caches.add(new ModelAuthCache(regModel.getUsername(), SharedModel.getId(), regModel.getPhone() , regModel.getEmail()));
                SharedModel.cache(caches);
                onClicks();
            }
        });

    }

    private void onClicks(){
        binding.bar.setVisibility(View.GONE);
        binding.nav.setVisibility(View.VISIBLE);

        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MyHomeFragment()).commit();

        binding.nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.uni_nav:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new UniversityBaseFragment()).commit();
                        return true;

                    case R.id.blog_nav:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new BlogFragment()).commit();
                        return true;

                    case R.id.home_nav:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new MyHomeFragment()).commit();
                        return true;
                    case R.id.profile_nav:
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new ProfileFragment()).commit();
                        return true;

                }
                return false;
            }
        });


    }
}