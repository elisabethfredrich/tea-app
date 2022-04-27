package com.example.itea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itea.database.BasketViewModel;

public class FragmentShopping extends Fragment {
    //TextView text;
    ItemsDB itemsDB;
    ItemAdapter mAdapter= new ItemAdapter();
    BasketViewModel basket;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_shopping, container, false);

      //  text = v.findViewById(R.id.fragment_name_text);
        itemsDB = new ItemsDB(getActivity());
        // Set up recyclerview
        RecyclerView itemList= v.findViewById(R.id.listItems);
        itemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemList.setAdapter(mAdapter);

        return v;
    }

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView name, price, addToBasket;

        public ItemHolder(View itemView) {
            super(itemView);

            name= itemView.findViewById(R.id.item_name);
            price= itemView.findViewById(R.id.item_price);
            addToBasket = itemView.findViewById(R.id.addToBasket);
            itemView.setOnClickListener(this);
        }

        public void bind(Item item, int position){
            name.setText(item.getName());
            price.setText(item.getPrice());
        }

        @Override
        public void onClick(View v) {
            String name= (String)((TextView)v.findViewById(R.id.item_name)).getText();
            basket.addItemToBasket(itemsDB.getItem(name));
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater= LayoutInflater.from(getActivity());
            View v= layoutInflater.inflate(R.layout.one_row, parent, false);
            return new ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            Item item=  itemsDB.getValues().get(position);
            holder.bind(item, position);
        }

        @Override
        public int getItemCount(){ return itemsDB.size(); }
    }
}
