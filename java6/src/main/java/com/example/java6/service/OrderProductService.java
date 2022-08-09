package com.example.java6.service;

import com.example.java6.model.OrderProduct;

import java.util.List;

public interface OrderProductService {

	OrderProduct updateOrderProduct(OrderProduct OrderProduct);

	void deleteOrderProductById(Long id);

	OrderProduct saveOrderProduct(OrderProduct orderProduct);

	OrderProduct getOrderProductById(Long id);

	List<OrderProduct> getAllOrderProducts();

}
