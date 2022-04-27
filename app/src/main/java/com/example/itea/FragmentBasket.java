package com.example.itea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class FragmentBasket extends Fragment {
    TextView text;
    BasketViewModel basket;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_basket, container, false);

        basket = new ViewModelProvider(requireActivity()).get(BasketViewModel.class);

        text = v.findViewById(R.id.fragment_name_text);
        text.setText(""+basket.size());

        return v;
    }
}
