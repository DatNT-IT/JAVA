package com.example.java6.service;

import com.example.java6.model.Shipper;

import java.util.List;

public interface ShipperService {

	Shipper updateShipper(Shipper shipper);

	void deleteShipperById(Long id);

	Shipper saveShipper(Shipper shipper);

	Shipper getShipperById(Long id);

	List<Shipper> getAllShippers();

}
