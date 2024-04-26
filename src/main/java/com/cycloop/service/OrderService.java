package com.cycloop.service;

import java.util.List;

import com.cycloop.Exception.OrderException;
import com.cycloop.model.Address;
import com.cycloop.model.Order;
import com.cycloop.model.User;

public interface OrderService {

	public Order createOrder(User user, Address shippingAddress);

	public Order findOrderById(Long orderId) throws OrderException;

	public List<Order> usersOrderHistory(Long userId);

	public Order placeOrder(Long orderId) throws OrderException;

	public Order confirmedOrder(Long orderId) throws OrderException;

	public Order shippedOrder(Long orderId) throws OrderException;

	public Order deliveredOrder(Long orderId) throws OrderException;

	public Order cancelOrder(Long orderId) throws OrderException;

	public List<Order> getAllOrders();

	public void deleteOrder(Long orderId) throws OrderException;

}
