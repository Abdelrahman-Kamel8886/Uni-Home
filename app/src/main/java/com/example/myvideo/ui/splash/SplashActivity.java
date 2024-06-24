package com.example.myvideo.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myvideo.databinding.ActivitySplashBinding;
import com.example.myvideo.ui.onboard.onboarding;


public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;
    Handler h=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this, onboarding.class);
                startActivity(intent);
                finish();
            }
        },1000);

    }
}




