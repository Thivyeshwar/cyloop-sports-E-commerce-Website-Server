package com.cycloop.service;

import org.springframework.stereotype.Service;

import com.cycloop.Repository.OrderItemRepository;
import com.cycloop.model.OrderItem;

@Service
public class OrderItemServiceImplementation implements OrderItemService {

	private OrderItemRepository orderItemRepository;

	public OrderItemServiceImplementation(OrderItemRepository orderItemRepository) {
		this.orderItemRepository = orderItemRepository;
	}

	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {

		return orderItemRepository.save(orderItem);
	}

}
