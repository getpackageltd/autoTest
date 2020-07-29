package com.GP.base;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log;
    static protected WebDriver sanpDriver = null;

    @Parameters({ "browser" })
    @BeforeTest
    protected void setUpTest(ITestContext ctx,String browser) {
        String testName = ctx.getCurrentXmlTest().getName();
        log = Logger.getLogger(testName);
        driver = BrowserFactory.getDriver(browser, log);
        sanpDriver = driver;
        log.info("================================================================================");
        log.info("Starting New Test");
        log.info("================================================================================");
    }

    @AfterTest
    protected void tearDownTest() {
        // driver.quit();
        if (driver != null) {
            driver.close();
            driver.quit();
        }
        log.info("================================================================================");
        log.info("Test Ended.");
        log.info("================================================================================");
    }

    // Take snapshot
    public static void takeSnapShot(String fileWithPath) throws Exception {
        takeSnapShot(sanpDriver, fileWithPath);
    }

    /**
     *
     * This function will take screenshot
     *
     * @param webdriver
     *
     * @param fileWithPath
     *
     * @throws Exception
     *
     */

    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
        if (webdriver != null) {
            // Convert web driver object to TakeScreenshot
            TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

            // Call getScreenshotAs method to create image file

            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

            // Move image file to new destination

            File DestFile = new File(fileWithPath + getCurrentDateTime() + ".png");

            // Copy file at destination

            FileUtils.copyFile(SrcFile, DestFile);
        }

    }

    // Get datetime
    protected static String getCurrentDateTime() {
        String dateFormatted = null;
        SimpleDateFormat fmt = new SimpleDateFormat("dd_MMM_yyyy_HH_mm_ss");
        Date date = GregorianCalendar.getInstance().getTime();
        try {
            dateFormatted = fmt.format(date);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return dateFormatted;

    }

}

