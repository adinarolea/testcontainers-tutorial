package com.tutorials.ar.mockserver;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockserver.client.MockServerClient;
import org.testcontainers.containers.MockServerContainer;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

public class ContainerInitializer implements BeforeAllCallback {

    public static MockServerContainer mockServer = new MockServerContainer();

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        mockServer.start();
        System.setProperty("external.server.url", "http://" + mockServer.getHost() + ":" + mockServer.getServerPort());
        new MockServerClient(mockServer.getHost(), mockServer.getServerPort())
                .when(request()
                        .withPath("/test"))
                .respond(response()
                        .withHeader("Content-Type", "application/json")
                        .withStatusCode(200)
                        .withBody("Hello there!"));
    }
}