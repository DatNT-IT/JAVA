package com.example.java6.service;

import com.example.java6.model.Product;

import java.util.List;

public interface ProductService {

	Product updateProduct(Product product);

	void deleteProductById(Long id);

	Product saveProduct(Product product);

	Product getProductById(Long id);

	List<Product> getAllProducts();

}
