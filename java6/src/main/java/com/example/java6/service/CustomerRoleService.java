package com.example.java6.service;

import com.example.java6.model.CustomerRole;

import java.util.List;

public interface CustomerRoleService {

	CustomerRole updateCustomerRole(CustomerRole customerRole);

	void deleteCustomerRoleById(Long id);

	CustomerRole saveCustomerRole(CustomerRole customerRole);

	CustomerRole getCustomerRoleById(Long id);

	List<CustomerRole> getAllCustomerRoles();

}
