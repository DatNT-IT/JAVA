package com.example.java6.repository;

import com.example.java6.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
	Restaurant findByEmail(String userName);
}
