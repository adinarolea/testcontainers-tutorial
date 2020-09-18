package com.tutorials.ar.localstack;

import com.amazonaws.services.s3.AmazonS3;
import com.tutorials.ar.localstack.config.S3LocalstackConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

public abstract class AbstractTest {

    private static final Integer LOCALSTACK_PORT = 4572;
    private static final GenericContainer container;

    protected static AmazonS3 amazonS3;

    static {

        /**
         * with generic container we would have something like this:
         */

//        container = new GenericContainer("localstack/localstack:latest")
//                .withExposedPorts(LOCALSTACK_PORT)
//                .withEnv("SERVICES", "s3")
//                .withLogConsumer(new Slf4jLogConsumer(LoggerFactory.getLogger(AbstractTest.class)));


        container = new LocalStackContainer()
                .withServices(LocalStackContainer.Service.S3)
                .withExposedPorts(LOCALSTACK_PORT);

        container.start();

        /**
         * localstack is started on a random port and we must always call getMappedPort after the container is started
         */
        amazonS3 = new S3LocalstackConfig().amazonS3("us-east-1",
                "http://localhost:" + container.getMappedPort(LOCALSTACK_PORT));
    }
}
