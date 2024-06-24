package com.example.myvideo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.example.myvideo.databinding.ActivityVideoBinding;
import com.example.myvideo.utils.SharedModel;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

public class VideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {



    ActivityVideoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setTitle(SharedModel.getVideo_title());
        binding.player.initialize("AIzaSyBp3O5Yd_M9aA2nMdfwy6AgYAdu0tZESws",this);

        onClicks();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }



    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(SharedModel.getVideo_id() , 0);
        youTubePlayer.play();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(VideoActivity.this, "Failed", Toast.LENGTH_SHORT).show();
    }

    private void onClicks(){
        Log.e("TAG", "onClicks: " );
        if(SharedModel.getIndex() == 0){
            binding.priv.setVisibility(View.GONE);
            binding.next.setVisibility(View.VISIBLE);
        }
        else if (SharedModel.getIndex() == SharedModel.getSelected_course().getVideos().size() -1 ){
            binding.priv.setVisibility(View.VISIBLE);
            binding.next.setVisibility(View.GONE);
        }
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedModel.setIndex(SharedModel.getIndex()+1);
                reload();

            }
        });
        binding.priv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedModel.setIndex(SharedModel.getIndex()-1);
                reload();
            }
        });
    }
    private void reload(){
        SharedModel.setVideo_id(SharedModel.getSelected_course().getVideos().get(SharedModel.getIndex()).getLink());
        SharedModel.setVideo_title(SharedModel.getSelected_course().getVideos().get(SharedModel.getIndex()).getTitle());
        VideoActivity.this.recreate();
    }


}