package com.tutorials.ar.postgres;

import com.tutorials.ar.postgres.model.CartEntity;
import com.tutorials.ar.postgres.model.ProductEntity;
import com.tutorials.ar.postgres.repository.CartRepository;
import com.tutorials.ar.postgres.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
public class CartRepositoryTest extends AbstractPostgresContainerTest {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    public void cleanup() {
        productRepository.deleteAll();
        cartRepository.deleteAll();
    }

    @Test
    @DisplayName("When insert -> then select it")
    public void whenInsertCart_thenCartIsAvailable() {
        CartEntity cartEntity = CartEntity.builder()
                .code("1234")
                .build();
        cartRepository.save(cartEntity);
        assertThat(cartRepository.findByCode("1234"))
                .isPresent();
    }

    @Test
    @DisplayName("When insert cart with products -> then M2M relationship is set")
    public void whenCartHasProducts_thenReturnThem() {
        CartEntity cartEntity = CartEntity.builder()
                .code("1234")
                .build();
        ProductEntity productEntity = ProductEntity.builder()
                .code("123")
                .name("Water")
                .price(BigDecimal.TEN)
                .build();
        productRepository.save(productEntity);

        cartEntity.getProducts().add(productEntity);
        cartRepository.save(cartEntity);

        CartEntity cart = cartRepository.findByCode("1234").get();
        assertThat(cart.getProducts())
                .hasSize(1);
    }
}
