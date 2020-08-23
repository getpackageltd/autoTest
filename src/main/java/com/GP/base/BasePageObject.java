package com.GP.base;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject<T> {
    protected WebDriver driver;
    protected Logger log;
    protected WebDriverWait wait;
    protected Actions action = null;

    protected  static String ERRORSSNAPSHOTFOLDER = "/Users/dan.perman/Documents/code/Automation_Demo_Test_Errors_SnapShot";

    protected BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
        wait = new WebDriverWait(driver, 40);
    }

    @SuppressWarnings("unchecked")
    protected T getPage(String url) {
        driver.get(url);
        return (T) this;
    }

    // Navigate back
    protected void goBack() {
        driver.navigate().back();
    }

    // Type text
    protected void type(String text, By element) {
        find(element).sendKeys(text);
    }

    // Clear text field
    protected void clearField(By element) {
        find(element).clear();
    }

    // Perform click
    protected void click(By element) {
        find(element).click();
    }

    // Select value from drop down
    protected void selectFromDropDownBy(final String value, final By element) {
        final Select dropdown = new Select(driver.findElement(element));
        dropdown.selectByValue(value);
    }

    protected Actions getAction() {
        if (action == null) {
            action = new Actions(driver);
        }
        return action;
    }

    // Perform double click
    protected void doubleClick(By element) {
        getAction().moveToElement(driver.findElement(element)).doubleClick().build().perform();
    }

    // Perform right click
    protected void rigthClick(By element) {
        getAction().contextClick(driver.findElement(element)).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();
    }

    // Perform click on Tab button
    protected void sendTabKey(By element) {
        find(element).sendKeys(Keys.TAB);
    }

    // Perform click on Enter button
    protected void sendEnterbKey(By element) {
        find(element).sendKeys(Keys.ENTER);
    }

    // Find element
    private WebElement find(By element) {
        return driver.findElement(element);
    }

    // Wait for visibility of element
    protected void waitForVisibilityOf(By locator, Integer... timeoutInSeconds) {
        int attempts = 0;
        while (attempts < 2) {
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeoutInSeconds.length > 0 ? timeoutInSeconds[0] : null));
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    // Wait for some condition
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeoutInSeconds) {
        timeoutInSeconds = timeoutInSeconds != null ? timeoutInSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.until(condition);
    }

    // Get page title
    public String getTitle() {
        return driver.getTitle();
    }

    // Get elements text
    protected String getText(By element) {
        return find(element).getText();
    }

    // Perform drag and drop
    public void dragAndDrop(By sourceElement, By destinationElement) {
        try {
            if (driver.findElement(sourceElement).isDisplayed() && driver.findElement(destinationElement).isDisplayed()) {
                getAction().dragAndDrop(driver.findElement(sourceElement), driver.findElement(destinationElement)).build().perform();
            } else {
                log.info("Element was not displayed to drag");
            }
        } catch (StaleElementReferenceException e) {
            log.info("Element with " + sourceElement + "or" + destinationElement
                    + "is not attached to the page document " + e.getStackTrace());
        } catch (NoSuchElementException e) {
            log.info("Element " + sourceElement + "or" + destinationElement + " was not found in DOM "
                    + e.getStackTrace());
        } catch (Exception e) {
            log.info("Error occurred while performing drag and drop operation " + e.getStackTrace());
        }
    }

    // Take snapshot
    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {

        // Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        // Call getScreenshotAs method to create image file

        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        // Move image file to new destination

        File DestFile = new File(fileWithPath);

        // Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);

    }

    // Get datetime
    protected String getCurrentDateTime() {
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


