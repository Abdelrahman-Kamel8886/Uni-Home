package com.example.myvideo.ui.profile.Account.FacultyInfo;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.UniversityModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FacultyInfoViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<UniversityModel>  model = new MutableLiveData<>();



    public void getData(){
        ref.child("Universities").child("Faculty of Computer And Informatics Zagazig University").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                model.setValue(snapshot.getValue(UniversityModel.class));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
