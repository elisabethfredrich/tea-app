package com.example.itea;

import android.os.Build;
import android.widget.ImageView;

import androidx.lifecycle.ViewModel;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket extends ViewModel{
    private static HashMap<String, Integer[]> basket; //the integer[] contains the price and the amount
    private int totalPrice;
    private int countSum;

    public void initialize(){
        if(basket == null){
            basket = new HashMap<>();
            totalPrice = 0;
        }
    }

    public void addItemToBasket(String itemName, int itemPrice){
        if(basket.containsKey(itemName)){
            Integer[] newValue = basket.get(itemName);
            newValue[1] += 1; //increase amount with +1
            basket.put(itemName, newValue);
        }
        else{
            Integer[] value = {itemPrice,1}; //new entry has values price and 1 amount
            basket.put(itemName,value);
        }
        totalPrice += itemPrice;
        countSum++;
    }

    public void removeItemFromBasket(String itemName){
        Integer[] itemValues = basket.get(itemName);
        int itemPrice = itemValues[0];
        int itemAmount = itemValues[1];
        if(itemAmount > 1){
            itemAmount -= 1; //decrease amount with 1
            itemValues[1] = itemAmount;
            basket.put(itemName, itemValues);
        }
        else{
            basket.remove(itemName);
        }
        totalPrice -= itemPrice;
        countSum--;
    }

    public Map<String, Integer[]> getValues(){
        return basket;
    }

    public List<String> getList(){
        List<String> result = new ArrayList<>();
        for(String itemName: basket.keySet()){
            result.add(itemName);
        }
        return result;
    }

    public int size(){
        return basket.size();
    }

    public int totalSize(){return countSum;}

    public int getPrice(){
        return totalPrice;
    }


}
