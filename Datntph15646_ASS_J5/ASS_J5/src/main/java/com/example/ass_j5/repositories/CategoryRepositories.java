package com.example.ass_j5.repositories;

import com.example.ass_j5.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepositories extends JpaRepository<Category,String> {
    @Query("SELECT u FROM Cate u WHERE u.updelete = :updelete")
    List<Category> findCategoryByUpdelete(@Param("updelete") Integer updelete);


}
