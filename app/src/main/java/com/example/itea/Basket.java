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

    public void initialize(){
        if(basket == null){
            basket = new HashMap<>();
        }
    }

    public void addItemToBasket(Item item){
        if(basket.containsKey(item)){
            int newValue = basket.get(item) + 1;
                //basket.remove(item); //dont need this as put updates the value
                basket.put(item, newValue);
            }
        else{
            basket.put(item, 1);
        }
    }

    public Map<Item, Integer> getValues(){
        return basket;
    }

    public int size(){
        return basket.size();
    }


}
