package com.example.java6.service.impl;

import com.example.java6.model.Discount;
import com.example.java6.repository.DiscountRepository;
import com.example.java6.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
	
	@Autowired
	private DiscountRepository discountRepository;
	
	@Override
	public List<Discount> getAllDiscounts() {
		return discountRepository.findAll();
	}
	
	@Override
	public Discount getDiscountById(Long id) {
		return discountRepository.findById(id).get();
	}
	
	@Override
	public Discount saveDiscount(Discount discount) {
		return discountRepository.save(discount);
	}
	
	@Override
	public void deleteDiscountById(Long id) {
		discountRepository.deleteById(id);	
	}
	
	@Override
	public Discount updateDiscount(Discount discount) {
		return discountRepository.save(discount);
	}
}
