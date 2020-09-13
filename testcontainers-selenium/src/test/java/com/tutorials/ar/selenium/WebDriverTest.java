package com.tutorials.ar.selenium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.BrowserWebDriverContainer;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {WebDriverTest.Initializer.class})
public class WebDriverTest {

    @LocalServerPort
    private int localPort;

    private static RemoteWebDriver chromeDriver;

    private static final String HOST = "172.17.0.1";

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            BrowserWebDriverContainer browserWebDriverContainer = WebDriverContainer.getInstance();
            browserWebDriverContainer.start();
            chromeDriver = browserWebDriverContainer.getWebDriver();
        }
    }

    @Test
    @DisplayName("Test can access the application on home")
    public void whenHomeIsAccessed_thenRespondWithPage() {
        String url = "http://" + HOST + ":" + localPort + "/";
        chromeDriver.get(url);
        WebElement element = chromeDriver.findElement(By.id("main-element"));
        assertThat(element.getText()).isEqualTo("Hello there!");
    }
}
