package com.example.itea;

import android.location.Location;
import android.util.Log;
import android.webkit.WebView;

public class httpThread implements Runnable {
    // to pass parameter to thread
    String dataUrl;
    WebView ui;
    Location start;

    public httpThread(WebView ui, Location start) {
        this.ui= ui;
        this.start= start;
    }

    public void run() {
        ui.post(new Runnable() {
            @Override
            public void run() {
                Location dest= TeaShopDB.get().findClosest(start).getLocation();
                //Log.i("***SR", start.toString());
                //Log.i("***DR", dest.toString());
                String mapUrl = "https://maps.google.com?saddr="+start.getLatitude()+","+start.getLongitude()+
                        "&daddr="+dest.getLatitude()+","+dest.getLongitude();
                ui.loadUrl(mapUrl);
            }
        });
    }
}

