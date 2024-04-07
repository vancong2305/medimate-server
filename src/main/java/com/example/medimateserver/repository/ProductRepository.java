package com.example.medimateserver.repository;

import com.example.medimateserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
            "(:keySearch IS NULL OR p.name LIKE CONCAT('%', :keySearch, '%'))")
    List<Product> findWithFilter(@Param("minPrice") Integer minPrice,
                                 @Param("maxPrice")Integer maxPrice,
                                 @Param("keySearch") String keySearch);
}
