package com.example.medimateserver.repository;

import com.example.medimateserver.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE " +
            "(:idCategory IS NULL OR p.category.id = :idCategory) AND " +
            "(:minPrice IS NULL OR p.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR p.price <= :maxPrice) AND " +
            "(:keySearch IS NULL OR p.name LIKE CONCAT('%', :keySearch, '%'))")
    List<Product> findWithFilter(
            @Param("idCategory") Integer idCategory,
            @Param("minPrice") Integer minPrice,
            @Param("maxPrice")Integer maxPrice,
            @Param("keySearch") String keySearch);

    @Query(value = "SELECT TOP 5 p.id, p.name, p.image, SUM(oi.quantity) AS total_sales "
            + "FROM product p "
            + "JOIN order_item oi ON p.id = oi.product_id "
            + "JOIN order o ON oi.order_id = o.id "
            + "GROUP BY p.id, p.name, p.image "
            + "ORDER BY total_sales DESC", nativeQuery = true)
    List<Product> findTop5BestSellingProducts();

}
