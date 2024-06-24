package com.example.myvideo.ui.University.MyUniversity;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.CourseModel;
import com.example.myvideo.models.MyUniversityModel;
import com.example.myvideo.utils.SharedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyUniversityViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<ArrayList<CourseModel>> Courses = new MutableLiveData<>();
    ArrayList<CourseModel> courseModels = new ArrayList<>();
    public MutableLiveData<String> empty = new MutableLiveData<>();

    //

    public void getinfo(){
        ref.child("MyUniversity").child(SharedModel.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SharedModel.setMyUniversity(snapshot.getValue(MyUniversityModel.class));
                getCourses();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    public void getCourses(){
        String grade = Integer.toString(SharedModel.getMyUniversity().getGrade());
        String dep = Integer.toString(SharedModel.getMyUniversity().getDepartment());
        courseModels.clear();
        ref.child("Universities").child("Faculty of Computer And Informatics Zagazig University").child("Grades")
                .child(grade)
                .child("departments")
                .child(dep)
                .child("terms").child("0")
                .child("Courses").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                courseModels.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                    courseModels.add(snapshot1.getValue(CourseModel.class));

                }
                if (courseModels.isEmpty()){
                    empty.setValue("null");
                }

                Courses.setValue(courseModels);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
