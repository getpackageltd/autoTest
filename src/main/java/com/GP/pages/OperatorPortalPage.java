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
    private By deliveriesTab = By.cssSelector("body > gp-root > gp-app > gp-app-frame > mat-sidenav-container > mat-sidenav > div > gp-sidebar > div > nav > ul:nth-child(1) > li:nth-child(1) > a > div.gp-icon-wrapper");
    private By routesTab = By.cssSelector("body > gp-root > gp-app > gp-app-frame > mat-sidenav-container > mat-sidenav > div > gp-sidebar > div > nav > ul:nth-child(1) > li:nth-child(2) > a > div.gp-icon-wrapper > img");
    private By excludedTab = By.cssSelector("body > gp-root > gp-app > gp-app-frame > mat-sidenav-container > mat-sidenav > div > gp-sidebar > div > nav > ul:nth-child(1) > li:nth-child(3) > a > div.gp-icon-wrapper > img");

    // Log Out from Operator portal
    public void logOut() throws Exception {
        waitForVisibilityOf(logOutButton,3);
        log.info("Clicking on Logout Button");
        click(logOutButton);
    }

    // Click on Deliveries tab
    public void clickOnDeliveriesTab(){
        waitForVisibilityOf(deliveriesTab,3);
        log.info("Clicking on Deliveries Tab");
        click(deliveriesTab);
    }

    // Click on Deliveries tab
    public void clickOnRoutesTab(){
        waitForVisibilityOf(routesTab,3);
        log.info("Clicking on Routes Tab");
        click(routesTab);
    }

    // Click on Deliveries tab
    public void clickOnExcludedTab(){
        waitForVisibilityOf(excludedTab,3);
        log.info("Clicking on Excluded Tab");
        click(excludedTab);
    }
}
