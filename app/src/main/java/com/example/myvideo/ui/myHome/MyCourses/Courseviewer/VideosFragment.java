package com.example.myvideo.ui.myHome.MyCourses.Courseviewer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myvideo.R;
import com.example.myvideo.VideoActivity;
import com.example.myvideo.adapters.VideosRecyclerAdapter;
import com.example.myvideo.databinding.FragmentVideosBinding;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.utils.SharedModel;

import java.util.ArrayList;


public class VideosFragment extends Fragment {

    FragmentVideosBinding binding;

    VideosRecyclerAdapter adapter = new VideosRecyclerAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_videos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentVideosBinding.bind(view);

        adapter.setList((ArrayList<CourseModel.Videos>) SharedModel.getSelected_course().getVideos());
        binding.recyclerc.setAdapter(adapter);


        adapter.setOnItemClick(new VideosRecyclerAdapter.OnItemClick() {
            @Override
            public void OnClick(CourseModel.Videos video) {

                SharedModel.setVideo_id(video.getLink());
                SharedModel.setVideo_title(video.getTitle());

                //Toast.makeText(getContext(), ""+SharedModel.getVideo_id(), Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(requireActivity(), VideoActivity.class);
                startActivity(intent);

                //requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_frame , new VideoVewierFragment(),"vv").addToBackStack("vv").commit();


            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}