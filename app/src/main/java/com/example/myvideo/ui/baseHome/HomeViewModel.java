package com.example.myvideo.ui.baseHome;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.RegModel;
import com.example.myvideo.utils.Consts;
import com.example.myvideo.utils.SharedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    MutableLiveData<RegModel> model = new MutableLiveData<>();



    public void getData(){
        ref.child(Consts.USERS ).child(SharedModel.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SharedModel.setId(snapshot.getKey());
                model.setValue(snapshot.getValue(RegModel.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }

}
