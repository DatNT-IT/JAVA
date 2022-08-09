package com.example.java6.service.impl;

import com.example.java6.model.Order;
import com.example.java6.repository.OrderRepository;
import com.example.java6.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}
	
	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).get();
	}
	
	@Override
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
	@Override
	public void deleteOrderById(Long id) {
		orderRepository.deleteById(id);	
	}
	
	@Override
	public Order updateOrder(Order order) {
		return orderRepository.save(order);
	}
}
