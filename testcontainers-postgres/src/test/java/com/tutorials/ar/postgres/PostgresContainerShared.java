package com.tutorials.ar.postgres;

import org.testcontainers.containers.PostgreSQLContainer;

public class PostgresContainerShared extends PostgreSQLContainer<PostgresContainerShared> {
    private static final String IMAGE_VERSION = "postgres:latest";
    private static PostgresContainerShared container;

    private PostgresContainerShared() {
        super(IMAGE_VERSION);
    }

    public static PostgresContainerShared getInstance() {
        if (container == null) {
            container = new PostgresContainerShared();
        }
        return container;
    }
}
