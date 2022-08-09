package com.example.java6.service;

import com.example.java6.model.Customer;

import java.util.List;

public interface CustomerService {

	Customer updateCustomer(Customer customer);

	void deleteCustomerById(Long id);

	Customer saveCustomer(Customer customer);

	Customer getCustomerById(Long id);

	List<Customer> getAllCustomers();

}
