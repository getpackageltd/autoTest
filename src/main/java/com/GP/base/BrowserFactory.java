package com.GP.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {

    // Driver initialization
    public static WebDriver getDriver(String browser, Logger log) {
        WebDriver driver;
        log.info("Starting " + browser + " driver");

        switch (browser) {
            case "firefox":

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "chrome":

                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                options.addArguments("window-size=1200x600");
                driver = new ChromeDriver(options);

                //driver = new ChromeDriver();

                break;

            case "ie":

                WebDriverManager.edgedriver().setup();
                driver = new InternetExplorerDriver();
                break;

            default:

                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
        }

        driver.manage().window().maximize();

        return driver;
    }
}

