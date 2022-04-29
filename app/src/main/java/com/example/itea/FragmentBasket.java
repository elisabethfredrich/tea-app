package com.example.itea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentBasket extends Fragment {
    BasketViewModel basket;
    ItemAdapter mAdapter= new ItemAdapter();
    TextView introText, totalPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_basket, container, false);

        basket = new ViewModelProvider(requireActivity()).get(BasketViewModel.class);

        // Set up recyclerview
        RecyclerView itemList= v.findViewById(R.id.listItems);
        itemList.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemList.setAdapter(mAdapter);

        basket.getValue().observe(getActivity(), itemsDB -> mAdapter.notifyDataSetChanged());


        introText = v.findViewById(R.id.introText);
        totalPrice = v.findViewById(R.id.price);

        if(basket.size()>0){
            introText.setText("Your basket contains:");
            totalPrice.setText("Total price: "+basket.getPrice() + " kr.");
        }
        else{
            introText.setText("Your basket is empty.");
            totalPrice.setText("");
        }


        return v;
    }

    public void onResume() {
        super.onResume();
        basket = new ViewModelProvider(requireActivity()).get(BasketViewModel.class);
        basket.getValue().observe(getViewLifecycleOwner(), i -> mAdapter.notifyDataSetChanged());
    }


    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView mNameTextView, mPriceTextView, mAmountTextView;
        private final ImageButton mRemoveButton;

        public ItemHolder(View itemView) {
            super(itemView);

            mNameTextView= itemView.findViewById(R.id.item_name);
            mPriceTextView= itemView.findViewById(R.id.item_price);
            mAmountTextView = itemView.findViewById(R.id.item_amount);
            mRemoveButton = itemView.findViewById(R.id.removeFromBasket);

            mRemoveButton.setOnClickListener(this);

        }

        public void bind(Item item, int position, int amount){
            mNameTextView.setText(item.getName());
            mPriceTextView.setText(item.getPrice() +" kr.");
            mAmountTextView.setText("x"+amount);
        }

        @Override
        public void onClick(View v) {
            String itemName= (String)((TextView)itemView.findViewById(R.id.item_name)).getText();
            //Item item = //how do we get the item from a string?
            //basket.removeItemFromBasket(item);
        }


    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater= LayoutInflater.from(getActivity());
            View v= layoutInflater.inflate(R.layout.one_row_basket, parent, false);
            return new ItemHolder(v);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            Item item=  basket.getList().get(position);
            int amount = basket.getMap().get(item);
            holder.bind(item, position, amount);
        }

        @Override
        public int getItemCount(){ return basket.size(); }
    }
}
