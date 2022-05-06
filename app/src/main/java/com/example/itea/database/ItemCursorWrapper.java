package com.example.itea.database;

import android.database.Cursor;
import android.database.CursorWrapper;
import com.example.itea.Item;

public class ItemCursorWrapper extends CursorWrapper {
    public ItemCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Item getItem() {
        String productName = getString(getColumnIndex(ItemsDBSchema.ItemTable.Cols.PRODUCT_NAME));
        int productPrice = getInt(getColumnIndex(ItemsDBSchema.ItemTable.Cols.PRICE));
        String productImg = getString(getColumnIndex(ItemsDBSchema.ItemTable.Cols.IMG));
        return new Item(productName, productPrice, productImg);
    }
}