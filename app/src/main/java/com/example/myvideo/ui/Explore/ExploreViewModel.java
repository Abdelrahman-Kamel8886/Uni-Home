package com.example.myvideo.ui.Explore;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.ArticleModel;
import com.example.myvideo.models.BookModel;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.models.PostModel;
import com.example.myvideo.utils.SharedModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ExploreViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<ArrayList<BookModel>> Books = new MutableLiveData<>();
    ArrayList<BookModel> bookModels = new ArrayList<>();

    DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference();
    public MutableLiveData<ArrayList<CourseModel>> Courses = new MutableLiveData<>();
    ArrayList<CourseModel> courseModels = new ArrayList<>();


    public MutableLiveData<ArrayList<String>> catslist = new MutableLiveData<>();
    ArrayList<String> cats = new ArrayList<>();

    public MutableLiveData<String> empty = new MutableLiveData<>();


    public MutableLiveData<ArrayList<ArticleModel>> articles = new MutableLiveData<>();
    ArrayList<ArticleModel> articlesmodels = new ArrayList<>();

    public MutableLiveData<ArrayList<PostModel>> posts = new MutableLiveData<>();
    ArrayList<PostModel> postModels = new ArrayList<>();





    public void getBooks(){
        bookModels.clear();
        ref.child("Books").child(SharedModel.getExplore_cat()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookModels.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    bookModels.add(snapshot1.getValue(BookModel.class));

                }
                if (bookModels.isEmpty()){
                    empty.setValue("null");
                }

                Books.setValue(bookModels);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }




    public void getCourses(){
        courseModels.clear();
        ref2.child("Courses").child(SharedModel.getExplore_cat()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                courseModels.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    Log.e("TAG", "onDataChange: "+snapshot1.getValue(CourseModel.class).getVideos().get(3).getLink());

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

    public void getCourses_Cats(){
        cats.clear();
        ref2.child("Courses").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cats.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    cats.add(snapshot1.getKey());

                }

                catslist.setValue(cats);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void getBooks_Cats(){
        cats.clear();
        ref2.child("Books").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cats.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    cats.add(snapshot1.getKey());

                }

                catslist.setValue(cats);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void getArticles(){
        articlesmodels.clear();
        ref2.child("Articles").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                articlesmodels.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                    articlesmodels.add(snapshot1.getValue(ArticleModel.class));

                }

                articles.setValue(articlesmodels);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void getPosts(){
        postModels.clear();
        ref2.child("Posts").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                postModels.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {


                    postModels.add(snapshot1.getValue(PostModel.class));

                }

                posts.setValue(postModels);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}
