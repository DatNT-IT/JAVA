package com.example.java6.service.impl;

import com.example.java6.model.Category;
import com.example.java6.repository.CategoryRepository;
import com.example.java6.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;


	@Override
	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}
	
	@Override
	public Category getCategoryById(Long id) {
		return categoryRepository.findById(id).get();
	}
	
	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	@Override
	public void deleteCategoryById(Long id) {
		categoryRepository.deleteById(id);	
	}
	
	@Override
	public Category updateCategory(Category category) {
		return categoryRepository.save(category);
	}

}
