package com.example.newkolbo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Order {

    private int sumprice;
    private int ordernum;
    private long date;
    private ArrayList<Perfume> perfumelist;
    private User user;


    public Order( ) {
        this.sumprice = 0;
        Random rnd = new Random();
        this.ordernum = rnd.nextInt(9001) + 1000; //1000 - 10000
        Calendar c = Calendar.getInstance();
        this.date = c.getTimeInMillis(); //c.setTimeInMillis(this.date); setText(c.getTime()+"")
        this.perfumelist = new ArrayList<>();
        this.user = new User(0500001112, "Netanel", "arlozerov 23", "gmail...", "123456");
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

    public ArrayList<Perfume> getPerfumelist() {
        return perfumelist;
    }

    public void setPerfumelist(ArrayList<Perfume> perfumelist) {
        this.perfumelist = perfumelist;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //?
    public void addPerfume(Perfume perfume)
    {

    }


}
