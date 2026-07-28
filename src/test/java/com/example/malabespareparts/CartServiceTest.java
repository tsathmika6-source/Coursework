package com.example.malabespareparts;

import com.example.malabespareparts.model.CartItem;
import com.example.malabespareparts.model.Part;
import com.example.malabespareparts.service.CartService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CartServiceTest {

    @Test
    void testAddItem() {

        CartService service = new CartService();

        Part part = new Part(
                "P001",
                "Brake Pad",
                "Toyota",
                1000.0,
                2,
                "Brakes",
                "2026-07-01",
                "test.jpg"
        );

        CartItem item = new CartItem(part, 2);

        service.addItem(item);

        assertEquals(1, service.getItemCount());
    }

    @Test
    void testGetTotalAmount() {

        CartService service = new CartService();

        Part part = new Part(
                "P001",
                "Brake Pad",
                "Toyota",
                1000.0,
                2,
                "Brakes",
                "2026-07-01",
                "test.jpg"
        );

        CartItem item = new CartItem(part, 2);

        service.addItem(item);

        assertEquals(2000.0, service.getTotalAmount());
    }

    @Test
    void testClearCart() {

        CartService service = new CartService();

        Part part = new Part(
                "P001",
                "Brake Pad",
                "Toyota",
                1000.0,
                2,
                "Brakes",
                "2026-07-01",
                "test.jpg"
        );

        CartItem item = new CartItem(part, 2);

        service.addItem(item);

        service.clearCart();

        assertEquals(0, service.getItemCount());
    }
}