package com.example.java6.service.impl;

import com.example.java6.model.ShipperRole;
import com.example.java6.repository.ShipperRoleRepository;
import com.example.java6.service.ShipperRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipperRoleServiceImpl implements ShipperRoleService {
	@Autowired
	private ShipperRoleRepository shipperRoleRepository;
	
	@Override
	public List<ShipperRole> getAllShipperRoles() {
		return shipperRoleRepository.findAll();
	}
	
	@Override
	public ShipperRole getShipperRoleById(Long id) {
		return shipperRoleRepository.findById(id).get();
	}
	
	@Override
	public ShipperRole saveShipperRole(ShipperRole shipperRole) {
		return shipperRoleRepository.save(shipperRole);
	}
	
	@Override
	public void deleteShipperRoleById(Long id) {
		shipperRoleRepository.deleteById(id);	
	}
	
	@Override
	public ShipperRole updateShipperRole(ShipperRole shipperRole) {
		return shipperRoleRepository.save(shipperRole);
	}
}
