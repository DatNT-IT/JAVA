package com.example.ass_j5.controller;

import com.example.ass_j5.entity.Category;
import com.example.ass_j5.repositories.CategoryRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//http:localhost:8080/categories
public class CategoryController {
    @Autowired
    private CategoryRepositories categoryRepositories;

    //http:localhost:8083/categories
    @RequestMapping(value = "categories", method = RequestMethod.GET)
    public String getAllCategories(ModelMap modelMap) {
        Iterable<Category> categories = categoryRepositories.findCategoryByUpdelete(1);
        modelMap.addAttribute("categories", categories);
        //
        return "category";
    }

}