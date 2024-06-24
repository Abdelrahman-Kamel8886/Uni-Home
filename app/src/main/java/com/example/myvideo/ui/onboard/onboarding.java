package com.example.myvideo.ui.onboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.myvideo.MainActivity;
import com.example.myvideo.R;
import com.example.myvideo.adapters.slideViewAdapter;


public class onboarding extends AppCompatActivity {
    public static ViewPager viewPager;
    slideViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding1);

        viewPager=findViewById(R.id.viewpager);
        adapter=new slideViewAdapter(this);
        viewPager.setAdapter(adapter);

        if(isOpenAlready()){
            //here
            Intent intent=new Intent(onboarding.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
        else{
            SharedPreferences.Editor editor=getSharedPreferences("slide",MODE_PRIVATE).edit();
            editor.putBoolean("slide",true);
            editor.commit();
        }
    }

    private boolean isOpenAlready() {
        SharedPreferences sharedPreferences=getSharedPreferences("slide",MODE_PRIVATE);
        boolean result=sharedPreferences.getBoolean("slide",false);
        return result;

    }
}