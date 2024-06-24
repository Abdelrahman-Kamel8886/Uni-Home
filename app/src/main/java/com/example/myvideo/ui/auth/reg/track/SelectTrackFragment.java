package com.example.myvideo.ui.auth.reg.track;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myvideo.R;
import com.example.myvideo.adapters.TermsRecyclerAdapter;
import com.example.myvideo.adapters.TracksRecyclerAdapter;
import com.example.myvideo.databinding.FragmentSelectTrackBinding;
import com.example.myvideo.models.MyUniversityModel;
import com.example.myvideo.ui.auth.reg.RegViewModel;
import com.example.myvideo.ui.baseHome.HomeFragment;
import com.example.myvideo.utils.SharedModel;

import java.util.ArrayList;


public class SelectTrackFragment extends Fragment {

    FragmentSelectTrackBinding binding;
    TrackViewModel viewModel;
    RegViewModel regViewModel;
    TracksRecyclerAdapter adapter = new TracksRecyclerAdapter();
    int flag =0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_track, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSelectTrackBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(TrackViewModel.class);
        regViewModel =new ViewModelProvider(this).get(RegViewModel.class);

        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void getData(){
        viewModel.gtracks();
        viewModel.tracks.observe(getViewLifecycleOwner(), new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {
                adapter.setList(strings);
                binding.recycler.setAdapter(adapter);

                adapter.setOnItemClick(new TracksRecyclerAdapter.OnItemClick() {
                    @Override
                    public void OnClick(String track) {
                        SharedModel.setTrack(track);
                        binding.bar.setVisibility(View.VISIBLE);
                        sign();
                    }
                });

            }
        });
    }

    private void sign(){
        regViewModel.Sign(SharedModel.getUri(),SharedModel.getUsername(),SharedModel.getMail(),SharedModel.getPhone() , SharedModel.getPassword() , SharedModel.getBirth());

        regViewModel.loged.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                flag =1;
                FragmentManager fm = getActivity().getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                binding.bar.setVisibility(View.GONE);
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frame,new HomeFragment()).commit();

            }
        });
    }


}