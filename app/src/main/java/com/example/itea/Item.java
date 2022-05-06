package com.example.itea;

public class Item {
    private String name;
    private int price;
    private String image;
    public Item(String productName, int productPrice, String imageName) {
        name = productName;
        price = productPrice;
        image = imageName;


    }
    @Override
    public String toString() { return oneLine(" ","kr."); }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public String getImage() { return image;}
    public String oneLine(String pre, String post) {
        return name+pre + price+post;
    }
}
