package com.example.java6.service;

import com.example.java6.model.RestaurantRole;

import java.util.List;

public interface RestaurantRoleService {

	RestaurantRole updateRestaurantRole(RestaurantRole restaurantRole);

	void deleteRestaurantRoleById(Long id);

	RestaurantRole saveRestaurantRole(RestaurantRole restaurantRole);

	RestaurantRole getRestaurantRoleById(Long id);

	List<RestaurantRole> getAllRestaurantRoles();

}
