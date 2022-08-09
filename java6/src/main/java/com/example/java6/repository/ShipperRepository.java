package com.example.java6.repository;

import com.example.java6.model.Shipper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long>{
	Shipper findByEmail(String userName);
}
