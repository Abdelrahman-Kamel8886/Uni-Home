package com.example.myvideo;

import android.app.Application;

import com.example.myvideo.local.MyRoomDatabase;
import com.google.firebase.FirebaseApp;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyRoomDatabase.initRoom(this);
    }
}
