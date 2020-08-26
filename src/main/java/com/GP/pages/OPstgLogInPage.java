package com.GP.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.GP.base.BasePageObject;
import com.GP.base.PropirtiesTest;
import org.testng.Assert;

public class OPstgLogInPage extends BasePageObject<OPstgLogInPage> {

    public OPstgLogInPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By emailField = By.className("gp-email");
    private By passwordField = By.className("gp-password");
    private By signInButton = By.className("gp-login-btn");
    private By errorMessage = By.className("error-message");

    String OPstgURL = "";

    // Open LogIn page
    public synchronized void openLogInPage() throws Exception {
        OPstgURL = PropirtiesTest.getInstance().getProperty("OPstgLogInURL");
        getPage(OPstgURL);
        waitForVisibilityOf(signInButton,5);
        //Thread.sleep(5000);
    }

    // Fill Username and password
    public synchronized void fillUpEmailAndPassword(String email, String password) throws Exception {
        log.info("Filling up email and password");
        clearField(emailField);
        type(email, emailField);
        clearField(passwordField);
        type(password, passwordField);
    }

    // Click on submit button
    public void pushSignInButton() throws Exception {
        log.info("Clicking on Log In Button");
        click(signInButton);
    }

    // Verify Operator portal presented
    public void verifyCorrectLogIn(){
        String ActualTitle = getTitle();
        String ExpectedTitle = "GetPackage | Operator Portal";
        Assert.assertEquals(ActualTitle, ExpectedTitle);
    }

    // Verify LogIn page presented
    public void verifyCorrectLogOut(){
        waitForVisibilityOf(emailField,5);
    }

    // Verify Error Message email may not be blank presented
    public void verifyNoEmailErrorPresented() throws InterruptedException {
        //waitForVisibilityOf(errorMessage,5);
        Thread.sleep(1000);
        String errorMessageText = getText(errorMessage);
        String expectedErrorMessageText = "Legacy: {\"email\":[\"This field may not be blank.\"]}";
        Assert.assertEquals(errorMessageText, expectedErrorMessageText);
    }

    // Verify Error Message password may not be blank presented
    public void verifyNoPasswordErrorPresented() throws InterruptedException {
        Thread.sleep(1000);
        String pErrorMessageText = getText(errorMessage);
        String pExpectedErrorMessageText = "Legacy: {\"password\":[\"This field may not be blank.\"]}";
        Assert.assertEquals(pErrorMessageText, pExpectedErrorMessageText);
    }

    // Verify Error Message password may not be blank presented
    public void verifyWrongEmailOrPasswordErrorPresented() throws InterruptedException {
        Thread.sleep(1000);
        String wErrorMessageText = getText(errorMessage);
        String wExpectedErrorMessageText = "Legacy: Email or password are wrong";
        Assert.assertEquals(wErrorMessageText, wExpectedErrorMessageText);
    }

    // Return Error Message
    public String returnErrorMessageText(){
        return getText(errorMessage);
    }

    // Refresh page
    public void reRefreshPage(){
        refreshPage();
    }
}
