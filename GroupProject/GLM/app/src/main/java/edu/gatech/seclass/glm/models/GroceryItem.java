package edu.gatech.seclass.glm.models;

/**
 * Created by Bj on 10/9/16.
 */

public class GroceryItem {
    private int id;
    private String type;
    private String name;
    private boolean checked;
    private int quantity;

    public GroceryItem(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public GroceryItem(int id, String type, String name, boolean checked, int quantity) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.checked = checked;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
