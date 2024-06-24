package com.example.myvideo.ui.profile.Chat;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.ModelChat;
import com.example.myvideo.utils.SharedModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FriendsChatViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    MutableLiveData<String> success = new MutableLiveData<>();

    ArrayList<ModelChat> msgs = new ArrayList<>();
    MutableLiveData<ArrayList<ModelChat>> list = new MutableLiveData<>();




    public void SendMessage(ModelChat model){
        int n1 = Integer.parseInt(SharedModel.getPhone());
        int n2 = Integer.parseInt(SharedModel.getSelected_user().getPhone());
        int n = n1+n2;

        ref.child("Chats").child(""+n).push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                success.setValue("success");
            }
        });
    }





    public void getMassages (){
        int n1 = Integer.parseInt(SharedModel.getPhone());
        int n2 = Integer.parseInt(SharedModel.getSelected_user().getPhone());
        int n = n1+n2;


        ref.child("Chats").child(""+n).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                msgs.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    msgs.add(snapshot1.getValue(ModelChat.class));
                }

                list.setValue(msgs);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
