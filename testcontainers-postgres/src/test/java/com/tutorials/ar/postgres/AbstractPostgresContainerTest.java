package com.tutorials.ar.postgres;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(initializers = {AbstractPostgresContainerTest.Initializer.class})
public class AbstractPostgresContainerTest {
    /**
     * H2 does not support all the features supported by postgres and for this reason the tests accuracy is affected.
     *
     * P.S H2 is more performant because it is in memory db
     * Use testcontainers only when you want to assure 100% database compatibility
     */
    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            PostgreSQLContainer postgreSQLContainer = PostgresContainerShared.getInstance();
            postgreSQLContainer.start();

            TestPropertyValues.of(
                    "spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
                    "spring.datasource.username=" + postgreSQLContainer.getUsername(),
                    "spring.datasource.password=" + postgreSQLContainer.getPassword()
            ).applyTo(configurableApplicationContext.getEnvironment());
        }
    }
}
