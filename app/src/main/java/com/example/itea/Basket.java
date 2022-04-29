package com.example.itea;

import android.os.Build;
import androidx.lifecycle.ViewModel;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Basket extends ViewModel{
    private static HashMap<Item, Integer> basket;
    private int price;

    public void initialize(){
        if(basket == null){
            basket = new HashMap<>();
            price = 0;
        }
    }

    public void addItemToBasket(Item item){
        if(basket.containsKey(item)){
            int newValue = basket.get(item) + 1;
            basket.put(item, newValue);
        }
        else{
            basket.put(item, 1);
        }
        price += item.getPrice();
    }

    public void removeItemFromBasket(Item item){
        if(basket.get(item)>0){
            int newValue = basket.get(item) -1;
            basket.put(item, newValue);
        }
        else{
            basket.remove(item);
        }
    }

    public Map<Item, Integer> getValues(){
        return basket;
    }

    public List<Item> getList(){
        List<Item> result = new ArrayList<>();
        for(Item item: basket.keySet()){
            result.add(item);
        }
        return result;
    }

    public int size(){
        return basket.size();
    }

    public int getPrice(){
        return price;
    }


}
