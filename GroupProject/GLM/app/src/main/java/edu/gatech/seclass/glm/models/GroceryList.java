package edu.gatech.seclass.glm.models;

/**
 * Created by Bj on 10/8/16.
 */

public class GroceryList {
    private int id;
    private String name;

    public GroceryList(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
