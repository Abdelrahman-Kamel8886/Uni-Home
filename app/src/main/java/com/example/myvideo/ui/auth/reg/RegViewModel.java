package com.example.myvideo.ui.auth.reg;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.models.BookModel;
import com.example.myvideo.models.CourseModel;
import com.example.myvideo.models.MyUniversityModel;
import com.example.myvideo.models.RegModel;
import com.example.myvideo.utils.Consts;
import com.example.myvideo.utils.SharedModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class RegViewModel extends ViewModel {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth auth = FirebaseAuth.getInstance();

    private StorageReference storage = FirebaseStorage.getInstance().getReference();
    private StorageReference sRef = FirebaseStorage.getInstance().getReference();

    public MutableLiveData<Integer> loged = new MutableLiveData<>();

    ArrayList<CourseModel> course = new ArrayList<>();
    ArrayList<BookModel> books = new ArrayList<>();


    public void Sign( Uri filePath  , String username , String email ,String phone, String password , String birth){
        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                uploadFile(filePath  , username , email ,phone, password ,birth);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void uploadFile(Uri filePath  , String username , String email ,String phone, String password , String birth) {

        if (filePath != null) {
            sRef = storage.child("file/" + "profiles/"+ phone +"/" +System.currentTimeMillis());

            sRef.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                    final StorageReference filePath = sRef;


                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            String finalpath = uri.toString();


                            SendData(username,email,phone,password,birth,finalpath);
                        }
                    });
                }
            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });


        }
    }



    private void SendData(String username , String email ,String phone, String password , String birth , String image){
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.e("TAG", "SendData: "+id );
        Log.e("TAG", "SendData: "+ref );
        if (id != null){
            SharedModel.setId(id);
            SharedModel.setUsername(username);
            ref.child(Consts.USERS).child(id).setValue(new RegModel(username,email,phone , password , birth , image))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    sendData2();
                }
            });
        }


    }

    private void sendData2(){
        ref.child("MyUniversity").child(SharedModel.getId()).setValue(SharedModel.getMyUniversity())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        getCourses();
                    }
                });
    }

    private void getCourses(){
        ref.child("MainSuggested").child(SharedModel.getTrack()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    course.add(snapshot1.getValue(CourseModel.class));

                }
                getBooks();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    private void getBooks(){
        ref.child("Books").child("Programming").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {

                    books.add(snapshot1.getValue(BookModel.class));

                }
                sendData4();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendData4(){
        ref.child("Suggested").child(SharedModel.getId()).child("Books").setValue(books)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        sendData3();
                    }
                });
    }

    private void sendData3(){
        ref.child("Suggested").child(SharedModel.getId()).child("Courses").setValue(course)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        login(SharedModel.getMail() , SharedModel.getPassword());
                    }
                });
    }


    private void login(String email ,String password ){
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                loged.setValue(1);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


}
