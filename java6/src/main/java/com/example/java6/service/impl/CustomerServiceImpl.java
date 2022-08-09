package com.example.java6.service.impl;

import com.example.java6.model.Customer;
import com.example.java6.repository.CustomerRepository;
import com.example.java6.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
		
	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}
	
	@Override
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id).get();
	}
	
	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
	
	@Override
	public void deleteCustomerById(Long id) {
		customerRepository.deleteById(id);	
	}
	
	@Override
	public Customer updateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

 	

}
