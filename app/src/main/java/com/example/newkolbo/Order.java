package com.example.newkolbo;

import java.util.ArrayList;

public class Order {

    private int sumprice;
    private int ordernum;
    private String date;
    private ArrayList<Perfume>perfumelist;
    private User user;



    public Order(int sumprice, int ordernum, String date, ArrayList<Perfume> perfumelist, User user) {
        this.sumprice = sumprice;
        this.ordernum = ordernum;
        this.date = date;
        this.perfumelist = perfumelist;
        this.user = user;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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


}
