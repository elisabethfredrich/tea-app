package com.example.itea;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.itea.Basket;
import com.example.itea.Item;

import java.util.List;
import java.util.List;
import java.util.Map;

public class BasketViewModel extends ViewModel {

        private static MutableLiveData<Basket> basket;

        public void initialize(){
            basket.getValue().initialize();
        }

        public BasketViewModel() {
            basket= new MutableLiveData<>();
            basket.setValue(new Basket());
        }

        public MutableLiveData<Basket> getValue() { return basket; }

        public void addItemToBasket(Item item){
            Basket temp= basket.getValue();
            temp.addItemToBasket(item);
            basket.setValue(temp);
        }

        public void removeItemFromBasket(Item item){
            Basket temp = basket.getValue();
            temp.removeItemFromBasket(item);
            basket.setValue(temp);
        }


        public Map<Item, Integer> getMap() {  return basket.getValue().getValues();  }
        public List<Item> getList() {  return basket.getValue().getList();  }



        public int size() { return basket.getValue().size(); }

        public int getPrice(){ return basket.getValue().getPrice();}
    }

