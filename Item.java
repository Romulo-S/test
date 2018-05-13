package com.example.analyst;

public class Item {

    private int itemID;
    private int quantity;
    private double itemPrice;

    public Item(int itemID, int quantity, double itemPrice) {
        this.itemID = itemID;
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public int getItemID() {

        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
}
