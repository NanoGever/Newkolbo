package com.example.newkolbo;

public class Perfume {
    private int price;
    private int barcode;
    private String name;
    private boolean instock;
    private int amount;

    public Perfume() {
        //פעולה בונה ריקה
    }

    public Perfume(int price, int barcode, String name, boolean instock, int amount) {
        this.price = price;
        this.barcode = barcode;
        this.name = name;
        this.instock = instock;
        this.amount = amount;
    }

    public Perfume(Perfume perfume) {
        this.price = perfume.price;
        this.barcode = perfume.barcode;
        this.name = perfume.name;
        this.instock = perfume.instock;
        this.amount = perfume.amount;
    }

    public int getPrice() { // פעולות גט וסט
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInstock() {
        return instock;
    }

    public void setInstock(boolean instock) {
        this.instock = instock;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Perfume{" +
                "price=" + price +
                ", barcode=" + barcode +
                ", name='" + name + '\'' +
                ", instock=" + instock +
                ", amount=" + amount +
                '}';
    }
}
