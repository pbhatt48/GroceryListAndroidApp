package edu.gatech.seclass.glm.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.seclass.glm.models.GroceryList;

/**
 * Created by Bj on 10/2/16.
 */

public class ListManager {
    private static String TableName = "GroceryList";
    private static SQLiteDatabase db;

    // Retrieve a list of GroceryList
    public static List<GroceryList> getGroceryLists() {
        List<GroceryList> groceryLists = new ArrayList<>();
        db = DbHelper.Instance.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TableName + " ORDER BY Name", null);

        // Loop through the database
        while(cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("Id"));
            String name = cursor.getString(cursor.getColumnIndex("Name"));

            groceryLists.add(new GroceryList(id, name));
        }

        // Close the database connection
        cursor.close();

        return groceryLists;
    }

    // Add a new GroceryList
    public static GroceryList addGroceryList(String name) {
        db = DbHelper.Instance.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("Name", name);
        long id = db.insert(TableName, null, data);

        return new GroceryList((int)id, name);
    }

    // Rename a GroceryList
    public static void update(int id, String name) {
        db = DbHelper.Instance.getWritableDatabase();

        ContentValues data = new ContentValues();
        data.put("Name", name);
        db.update(TableName, data, "Id = " + id, null);
    }

    // Delete a GroceryList
    public static void delete(int id) {
        db = DbHelper.Instance.getWritableDatabase();
        db.delete("ItemInList", "GroceryListId = " + id, null);
        db.delete(TableName, "Id = " + id, null);
    }
}
