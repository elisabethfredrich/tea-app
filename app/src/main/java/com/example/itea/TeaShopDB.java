package com.example.itea;

import android.location.Location;

public class TeaShopDB {
    //singleton class
    private static TeaShopDB sTeaShopDB;
    private TeaShop[] teaShops;

    private TeaShopDB()  {
        Location lCPH= new Location("Cph tea shop");
        lCPH.setLatitude(55.68074);
        lCPH.setLongitude(12.57897);
        TeaShop tCPH = new TeaShop(lCPH);

        Location lCPHairport= new Location("Cph airport tea shop");
        lCPHairport.setLatitude(55.66042);
        lCPHairport.setLongitude(12.64810);
        TeaShop tCPHairport = new TeaShop(lCPHairport);

        Location lViby= new Location("Viby tea shop");
        lViby.setLatitude(56.20160);
        lViby.setLongitude(10.20356);
        TeaShop tViby = new TeaShop(lViby);

        TeaShop[] temp = {tCPH,tCPHairport,tViby};
        teaShops = temp;
    }

    public static TeaShopDB get() {
        if (sTeaShopDB == null) sTeaShopDB= new TeaShopDB();
        return sTeaShopDB;
    }


    private double distance (TeaShop p1, Location p2) {
        return p1.getLocation().distanceTo(p2);
    }

    public TeaShop findClosest(Location target ) {
        double temp;
        if ((teaShops != null ) && (teaShops.length>0)) {
            int i= teaShops.length -1;
            TeaShop closest= teaShops[i];
            double min= distance(closest, target);
            while (i>0) {
                i= i-1;
                if (!teaShops[i].getmStreet().equals("null")) {
                    temp = distance(teaShops[i], target);
                    if (temp < min) {
                        closest = teaShops[i];
                        min = temp;
                    }
                }
            }
            return closest;
        }
        return null;
    }
}
