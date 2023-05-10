package com.myapplication.myseelapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.myseelapp.databinding.BoksqlBinding;

import java.util.ArrayList;

public class bokadapter extends RecyclerView.Adapter<bokadapter.bokholder> {
ArrayList<books> bookarrlist;

    public bokadapter(ArrayList<books> bookarrlist) {
        this.bookarrlist = bookarrlist;
    }

    @NonNull
    @Override
    public bokholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BoksqlBinding binding= BoksqlBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new bokholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull bokholder holder, int position) {
    holder.binding.txt1.setText("Kitab adı: "+bookarrlist.get(position).bookname);
    holder.binding.txt2.setText("Müəllif: "+bookarrlist.get(position).authorname);
    holder.binding.txt3.setText("Qiyməti: "+bookarrlist.get(position).sale+"AZN");
    holder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(holder.itemView.getContext(), click.class);
            intent.putExtra("geden", (bookarrlist.get(position)));
            holder.itemView.getContext().startActivity(intent);
        }
    });
    }

    @Override
    public int getItemCount(){
        return bookarrlist.size();
    }

    public class bokholder extends RecyclerView.ViewHolder{
        BoksqlBinding binding;
        public bokholder(BoksqlBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }


}
