package com.tutorials.ar.postgres;

import com.tutorials.ar.postgres.model.CartEntity;
import com.tutorials.ar.postgres.repository.CartRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class CartRepositoryTest extends AbstractPostgresContainerTest {

    @Autowired
    private CartRepository cartRepository;

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
}
