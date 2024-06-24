package com.example.myvideo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.myvideo.databinding.ActivityMainBinding;
import com.example.myvideo.local.MyRoomDatabase;
import com.example.myvideo.models.ModelAuthCache;
import com.example.myvideo.ui.auth.login.LoginFragment;
import com.example.myvideo.ui.baseHome.HomeFragment;
import com.example.myvideo.utils.SharedModel;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.FirebaseApp;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        getcache();

    }

    private void splash(){

        if(SharedModel.cache == false){
            getSupportFragmentManager().beginTransaction().replace(binding.frame.getId(),new LoginFragment()).commit();
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(binding.frame.getId(),new HomeFragment()).commit();
        }

    }

    private   void getcache(){
        MyRoomDatabase.getInstance().getDao().getall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ModelAuthCache>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<ModelAuthCache> modelAuthCaches) {


                        if(modelAuthCaches.size()>0){
                            SharedModel.cache = true;
                            SharedModel.setId(modelAuthCaches.get(0).getUser_id());
                            SharedModel.setPhone(modelAuthCaches.get(0).getUser_phone());
                            SharedModel.setUsername(modelAuthCaches.get(0).getUser_name());
                            SharedModel.setMail(modelAuthCaches.get(0).getEmail());
                            splash();
                        }
                        else{
                            splash();
                        }


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        splash();
                    }
                });
    }
}