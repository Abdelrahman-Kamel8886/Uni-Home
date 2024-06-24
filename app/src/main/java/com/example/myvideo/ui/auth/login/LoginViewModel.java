package com.example.myvideo.ui.auth.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myvideo.utils.SharedModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {

    FirebaseAuth ref = FirebaseAuth.getInstance();
    MutableLiveData<Integer> loged = new MutableLiveData<>();
    MutableLiveData<Exception> notloged = new MutableLiveData<>();



    public void login(String email ,String password ){
        ref.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
                SharedModel.setId(user_id);
                loged.setValue(1);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                notloged.setValue(e);

            }
        });
    }


}
