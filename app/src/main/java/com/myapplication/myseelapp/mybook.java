package com.myapplication.myseelapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;

import com.myapplication.myseelapp.databinding.ActivityMybookBinding;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public class mybook extends AppCompatActivity {
ArrayList<books> bookarrlist;
    SQLiteDatabase db;
    RecyclerView rc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMybookBinding binding;
        binding=ActivityMybookBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        bookarrlist=new ArrayList<>();
        binding.rc.setLayoutManager(new LinearLayoutManager(this));
        bokadapter bkk=new bokadapter(bookarrlist);
        binding.rc.setAdapter(bkk);

        getdata();

    }

    public void getdata(){
        View v;
       db =this.openOrCreateDatabase("Book",MODE_PRIVATE,null);
        Cursor cursor=db.rawQuery("SELECT*FROM book",null);
        int bn=cursor.getColumnIndex("bname");
        int an=cursor.getColumnIndex("aname");
        int bs=cursor.getColumnIndex("bsale");
        while(cursor.moveToNext()){
            String bname=cursor.getString(bn);
            String aname=cursor.getString(an);
            String bsale=cursor.getString(bs);
            books bk1=new books(bname,aname,bsale);
            bookarrlist.add(bk1);
        }
        cursor.close();
    }
}