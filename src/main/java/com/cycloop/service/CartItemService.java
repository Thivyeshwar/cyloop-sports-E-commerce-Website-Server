package com.cycloop.service;

import com.cycloop.Exception.CartItemException;
import com.cycloop.Exception.UserException;
import com.cycloop.model.Cart;
import com.cycloop.model.CartItem;
import com.cycloop.model.Product;

public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);

	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;

	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);

	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;

	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
