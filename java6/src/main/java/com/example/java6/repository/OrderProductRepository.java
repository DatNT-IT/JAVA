package com.example.java6.repository;

import com.example.java6.model.Order;
import com.example.java6.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>{
	List<OrderProduct> findByOrder(Order order);
}
