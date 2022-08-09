package com.example.java6.service.impl;

import com.example.java6.model.OrderProduct;
import com.example.java6.repository.OrderProductRepository;
import com.example.java6.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {
	@Autowired
	private OrderProductRepository orderProductRepository;
		
	@Override
	public List<OrderProduct> getAllOrderProducts() {
		return orderProductRepository.findAll();
	}
	
	@Override
	public OrderProduct getOrderProductById(Long id) {
		return orderProductRepository.findById(id).get();
	}
	
	@Override
	public OrderProduct saveOrderProduct(OrderProduct orderProduct) {
		return orderProductRepository.save(orderProduct);
	}
	
	@Override
	public void deleteOrderProductById(Long id) {
		orderProductRepository.deleteById(id);	
	}
	
	@Override
	public OrderProduct updateOrderProduct(OrderProduct OrderProduct) {
		return orderProductRepository.save(OrderProduct);
	}
}
