package com.example.itea;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentShopping extends Fragment {
    ItemsDB itemsDB;
    ItemAdapter mAdapter= new ItemAdapter();
    BasketViewModel basket;

   //Do we need this?
   /* @Override public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_shopping, container, false);

        //get the basket and the itemsDB
        basket = new ViewModelProvider(requireActivity()).get(BasketViewModel.class);
        itemsDB = new ItemsDB(getActivity());

        // Set up recyclerview
        RecyclerView itemList= v.findViewById(R.id.listItems);
        itemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemList.setAdapter(mAdapter);

        return v;
    }

    //not sure if we need this. Still works without it
    //public void onResume() {
    //    super.onResume();
    //   basket = new ViewModelProvider(requireActivity()).get(BasketViewModel.class);
    //   basket.getValue().observe(getViewLifecycleOwner(), i -> mAdapter.notifyDataSetChanged());
    //}

    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView nameTextView, priceTextView;
        private ImageButton addToBasketButton;
        private ImageView productImage;

        public ItemHolder(View itemView) {
            super(itemView);
            nameTextView= itemView.findViewById(R.id.item_name);
            priceTextView= itemView.findViewById(R.id.item_price);
            productImage = itemView.findViewById(R.id.tea_img);
            addToBasketButton = itemView.findViewById(R.id.addToBasket);

            addToBasketButton.setOnClickListener(this);
        }

        public void bind(Item item, int position){
        //    productImage.setImageDrawable(item.getImage());
            nameTextView.setText(item.getName());
            priceTextView.setText(item.getPrice()+" kr.");

            String uri = "@drawable/"+item.getImage();
            int imageResource = getResources().getIdentifier(uri, null, "com.example.itea");
            Drawable res = getResources().getDrawable(imageResource);
            productImage.setImageDrawable(res);
            //productImage.setImageResource(imageResource);
        }

        @Override
        public void onClick(View v) {
       //     ImageView itemImage = itemView.findViewById(R.id.tea_img);
            String itemName= (String)((TextView)itemView.findViewById(R.id.item_name)).getText();
            int itemPrice= Integer.parseInt(((String)((TextView)itemView.findViewById(R.id.item_price)).getText()).split("\\s")[0]);
            basket.addItemToBasket(itemName,itemPrice);

            Toast.makeText(getActivity(), itemName + " succesfully added to basket", Toast.LENGTH_LONG).show();
        }}

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater= LayoutInflater.from(getActivity());
            View v= layoutInflater.inflate(R.layout.one_row_shopping, parent, false);
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
