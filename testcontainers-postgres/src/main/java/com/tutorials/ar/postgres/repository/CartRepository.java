package com.tutorials.ar.postgres.repository;

import com.tutorials.ar.postgres.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<CartEntity, Long> {

    Optional<CartEntity> findByCode(String code);
}
