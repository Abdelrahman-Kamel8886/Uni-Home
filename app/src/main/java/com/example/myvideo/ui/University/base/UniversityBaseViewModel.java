package com.example.myvideo.ui.University.base;

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

public class UniversityBaseViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

    public MutableLiveData<ArrayList<CourseModel>> Courses1 = new MutableLiveData<>();
    public MutableLiveData<ArrayList<CourseModel>> Courses2 = new MutableLiveData<>();

    ArrayList<CourseModel> courseModels = new ArrayList<>();
    ArrayList<CourseModel> courseModels2 = new ArrayList<>();


    public void getinfo(){
        ref.child("MyUniversity").child(SharedModel.getId()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SharedModel.setMyUniversity(snapshot.getValue(MyUniversityModel.class));
                getCourses1();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }




    private void getCourses1(){
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

                Courses1.setValue(courseModels);
                getCourses2();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void getCourses2(){
        String grade = Integer.toString(SharedModel.getMyUniversity().getGrade());
        String dep = Integer.toString(SharedModel.getMyUniversity().getDepartment());
        courseModels2.clear();
        ref.child("Universities").child("Faculty of Computer And Informatics Zagazig University").child("Grades")
                .child(grade)
                .child("departments")
                .child(dep)
                .child("terms").child("1")
                .child("Courses").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                courseModels2.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                    courseModels2.add(snapshot1.getValue(CourseModel.class));

                }

                Courses2.setValue(courseModels2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
