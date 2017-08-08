package edu.gatech.seclass.glm.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.glm.models.GroceryItem;

/**
 * Created by Bj on 10/2/16.
 */

public class ItemManager {
    private static String TableName = "ItemInList";
    private static SQLiteDatabase db;

    // Search items (type and name)
    public static List<GroceryItem> searchItems(String search) {
        db = DbHelper.Instance.getWritableDatabase();
        List<GroceryItem> groceryItems = new ArrayList<>();

        String query = "SELECT * FROM Item ";

        if (!search.isEmpty()) {
            query += "WHERE Type LIKE '%" + search + "%' OR Name LIKE '%" + search + "%' ";
        }

        query += "ORDER BY Type, Name LIMIT 50";

        Cursor cursor = db.rawQuery(query, null);
        while(cursor.moveToNext()) {
            int itemId = cursor.getInt(cursor.getColumnIndex("Id"));
            String type = cursor.getString(cursor.getColumnIndex("Type"));
            String name = cursor.getString(cursor.getColumnIndex("Name"));

            groceryItems.add(new GroceryItem(itemId, type, name));
        }

        return groceryItems;
    }

    public static List<String> getTypes() {
        db = DbHelper.Instance.getWritableDatabase();
        List<String> types = new ArrayList<>();

        String query = "SELECT DISTINCT Type FROM Item ORDER BY Type";

        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()) {
            types.add(cursor.getString(cursor.getColumnIndex("Type")));
        }

        // Close the database connection
        cursor.close();

        return types;
    }

    // Retrieve a list of GroceryItem
    public static List<GroceryItem> getItemsInList(int id) {
        db = DbHelper.Instance.getWritableDatabase();
        List<GroceryItem> groceryItems = new ArrayList<>();

        String query = "SELECT ItemInList.Id, Name, Type, Quantity, Checked " +
                "FROM ItemInList INNER JOIN Item ON Item.Id = ItemId " +
                "WHERE GroceryListId = " + id + " ORDER BY Type, Name";

        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()) {
            int itemId = cursor.getInt(cursor.getColumnIndex("Id"));
            String type = cursor.getString(cursor.getColumnIndex("Type"));
            String name = cursor.getString(cursor.getColumnIndex("Name"));
            boolean checked = cursor.getInt(cursor.getColumnIndex("Checked")) == 1;
            int quantity = cursor.getInt(cursor.getColumnIndex("Quantity"));

            groceryItems.add(new GroceryItem(itemId, type, name, checked, quantity));
        }

        // Close the database connection
        cursor.close();

        return groceryItems;
    }

    // Toggle all items in a list
    public static void toggleList(int id, boolean checked) {
        db = DbHelper.Instance.getWritableDatabase();

        ContentValues data = new ContentValues();
        data.put("Checked", checked);
        db.update(TableName, data, "GroceryListId = " + id, null);
    }

    // Create an item
    public static long createItem(String type, String name) {
        db = DbHelper.Instance.getWritableDatabase();

        // Check if item already exists
        String query = "SELECT * FROM Item WHERE Type = '" + type + "' AND Name = '" + name + "'";
        if (db.rawQuery(query, null).getCount() > 0) {
            return -1;
        }

        ContentValues data = new ContentValues();
        data.put("Type", type);
        data.put("Name", name);

        return db.insert("Item", null, data);
    }

    // Add an item to a list
    public static int addToList(int groceryListId, int itemId) {
        db = DbHelper.Instance.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("ItemId", itemId);
        data.put("Checked", 0);
        data.put("Quantity", 1);
        data.put("GroceryListId", groceryListId);
        long id = db.insert(TableName, null, data);

        return (int)id;
    }

    // Update a GroceryItem quantity or toggle
    public static void update(int id, boolean checked, int quantity) {
        db = DbHelper.Instance.getWritableDatabase();

        ContentValues data = new ContentValues();
        data.put("Checked", checked);
        data.put("Quantity", quantity);
        db.update(TableName, data, "Id = " + id, null);
    }

    // Delete a GroceryItem (ItemInList)
    public static void delete(int id) {
        db = DbHelper.Instance.getWritableDatabase();
        db.delete(TableName, "Id = " + id, null);
    }
}
