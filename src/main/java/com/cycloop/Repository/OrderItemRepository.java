package com.cycloop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cycloop.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
