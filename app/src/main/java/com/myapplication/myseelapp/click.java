package com.myapplication.myseelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.myapplication.myseelapp.databinding.ActivityClickBinding;

public class click extends AppCompatActivity {
     ActivityClickBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click);
        ActivityClickBinding binding=ActivityClickBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        Intent intent=getIntent();
       books books=(books)intent.getSerializableExtra("geden");
       binding.textView.setText(books.bookname);

    }
}