package com.example.kithub.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findByProductName(String name);

    @Query(value = "SELECT p.* FROM Product p JOIN category c ON p.category = c.id WHERE " +
            "(:nameOrDescription IS NULL OR LOWER(p.product_name) LIKE LOWER('%'||:nameOrDescription||'%') OR LOWER(p.description) LIKE LOWER('%'||:nameOrDescription||'%')) AND " +
            "(:region IS NULL OR :region = ANY(p.regions)) AND " +
            "(:category IS NULL OR LOWER(c.category_name) = LOWER(:category)) AND " +
            "(:supplier IS NULL OR LOWER(p.supplier) LIKE LOWER('%'||:supplier||'%'))", nativeQuery = true)
    List<Product> findProductsByMatchingCriteria(
            @Param("nameOrDescription") String nameOrDescription,
            @Param("region") String region,
            @Param("category") String category,
            @Param("supplier") String supplier
    );
}
