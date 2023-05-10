package com.myapplication.myseelapp;

import android.widget.TextView;

import java.io.Serializable;

public class post implements Serializable {
    public String url;
     public String name;
    public String author;
    public String sales;
  public   String number;

    public post(String url, String name, String author, String sales, String number) {
        this.url = url;
        this.name = name;
        this.author = author;
        this.sales = sales;
        this.number = number;
    }
}
