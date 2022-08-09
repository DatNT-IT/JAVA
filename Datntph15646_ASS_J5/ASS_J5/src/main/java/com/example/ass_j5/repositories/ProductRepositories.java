package com.example.ass_j5.repositories;

import com.example.ass_j5.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface ProductRepositories extends JpaRepository<Product,String> {
    Product findTopByOrderByPriceDesc();
    @Query(value = "SELECT * FROM product  ORDER BY id OFFSET :index ROWS FETCH NEXT 3 ROWS ONLY",nativeQuery = true)
    List<Product> findByNext3(@Param("index") Integer index);
    @Query(value = "select top 3 * from  product where updelete =?1",nativeQuery = true)
    List<Product> findTop3ByupdeProduct(@Param("updelete") Integer updelete);
    @Query("SELECT u FROM product u WHERE u.updelete = :updelete")
    List<Product> findProductByUpdelete(@Param("updelete") Integer updelete);
    Iterable<Product> findByAccountAndUpdelete(String account,Integer updelete);
    Iterable<Product> findByCateAndUpdelete(String cate,Integer updelete);
    Product findByIdAndUpdelete(String Id,Integer updelete);
    List<Product> findByNameLikeAndUpdelete(String name,Integer updelete) ;
    Iterable<Product> findByCate(String categoryID);


}
