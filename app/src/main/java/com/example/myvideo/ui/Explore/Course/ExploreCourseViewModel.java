package com.example.myvideo.ui.Explore.Course;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.CourseModel;
import com.example.myvideo.utils.SharedModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ExploreCourseViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    public MutableLiveData<Integer> done = new MutableLiveData<>();

    public MutableLiveData<Integer> added = new MutableLiveData<>();

    public void addCourse(CourseModel model){

        ref.child("MyCourses").child(SharedModel.getId()).child("Courses").child(model.getTitle()).setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                done.setValue(1);
            }
        });
    }


    public void Check(CourseModel model){

        ref.child("MyCourses").child(SharedModel.getId()).child("Courses").child(model.getTitle()).addListenerForSingleValueEvent(new ValueEventListener() {
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
