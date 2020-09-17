package com.tutorials.ar.toxiproxy;

import com.tutorials.ar.toxiproxy.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.ToxiproxyContainer;
import org.testcontainers.elasticsearch.ElasticsearchContainer;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(initializers = {ElasticsearchConnectionTest.Initializer.class})
public class ElasticsearchConnectionTest {

    private static final String TOXIPROXY_NETWORK_ALIAS = "toxiproxy";
    private static ToxiproxyContainer.ContainerProxy proxy;

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            Network network = Network.newNetwork();
            ElasticsearchContainer elasticsearchContainer = new ElasticsearchContainer()
                    .withExposedPorts(9200, 9300)
                    .withNetwork(network);
            elasticsearchContainer.start();
            ToxiproxyContainer toxiproxy = new ToxiproxyContainer("shopify/toxiproxy:2.1.0")
                    .withNetwork(network)
                    .withNetworkAliases(TOXIPROXY_NETWORK_ALIAS);
            toxiproxy.start();

            proxy = toxiproxy.getProxy(elasticsearchContainer, elasticsearchContainer.getMappedPort(9300));
            System.setProperty("elasticsearch.host", elasticsearchContainer.getHttpHostAddress());
            System.setProperty("elasticsearch.port", String.valueOf(elasticsearchContainer.getMappedPort(9300)));
        }
    }

    @Autowired
    MovieService movieService;

    @Test
    public void whenConnectionIsAvailable_thenConnect() {
        // proxy.setConnectionCut(true);
        movieService.getAllMovies();
        // proxy.setConnectionCut(false);
    }
}
