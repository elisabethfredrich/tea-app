package com.example.itea;

import android.location.Location;

public class TeaShop {
    private Location mLoc;  // GPS position
    private String mStreet; //Street name

    public TeaShop(Location loc, String mStreet) {
        mLoc= loc;
        setmStreet(mStreet);
    }

    public TeaShop(Location loc) {mLoc= loc; setmStreet("  ");}

    public String getmStreet() {return mStreet; }

    public void setmStreet(String mStreet) {
        if (mStreet!=null) this.mStreet = mStreet;
        else this.mStreet="No name";
    }

    public Location getLocation() { return mLoc; }

    @Override
    public String toString() {return mStreet + " la= " + mLoc.getLatitude() + ", lo= " + mLoc.getLongitude(); }
}