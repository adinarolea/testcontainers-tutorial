package com.tutorials.ar.mockserver;

import com.tutorials.ar.mockserver.service.ExternalService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({SpringExtension.class, ContainerInitializer.class})
@SpringBootTest
public class ExternalServiceTest {

    @Autowired
    ExternalService externalService;

    @Test
    @DisplayName("Test get from server")
    public void whenGetFromServer_thenReceiveStringResponse() {
        String response = externalService.getResponse();
        assertThat(response).isEqualTo("Hello there!");
    }
}
