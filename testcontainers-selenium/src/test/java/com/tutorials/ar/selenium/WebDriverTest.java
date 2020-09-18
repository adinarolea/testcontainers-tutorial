package com.tutorials.ar.selenium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.BrowserWebDriverContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ContextConfiguration(initializers = WebDriverTest.Initializer.class)
public class WebDriverTest {

    @LocalServerPort
    private int localPort;

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            applicationContext.addApplicationListener((ApplicationListener<WebServerInitializedEvent>) event -> {
                org.testcontainers.Testcontainers.exposeHostPorts(event.getWebServer().getPort());
            });
        }
    }

    @Container
    public BrowserWebDriverContainer<?> chromeContainer = new BrowserWebDriverContainer<>()
            .withCapabilities(new ChromeOptions())
            .withRecordingMode(BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL, new File("/tmp/result"));

    private static final String HOST = "host.testcontainers.internal";

    @Test
    @DisplayName("Test can access the application on home")
    public void whenHomeIsAccessed_thenRespondWithPage() throws InterruptedException {
        RemoteWebDriver chromeDriver = this.chromeContainer.getWebDriver();
        chromeDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        String url = "http://" + HOST + ":" + localPort + "/";
        chromeDriver.get(url);
        WebElement element = chromeDriver.findElement(By.id("main-element"));
        assertThat(element.getText()).isEqualTo("Hello there!");
        Thread.sleep(1000);
        chromeDriver.findElement(By.id("link-element")).click();
        Thread.sleep(1000);
        assertThat(chromeDriver.getTitle()).isEqualTo("Testcontainers");
    }
}
