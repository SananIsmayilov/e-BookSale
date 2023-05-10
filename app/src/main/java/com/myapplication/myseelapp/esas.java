package com.myapplication.myseelapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.myapplication.myseelapp.databinding.ActivityEsasBinding;

import java.util.ArrayList;
import java.util.Map;

public class esas extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    ArrayList<post> postlist;
    RecyclerView listm;
    adapter adap1;
    ActivityEsasBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding=ActivityEsasBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        postlist=new ArrayList<>();

        auth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        getdata();

        binding.listm.setLayoutManager(new LinearLayoutManager(esas.this));
        adap1=new adapter(postlist);
        binding.listm.setAdapter(adap1);
    }

    public  void getdata(){
 firebaseFirestore.collection("Posts").addSnapshotListener(new EventListener<QuerySnapshot>() {
     @Override
     public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
     if(error!=null){
         Toast.makeText(esas.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
     }
     if(value!=null){
         for(DocumentSnapshot snap:value.getDocuments()){
             Map<String,Object> data=snap.getData();
              String name= (String) data.get("Kitabın adı");
             String urldown= (String) data.get("downloadurl");
             String sales= (String) data.get("qiyməti");
             String number=(String) data.get("Əlaqə nömrəsi");
             String author=(String) data.get("Kitab müəllifi");
             post post1=new post(urldown,name,author,sales,number);
             postlist.add(post1);
         }
         adap1.notifyDataSetChanged();

     }


     }
 });
    }

}