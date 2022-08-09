package com.example.java6.service.impl;

import com.example.java6.model.Product;
import com.example.java6.repository.ProductRepository;
import com.example.java6.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id).get();
	}
	
	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	@Override
	public void deleteProductById(Long id) {
		productRepository.deleteById(id);	
	}
	
	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}
}
