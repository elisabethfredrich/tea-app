package com.example.itea;

import android.content.Context;
import android.graphics.drawable.Drawable;


public class Item {
    private String name = null;
    private int price = 0;
    private Drawable image = null;
    public Item(String productName, int productPrice, String imageName) {
        name = productName;
        price = productPrice;

       /* int identifier = context.getResources().getIdentifier("imageName", "drawable", context.getPackageName());
        image = context.getResources().getDrawable(, null);*/

    }
    @Override
    public String toString() { return oneLine(" ","kr."); }
    public String getName() { return name; }
    public int getPrice() { return price; }
    public Drawable getImage() { return image; }

    public String oneLine(String pre, String post) {
        return name+pre + price+post;
    }
}
