package com.example.java6.repository;

import com.example.java6.model.Customer;
import com.example.java6.model.CustomerRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface CustomerRoleRepository extends JpaRepository<CustomerRole, Long>{
	List<CustomerRole> findByCustomer(Customer customer);
}
