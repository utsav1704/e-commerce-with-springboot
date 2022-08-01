package com.example.ecommerce.ECommerce.global;

import com.example.ecommerce.ECommerce.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> cart;
    static {
        cart = new ArrayList<>();
    }
}
