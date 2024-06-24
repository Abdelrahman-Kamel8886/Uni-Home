package com.example.myvideo.ui.University.AllDegree;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.CourseModel;
import com.example.myvideo.models.MyUniversityModel;
import com.example.myvideo.models.UniversityModel;
import com.example.myvideo.utils.SharedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllDegreesViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<ArrayList<UniversityModel.Grades>> Grades = new MutableLiveData<>();



    public void getinfo(){

        ref.child("Universities").child("Faculty of Computer And Informatics Zagazig University").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SharedModel.setSelected_university(snapshot.getValue(UniversityModel.class));
                Grades.setValue((ArrayList<UniversityModel.Grades>) SharedModel.getSelected_university().getGrades());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}
