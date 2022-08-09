package com.example.java6.repository;

import com.example.java6.model.Restaurant;
import com.example.java6.model.RestaurantRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRoleRepository extends JpaRepository<RestaurantRole, Long>{
	List<RestaurantRole> findByRestaurant(Restaurant restaurant);
}
