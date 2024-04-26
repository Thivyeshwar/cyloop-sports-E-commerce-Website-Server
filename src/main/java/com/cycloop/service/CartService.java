package com.cycloop.service;

import com.cycloop.Exception.ProductException;
import com.cycloop.model.Cart;
import com.cycloop.model.User;
import com.cycloop.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);

	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;

	public Cart findUserCart(Long userId);

}
