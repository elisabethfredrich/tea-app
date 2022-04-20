package com.example.itea;

public class Item {
    private String name = null;
    private int price = 0;
    public Item(String productName, int productPrice) { name = productName;  price = productPrice; }
    @Override
    public String toString() { return oneLine(" ","kr."); }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public String oneLine(String pre, String post) {
        return name+pre + price+post;
    }
}
