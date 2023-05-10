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
import com.google.firebase.auth.FirebaseUser;
import com.myapplication.myseelapp.databinding.LoginpageBinding;

public class MainActivity extends AppCompatActivity {
LoginpageBinding Binding;
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding=LoginpageBinding.inflate(getLayoutInflater());
        View view =Binding.getRoot();
        setContentView(view);
        auth=FirebaseAuth.getInstance();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent intenttoesas=new Intent(MainActivity.this,alissatis.class);
            startActivity(intenttoesas);
            finish();
        }
    }
  public void qeydiyyataget(View v){
        Intent intent=new Intent(this,qeydiyyat.class);
        startActivity(intent);
  }
  public void qeydiol(View v){
   String useremail=Binding.etxt1.getText().toString();
   String userpas=Binding.etxt2.getText().toString();
   if(TextUtils.isEmpty(useremail) || TextUtils.isEmpty(userpas)){
       Toast.makeText(this, "Xanaları tam doldurun", Toast.LENGTH_SHORT).show();
   }
   else{
       auth.signInWithEmailAndPassword(useremail,userpas).addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               Toast.makeText(MainActivity.this, e.getLocalizedMessage().toString(), Toast.LENGTH_SHORT).show();
           }
       }).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
           @Override
           public void onSuccess(AuthResult authResult) {
               Intent intenttoesas=new Intent(MainActivity.this,alissatis.class);
               startActivity(intenttoesas);
               finish();
           }
       });
   }
  }
}