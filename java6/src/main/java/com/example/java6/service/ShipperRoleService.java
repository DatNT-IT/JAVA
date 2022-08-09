package com.example.java6.service;

import com.example.java6.model.ShipperRole;

import java.util.List;

public interface ShipperRoleService {

	ShipperRole updateShipperRole(ShipperRole shipperRole);

	void deleteShipperRoleById(Long id);

	ShipperRole saveShipperRole(ShipperRole shipperRole);

	ShipperRole getShipperRoleById(Long id);

	List<ShipperRole> getAllShipperRoles();

}
