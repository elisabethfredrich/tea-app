package com.example.itea;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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

        //when the content of the basket changes, we notify the ItemAdapter and updates the shown price
        basket.getValue().observe(getViewLifecycleOwner(), basket -> {
            mAdapter.notifyDataSetChanged();
            updateBasketText();
                }
        );

        introText = v.findViewById(R.id.introText);
        totalPrice = v.findViewById(R.id.price);

        updateBasketText();

        return v;
    }


    private void updateBasketText(){
        if(basket.size()>0){
            introText.setText("Your basket contains:");
            totalPrice.setText("Total price: "+basket.getPrice() + " kr.");
        }
        else{
            introText.setText("Your basket is empty.");
            totalPrice.setText("");
        }
    }


    private class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private final TextView mNameTextView, mPriceTextView, mAmountTextView;
        private final ImageButton mRemoveButton;
        private ImageView mProductImage;
        private ItemsDB itemsDB = new ItemsDB(getActivity());



        public ItemHolder(View itemView) {
            super(itemView);
            mProductImage=itemView.findViewById(R.id.tea_img);
            mNameTextView= itemView.findViewById(R.id.item_name);
            mPriceTextView= itemView.findViewById(R.id.item_price);
            mAmountTextView = itemView.findViewById(R.id.item_amount);
            mRemoveButton = itemView.findViewById(R.id.removeFromBasket);

            mRemoveButton.setOnClickListener(this);

        }

        public void bind(String itemName, int position, int itemPrice, int itemAmount){
            mNameTextView.setText(itemName);
            mPriceTextView.setText(itemPrice + " kr.");
            mAmountTextView.setText("x"+itemAmount);

            String imageName = itemName.split(" ")[0].toLowerCase();
            String uri = "@drawable/"+imageName;
            int imageResource = getResources().getIdentifier(uri, null, "com.example.itea");
            Drawable res = getResources().getDrawable(imageResource);
            mProductImage.setImageDrawable(res);
        }

        @Override
        public void onClick(View v) {
            String itemName= (String)((TextView)itemView.findViewById(R.id.item_name)).getText();
            basket.removeItemFromBasket(itemName);
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
            String itemName=  basket.getList().get(position);
            Integer[] itemValues = basket.getMap().get(itemName);
            int itemPrice= itemValues[0];
            int itemAmount = itemValues[1];
            holder.bind(itemName, position, itemPrice, itemAmount);
        }

        @Override
        public int getItemCount(){ return basket.size(); }
    }
}
