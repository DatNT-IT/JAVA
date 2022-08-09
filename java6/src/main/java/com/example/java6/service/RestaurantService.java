package com.example.java6.service;

import com.example.java6.model.Restaurant;

import java.util.List;

public interface RestaurantService {

	List<Restaurant> getAllRestaurants();

	Restaurant getRestaurantById(Long id);

	Restaurant updateRestaurant(Restaurant restaurant);

	void deleteRestaurantById(Long id);

	Restaurant saveRestaurant(Restaurant restaurant);

}
