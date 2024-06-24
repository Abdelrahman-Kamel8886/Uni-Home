package com.example.myvideo.ui.profile.MyPosts;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.PostModel;
import com.example.myvideo.utils.SharedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyPostsViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    public MutableLiveData<ArrayList<PostModel>> posts = new MutableLiveData<>();
    ArrayList<PostModel> postModels = new ArrayList<>();



    public void getPosts(){
        postModels.clear();
        ref.child("Posts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postModels.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    PostModel post = snapshot1.getValue(PostModel.class);

                    if (post.getWriter().equals(SharedModel.getUsername())){
                        postModels.add(post);
                    }




                }

                posts.setValue(postModels);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
