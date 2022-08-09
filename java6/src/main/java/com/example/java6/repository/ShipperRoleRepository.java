package com.example.java6.repository;

import com.example.java6.model.Shipper;
import com.example.java6.model.ShipperRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipperRoleRepository extends JpaRepository<ShipperRole, Long>{
	List<ShipperRole> findByShipper(Shipper shipper);
}
