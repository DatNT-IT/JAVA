package com.example.java6.service.impl;

import com.example.java6.model.Shipper;
import com.example.java6.repository.ShipperRepository;
import com.example.java6.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipperServiceImpl implements ShipperService {
	
	@Autowired
	private ShipperRepository shipperRepository;
	
	
	@Override
	public List<Shipper> getAllShippers() {
		return shipperRepository.findAll();
	}
	
	@Override
	public Shipper getShipperById(Long id) {
		return shipperRepository.findById(id).get();
	}
	
	@Override
	public Shipper saveShipper(Shipper shipper) {
		return shipperRepository.save(shipper);
	}
	
	@Override
	public void deleteShipperById(Long id) {
		shipperRepository.deleteById(id);	
	}
	
	@Override
	public Shipper updateShipper(Shipper shipper) {
		return shipperRepository.save(shipper);
	}
}
