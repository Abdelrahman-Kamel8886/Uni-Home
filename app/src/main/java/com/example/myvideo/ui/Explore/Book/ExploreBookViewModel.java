package com.example.myvideo.ui.Explore.Book;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.BookModel;
import com.example.myvideo.utils.SharedModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ExploreBookViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    public MutableLiveData<Integer> done = new MutableLiveData<>();

    public MutableLiveData<Integer> added = new MutableLiveData<>();

    public void addBook(BookModel model){

        ref.child("MyBooks").child(SharedModel.getId()).child("Books").child(model.getBook_title()).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                done.setValue(1);
            }
        });
    }


    public void Check(BookModel model){

        ref.child("MyBooks").child(SharedModel.getId()).child("Books").child(model.getBook_title()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if (snapshot.getValue() != null){
                    added.setValue(1);
                }
                else {
                    added.setValue(0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
