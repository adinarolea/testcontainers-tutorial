package com.tutorials.ar.mockserver.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DefaultExternalService implements ExternalService {

    private RestTemplate restTemplate = new RestTemplate();

    @Value("${external.server.url}")
    private String serverUrl;

    @Override
    public String getResponse() {
        return restTemplate.getForEntity(serverUrl + "/test", String.class)
                .getBody();
    }
}
