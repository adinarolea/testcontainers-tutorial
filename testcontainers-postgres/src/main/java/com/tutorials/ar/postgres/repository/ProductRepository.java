package com.tutorials.ar.postgres.repository;

import com.tutorials.ar.postgres.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
