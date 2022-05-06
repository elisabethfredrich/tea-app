package com.example.itea;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static BasketViewModel basket;
    private BadgeDrawable badge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //logo in top bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.logo, null);
        actionBar.setCustomView(view);

        // Find the NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // Find reference to bottom navigation view
        BottomNavigationView navView= findViewById(R.id.bottom_nav_view);
        // Hook navigation controller to bottom navigation view
        NavigationUI.setupWithNavController(navView, navController);

        //Initialising the basket
        basket = new ViewModelProvider(this).get(BasketViewModel.class);
        basket.initialize();

        //set up basket badge
        badge = navView.getOrCreateBadge(R.id.FragmentBasket);
        basket.getValue().observe(this, badge -> updateBadge());
    }


    public void updateBadge(){
        if(basket.totalSize()==0){
            badge.setVisible(false);
        }
        else{
            badge.setNumber(basket.totalSize());
            badge.setVisible(true);
        }

    }
}
