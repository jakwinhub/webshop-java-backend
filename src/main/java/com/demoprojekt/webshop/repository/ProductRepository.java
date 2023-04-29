package com.demoprojekt.webshop.repository;

import com.demoprojekt.webshop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    @Query(value = "select p from ProductEntity p join fetch p.tags t where t = :tag")
    List<ProductEntity> findByTag(String tag);
}