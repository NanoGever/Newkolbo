package com.example.newkolbo;

import com.example.newkolbo.Activity.LoginRegActivity;
import com.example.newkolbo.Activity.MainActivity;
import com.example.newkolbo.Activity.MainOrder;
import com.example.newkolbo.Activity.OrderActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Order implements Serializable {

    private int sumprice;
    private String ordernum;
    private long date;
    private ArrayList<Perfume> perfumeList;
    private User user;


    public Order() {
        this.sumprice = 0;
        Calendar c = Calendar.getInstance();
        this.date = c.getTimeInMillis(); //c.setTimeInMillis(this.date); setText(c.getTime()+"")
        //this.perfumeList = new ArrayList<>(StartActivity.perfumes); //each order has all products with amount zero todo: fix copy constructor!
        //this.perfumeList = new ArrayList<>(initData()); //each order has all products with amount zero todo: fix copy constructor!
        this.perfumeList = new ArrayList<>(MainActivity.perfumeslist); //each order has all products with amount zero todo: fix copy constructor!
        for(Perfume p : this.perfumeList)
            p.setAmount(0);
        this.user = LoginRegActivity.currentUser;
    }

    /*private ArrayList<Perfume> initData() {
        ArrayList<Perfume> perfumeslist = new ArrayList<>();
        perfumeslist.add(new Perfume(1,1,"A",true,0));
        perfumeslist.add(new Perfume(3,2,"B",true,0));
        perfumeslist.add(new Perfume(2,3,"C",true,0));
        perfumeslist.add(new Perfume(2,4,"D",true,0));
        perfumeslist.add(new Perfume(2,5,"E",true,0));
        perfumeslist.add(new Perfume(2,6,"F",true,0));
        return perfumeslist;
    }*/

    public int getSumprice() {
        return sumprice;
    }

    public void setSumprice(int sumprice) {
        this.sumprice = sumprice;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
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
