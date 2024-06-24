package com.example.myvideo.ui.profile.MyFriends;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.PostModel;
import com.example.myvideo.models.RegModel;
import com.example.myvideo.utils.SharedModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyFriendsViewModel extends ViewModel {


    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();


    public MutableLiveData<String> done= new MutableLiveData<>();
    public MutableLiveData<String> failed= new MutableLiveData<>();

    public MutableLiveData<ArrayList<RegModel>> list = new MutableLiveData<>();

    ArrayList<RegModel> users = new ArrayList<>();
    ArrayList<String> friends = new ArrayList<>();

    public void SendData(String id ){

        ref.child("Friends").child(SharedModel.getId()).child(id).setValue("done").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                ref.child("Friends").child(id).child(SharedModel.getId()).setValue("done").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        done.setValue("2");
                    }
                });
            }
        });



    }

    public void check(String id){
        ref.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i =0;
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    if (snapshot1.getKey().equals(id) && !SharedModel.getId() .equals(id) ){
                        i =1;
                        break;
                    }
                }
                if (i==1){
                    SendData(id);
                }
                else {
                    failed.setValue("5");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void getFriends(){
        friends.clear();
        ref.child("Friends").child(SharedModel.getId()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                friends.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    friends.add(snapshot1.getKey());
                }
                getUsers();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getUsers(){
        users.clear();
        for (String user : friends) {
            ref.child("users").child(user).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    users.add(snapshot.getValue(RegModel.class));

                    if (users.size() == friends.size()){
                        list.setValue(users);
                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }




}
