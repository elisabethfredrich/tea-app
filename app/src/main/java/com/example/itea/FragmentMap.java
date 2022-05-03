package com.example.itea;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;
import android.location.Location;

import androidx.fragment.app.Fragment;

public class FragmentMap extends Fragment {
    TextView text;
    private WebView mWeb;
    private TeaShopDB teaShopDB;

    private Location lITU= new Location("ITU");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);

        text = v.findViewById(R.id.fragment_name_text);

        //Set Coordinates for ITU
        lITU.setLatitude(55.659886);
        lITU.setLongitude(12.591235);


        mWeb = v.findViewById(R.id.webpage);
        mWeb.getSettings().setJavaScriptEnabled(true);

        // closest tea shop to ITU:
        Location closest=findTeaShop(lITU);
        startBrowser(lITU, closest);

        return v;
    }

    private void startBrowser(Location start, Location dest) {
        String url = "https://maps.google.com?saddr=" + start.getLatitude() + "," + start.getLongitude() +
                "&daddr=" + dest.getLatitude() + "," + dest.getLongitude();
        mWeb.loadUrl(url);
    }

    private Location findTeaShop(Location l){
        teaShopDB= teaShopDB.get();
        return teaShopDB.findClosest(l).getLocation();
    }
}
