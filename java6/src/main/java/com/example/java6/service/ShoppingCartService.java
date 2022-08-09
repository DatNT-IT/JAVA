package com.example.java6.service;

import java.util.Collection;

import com.example.java6.model.CartItem;


public interface ShoppingCartService {

	void add(CartItem item);

	Collection<CartItem> getCartItem();

	double getAmount();

	int getCount();

	void update(Long productId, int quantity);

	void clear();

	void remove(Long productId);

}
