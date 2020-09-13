package com.tutorials.ar.selenium;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testcontainers.containers.BrowserWebDriverContainer;

import java.io.File;

import static org.testcontainers.containers.BrowserWebDriverContainer.VncRecordingMode.RECORD_ALL;

public class WebDriverContainer extends BrowserWebDriverContainer<WebDriverContainer> {
    private static BrowserWebDriverContainer container;

    public static BrowserWebDriverContainer getInstance() {
        if (container == null) {
            File resultDir = new File("/tmp/result");
            container = new BrowserWebDriverContainer()
                    .withCapabilities(new ChromeOptions())
                    .withRecordingMode(RECORD_ALL, resultDir);
        }
        return container;
    }
}
