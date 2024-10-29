package com.example.newkolbo;

public class PerfumeLine extends Perfume {
    private int amount;

    public PerfumeLine() {
    }

    public PerfumeLine(Perfume perfume){
       super(perfume);
       this.amount = 0;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
