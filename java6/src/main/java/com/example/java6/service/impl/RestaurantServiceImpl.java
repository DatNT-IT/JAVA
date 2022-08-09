package com.example.java6.service.impl;

import com.example.java6.model.Restaurant;
import com.example.java6.repository.RestaurantRepository;
import com.example.java6.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Override
	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}
	
	@Override
	public Restaurant getRestaurantById(Long id) {
		return restaurantRepository.findById(id).get();
	}
	
	@Override
	public Restaurant saveRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}
	
	@Override
	public void deleteRestaurantById(Long id) {
		restaurantRepository.deleteById(id);	
	}
	
	@Override
	public Restaurant updateRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}


}
