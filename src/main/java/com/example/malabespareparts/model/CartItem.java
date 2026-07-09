package com.example.malabespareparts.model;

public class CartItem {

    private part part;
    private int quantity;
    private double subtotal;

    public CartItem(part part, int quantity, double subtotal) {
        this.part = part;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public part getPart() {
        return part;
    }

    public void setPart(part part) {
        this.part = part;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}
