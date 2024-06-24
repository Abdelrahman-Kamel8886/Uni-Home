package com.example.myvideo.ui.auth.reg.track;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.UniversityModel;
import com.example.myvideo.utils.SharedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrackViewModel extends ViewModel {


    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    ArrayList<String> list = new ArrayList<>();
    public MutableLiveData<ArrayList<String>> tracks = new MutableLiveData<>();


    public void gtracks(){

        ref.child("MainSuggested").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                   list.add(snapshot1.getKey());

                }
                tracks.setValue(list);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
