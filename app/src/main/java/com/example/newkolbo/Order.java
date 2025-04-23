package com.example.newkolbo;

import com.example.newkolbo.Activity.MainOrder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Order {

    private int sumprice;
    private int ordernum;
    private long date;
    private ArrayList<Perfume> perfumeList;
    private User user;


    public Order() {
        this.sumprice = 0;
        Calendar c = Calendar.getInstance();
        this.date = c.getTimeInMillis(); //c.setTimeInMillis(this.date); setText(c.getTime()+"")
        //this.perfumeList = new ArrayList<>(StartActivity.perfumes); //each order has all products with amount zero todo: fix copy constructor!
        this.perfumeList = new ArrayList<>(MainOrder.perfumeslist); //each order has all products with amount zero todo: fix copy constructor!
        for(Perfume p : this.perfumeList)
            p.setAmount(0);
        //this.user = RegisterActivity.currentUser; //todo
        this.user = null; //todo
    }


    public int getSumprice() {
        return sumprice;
    }

    public void setSumprice(int sumprice) {
        this.sumprice = sumprice;
    }

    public int getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(int ordernum) {
        this.ordernum = ordernum;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public ArrayList<Perfume> getPerfumeList() {
        return perfumeList;
    }

    public void setPerfumeList(ArrayList<Perfume> perfumeList) {
        this.perfumeList = perfumeList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
