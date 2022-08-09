package com.example.java6.service.impl;

import com.example.java6.model.CustomerRole;
import com.example.java6.repository.CustomerRoleRepository;
import com.example.java6.service.CustomerRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerRoleServiceImpl implements CustomerRoleService {
	@Autowired
	private CustomerRoleRepository customerRoleRepository;
	
	@Override
	public List<CustomerRole> getAllCustomerRoles() {
		return customerRoleRepository.findAll();
	}
	
	@Override
	public CustomerRole getCustomerRoleById(Long id) {
		return customerRoleRepository.findById(id).get();
	}
	
	@Override
	public CustomerRole saveCustomerRole(CustomerRole customerRole) {
		return customerRoleRepository.save(customerRole);
	}
	
	@Override
	public void deleteCustomerRoleById(Long id) {
		customerRoleRepository.deleteById(id);	
	}
	
	@Override
	public CustomerRole updateCustomerRole(CustomerRole customerRole) {
		return customerRoleRepository.save(customerRole);
	}
}
