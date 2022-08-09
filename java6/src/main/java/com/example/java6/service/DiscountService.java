package com.example.java6.service;

import com.example.java6.model.Discount;

import java.util.List;

public interface DiscountService {

	Discount updateDiscount(Discount discount);

	void deleteDiscountById(Long id);

	Discount saveDiscount(Discount discount);

	Discount getDiscountById(Long id);

	List<Discount> getAllDiscounts();

}
