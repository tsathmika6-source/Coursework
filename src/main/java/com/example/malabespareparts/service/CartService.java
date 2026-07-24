package com.example.malabespareparts.service;

import com.example.malabespareparts.model.CartItem;

import java.util.ArrayList;

public class CartService {

    private  final ArrayList<CartItem> cartItems;

    public CartService() {
        cartItems=new ArrayList<>();
    }

    public void addItem(CartItem item){
        cartItems.add(item);
    }

    public void removeItem(CartItem item){
        cartItems.remove(item);
    }

    public ArrayList<CartItem> getCartItems(){
        return cartItems;
    }

    public  double getTotalAmount(){
        double total=0;

        for(CartItem item:cartItems){
            total+=item.getSubtotal();
        }
        return  total;
    }

    public  void  clearCart(){
        cartItems.clear();
    }

    public  int getItemCount(){
        return cartItems.size();
    }
}
