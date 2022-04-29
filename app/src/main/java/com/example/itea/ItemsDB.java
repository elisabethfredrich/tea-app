package com.example.itea;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.itea.database.ItemBaseHelper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.database.Cursor;

import androidx.lifecycle.ViewModel;

import com.example.itea.database.ItemCursorWrapper;
import com.example.itea.database.ItemsDBSchema;


public class ItemsDB {
    private static SQLiteDatabase mDatabase;

    public ItemsDB(Context context){
        if (mDatabase == null) {
            mDatabase= new ItemBaseHelper(context.getApplicationContext()).getWritableDatabase();
            if (getValues().size() == 0) fillItemsDB(context);
        }
    }

  /* public void initialize(Context context) {
       if (mDatabase == null) {
           mDatabase = new ItemBaseHelper(context.getApplicationContext()).getWritableDatabase();
           if (getValues().size() == 0) fillItemsDB(context);
       }
   }*/

    public void addItem(String name, int price){
            Item newItem= new Item(name, price);
            ContentValues values= getContentValues(newItem);
            mDatabase.insert(ItemsDBSchema.ItemTable.NAME, null, values);
    }


    public void fillItemsDB(Context context) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("products.txt")));
            String line = reader.readLine();
            while(line != null){
                String[] item = line.split(", ");
                addItem(item[0].toLowerCase(),Integer.parseInt(item[1]));
                line = reader.readLine();
            }
        }        catch(IOException e){
                //FIX LATER
        }}


    public int size() {
        return getValues().size();
    }


    public List<Item> getValues() {
        ArrayList<Item> items= new ArrayList<>();
        ItemCursorWrapper cursor= queryItems(null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            items.add(cursor.getItem());
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

    /*public Item getItem(String name){
        ArrayList<Item> items= new ArrayList<>();
        Item item = null;
        ItemCursorWrapper cursor= queryItems(null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(cursor.getItem().getName().equals(name)) {
                item = cursor.getItem();
            }
        }
        cursor.close();
        return item;
    }*/

    public Item getItem(String name){
        String selection = ItemsDBSchema.ItemTable.Cols.PRODUCT_NAME+ " = ?";
        String[] selectionArgs = { name.toLowerCase() };

        Item item = null;

        ItemCursorWrapper cursor= queryItems(selection, selectionArgs);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            item = cursor.getItem();
            cursor.moveToNext();
        }
        cursor.close();
        return item;
    }

    // Database helper methods to convert between Items and database rows
    private static ContentValues getContentValues(Item item) {
        ContentValues values=  new ContentValues();
        values.put(ItemsDBSchema.ItemTable.Cols.PRODUCT_NAME, item.getName());
        values.put(ItemsDBSchema.ItemTable.Cols.PRICE, item.getPrice());
        return values;
    }

    static private ItemCursorWrapper queryItems(String whereClause, String[] whereArgs) {
        Cursor cursor= mDatabase.query(
                ItemsDBSchema.ItemTable.NAME,
                null, // Columns - null selects all columns
                whereClause, whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new ItemCursorWrapper(cursor);
    }



}
