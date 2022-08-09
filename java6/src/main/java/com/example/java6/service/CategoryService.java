package com.example.java6.service;

import com.example.java6.model.Category;

import java.util.List;

public interface CategoryService {

	Category updateCategory(Category category);

	void deleteCategoryById(Long id);

	Category saveCategory(Category category);

	Category getCategoryById(Long id);

	List<Category> getAllCategories();

}
