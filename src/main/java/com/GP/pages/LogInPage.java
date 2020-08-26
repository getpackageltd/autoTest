package com.GP.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.GP.base.BasePageObject;
import com.GP.base.PropirtiesTest;

public class LogInPage extends BasePageObject<LogInPage> {

    private By emailField = By.id("login_email_field");
    private By passwordField = By.id("password_field");
    private By signInButton = By.id("sign_in_email_btn");

    String GetPackageURL = "";


    public LogInPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public synchronized void openLogInPage() throws Exception {

        GetPackageURL = PropirtiesTest.getInstance().getProperty("LogInURL");
        getPage(GetPackageURL);
        waitForVisibilityOf(signInButton,5);
        //Thread.sleep(5000);
    }

    public synchronized void fillUpEmailAndPassword(String email, String password) throws Exception {

        log.info("Filling up email and password");
        type(email, emailField);
        type(password, passwordField);
    }

    public void pushSignInButton() throws Exception {

        log.info("Clicking on Log In Button");
        click(signInButton);
    }

}
