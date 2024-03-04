package com.example.fetch.model;

public class ListItem {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_ITEM = 1;
    private int type;
    private Item item;
    private String header;

    public ListItem(Item item) {
        this.type = TYPE_ITEM;
        this.item = item;
    }

    public ListItem(String header) {
        this.type = TYPE_HEADER;
        this.header = header;
    }

    public int getType() {
        return type;
    }

    public Item getItem() {
        return item;
    }

    public String getHeader() {
        return header;
    }
}

