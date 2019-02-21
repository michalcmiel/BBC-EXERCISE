package com.example.bbcapplication;

import java.io.Serializable;

public class Fruit implements Serializable {
    private String fruit_name;
    private int fruit_price;
    private double fruit_weight;

    public Fruit(String fruit_name, int fruit_price, double fruit_weight){
        this.fruit_name = fruit_name;
        this.fruit_price = fruit_price;
        this.fruit_weight = fruit_weight;
    }

    public int getFruit_price() {
        return fruit_price;
    }

    public double getFruit_weight() {
        return fruit_weight;
    }

    public String getFruit_name() {
        return fruit_name;
    }

    public void setFruit_name(String fruit_name) {
        this.fruit_name = fruit_name;
    }

    public void setFruit_price(int fruit_price) {
        this.fruit_price = fruit_price;
    }

    public void setFruit_weight(double fruit_weight) {
        this.fruit_weight = fruit_weight;
    }
}
