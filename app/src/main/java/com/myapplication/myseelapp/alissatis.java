package com.myapplication.myseelapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class alissatis extends AppCompatActivity {
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alissatis);
        auth=FirebaseAuth.getInstance();
    }
    public void alis(View v) {
        Intent intent=new Intent(this,esas.class);
        startActivity(intent);
    }
    public void satis(View v){
        Intent intent=new Intent(this,postpage.class);
        startActivity(intent);
    }
    public void signoutbtn(View v){
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setTitle("Çıxmaq").setMessage("Profilinizdən çıxırsınız?").setCancelable(true);
        alert.setNegativeButton("Xeyr", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alert.setPositiveButton("Bəli", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                auth.signOut();
                Intent intent=new Intent(alissatis.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        alert.show();

    }
    public void mydovn(View v){
     Intent intent=new Intent(this,mybook.class);
     startActivity(intent);
    }

}