package com.example.fetch.model;

public class Item {
    private int listId;
    private int id;
    private String name;

    // Constructor
    public Item(int listId, int id, String name) {
        this.listId = listId;
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
