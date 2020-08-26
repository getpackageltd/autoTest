package com.GP.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.GP.base.BasePageObject;
import com.GP.base.PropirtiesTest;
import org.testng.Assert;

public class OperatorPortalPage extends BasePageObject<OperatorPortalPage> {
    public OperatorPortalPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    private By logOutButton = By.xpath("/html/body/gp-root/gp-app/gp-app-frame/mat-sidenav-container/mat-sidenav/div/gp-sidebar/div/nav/ul[2]/li/a/div[1]/img");

    // Log Out from Operator portal
    public void logOut() throws Exception {
        waitForVisibilityOf(logOutButton,3);
        log.info("Clicking on Logout Button");
        click(logOutButton);
    }
}
