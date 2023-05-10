package com.myapplication.myseelapp;

import java.io.Serializable;

public class books implements Serializable {
    String bookname,authorname,sale;

    public books(String bookname, String authorname, String sale) {
        this.bookname = bookname;
        this.authorname = authorname;
        this.sale = sale;
    }
}
