package com.example.java6.service.impl;

import com.example.java6.model.RestaurantRole;
import com.example.java6.repository.RestaurantRoleRepository;
import com.example.java6.service.RestaurantRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantRoleServiceImpl implements RestaurantRoleService {
	@Autowired
	private RestaurantRoleRepository restaurantRoleRepository;
	
	@Override
	public List<RestaurantRole> getAllRestaurantRoles() {
		return restaurantRoleRepository.findAll();
	}
	
	@Override
	public RestaurantRole getRestaurantRoleById(Long id) {
		return restaurantRoleRepository.findById(id).get();
	}
	
	@Override
	public RestaurantRole saveRestaurantRole(RestaurantRole restaurantRole) {
		return restaurantRoleRepository.save(restaurantRole);
	}
	
	@Override
	public void deleteRestaurantRoleById(Long id) {
		restaurantRoleRepository.deleteById(id);	
	}
	
	@Override
	public RestaurantRole updateRestaurantRole(RestaurantRole restaurantRole) {
		return restaurantRoleRepository.save(restaurantRole);
	}
}
