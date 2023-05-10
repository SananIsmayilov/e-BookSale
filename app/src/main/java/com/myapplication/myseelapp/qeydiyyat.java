package com.myapplication.myseelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.myapplication.myseelapp.databinding.ActivityQeydiyyatBinding;

public class qeydiyyat extends AppCompatActivity {
FirebaseAuth auth;
ActivityQeydiyyatBinding bind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind=ActivityQeydiyyatBinding.inflate(getLayoutInflater());
        View view=bind.getRoot();
        setContentView(view);
        auth=FirebaseAuth.getInstance();
    }
    public void intenqeyd(View v){
        String email=bind.etxt3.getText().toString();
        String emailpas=bind.etxt4.getText().toString();
        if(!TextUtils.isEmpty(email) )
        {
            if(!TextUtils.isEmpty(emailpas)){
                auth.createUserWithEmailAndPassword(email,emailpas).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(qeydiyyat.this, "Uğurlu qeydiyyat...", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(qeydiyyat.this,alissatis.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure( Exception e) {
                        Toast.makeText(qeydiyyat.this, e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });
            }    else {
                Toast.makeText(this, "Məlumatları tam doldurun!", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Məlumatları tam doldurun!", Toast.LENGTH_SHORT).show();
    }}
}