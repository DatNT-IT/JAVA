package com.example.java6.service;

import com.example.java6.model.Order;

import java.util.List;

public interface OrderService {

	Order updateOrder(Order order);

	void deleteOrderById(Long id);

	Order saveOrder(Order order);

	Order getOrderById(Long id);

	List<Order> getAllOrders();

}
