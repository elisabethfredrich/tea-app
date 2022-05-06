package com.example.itea;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
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

        public void addItemToBasket(String itemName, int itemPrice){
            Basket temp= basket.getValue();
            temp.addItemToBasket(itemName, itemPrice);
            basket.setValue(temp);
        }

        public void removeItemFromBasket(String itemName){
            Basket temp = basket.getValue();
            temp.removeItemFromBasket(itemName);
            basket.setValue(temp);
        }


        public Map<String, Integer[]> getMap() {  return basket.getValue().getValues();  }

        public List<String> getList() {  return basket.getValue().getList();  }

        public int size() { return basket.getValue().size(); }

        public int totalSize(){return basket.getValue().totalSize();}

        public int getPrice(){ return basket.getValue().getPrice();}
    }

