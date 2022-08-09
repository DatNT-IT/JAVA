package com.example.ass_j5.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoriesTest {
    @Autowired
    private ProductRepositories productRepositories;
    @Test
    void findByNext3() {
        System.out.println(productRepositories.findTop3ByupdeProduct(1));
    }

    @Test
    void findTop3ByupdeProduct() {
    }
}