package com.myapplication.myseelapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.myseelapp.databinding.GorunumBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.holderadapter> {

    ArrayList<post> postlist;
    public adapter(ArrayList<post> postlist) {
        this.postlist = postlist;
    }




    @NonNull
    @Override
    public holderadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GorunumBinding binding=GorunumBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new holderadapter(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull holderadapter holder, int position) {
holder.binding.ad1.setText("Kitabın adı : "+ postlist.get(position).name);
holder.binding.muellif1.setText("Müəllif : "+postlist.get(position).author);
holder.binding.nomre1.setText("Əlaqə nömrəsi : +994 "+postlist.get(position).number);
holder.binding.qiymet1.setText("Qiymət : "+postlist.get(position).sales+" AZN");
        Picasso.get().load(postlist.get(position).url).into(holder.binding.imageview1);

    }

    @Override
    public int getItemCount() {
        return postlist.size();
    }

    public class holderadapter extends RecyclerView.ViewHolder{
GorunumBinding binding;
        public holderadapter(GorunumBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
