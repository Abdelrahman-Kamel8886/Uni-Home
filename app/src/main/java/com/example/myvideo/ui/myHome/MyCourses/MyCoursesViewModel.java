package com.example.myvideo.ui.myHome.MyCourses;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.CourseModel;
import com.example.myvideo.utils.SharedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyCoursesViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<ArrayList<CourseModel>> Courses = new MutableLiveData<>();
    ArrayList<CourseModel> courseModels = new ArrayList<>();

    ArrayList<CourseModel> courseModels2 = new ArrayList<>();
    public MutableLiveData<ArrayList<CourseModel>> SuggestedCourseModels = new MutableLiveData<>();


    public void getCourses(){
        courseModels.clear();
        ref.child("MyCourses").child(SharedModel.getId()).child("Courses").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                courseModels.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    courseModels.add(snapshot1.getValue(CourseModel.class));

                }
                Courses.setValue(courseModels);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void getSuggestedCourses(){
        courseModels2.clear();
        ref.child("Suggested").child(SharedModel.getId()).child("Courses").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                courseModels2.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    courseModels2.add(snapshot1.getValue(CourseModel.class));

                }
                SuggestedCourseModels.setValue(courseModels2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
