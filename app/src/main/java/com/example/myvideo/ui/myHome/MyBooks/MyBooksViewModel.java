package com.example.myvideo.ui.myHome.MyBooks;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.BookModel;
import com.example.myvideo.utils.SharedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyBooksViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<ArrayList<BookModel>> Books = new MutableLiveData<>();
    ArrayList<BookModel> bookModels = new ArrayList<>();


    public MutableLiveData<ArrayList<BookModel>> Books2 = new MutableLiveData<>();
    ArrayList<BookModel> bookModels2 = new ArrayList<>();


    public void getBooks(){
        bookModels.clear();
        ref.child("MyBooks").child(SharedModel.getId()).child("Books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookModels.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    bookModels.add(snapshot1.getValue(BookModel.class));

                }
                Books.setValue(bookModels);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void getBooks2(){
        bookModels2.clear();
        ref.child("Suggested").child(SharedModel.getId()).child("Books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookModels2.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    bookModels2.add(snapshot1.getValue(BookModel.class));

                }
                Books2.setValue(bookModels2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
