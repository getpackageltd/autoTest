package com.GP;

import com.GP.base.BaseTest;
import com.GP.pages.LogInPage;
import com.GP.pages.OPstgLogInPage;
import com.GP.pages.OperatorPortalPage;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GPAutomationTest extends BaseTest {
    LogInPage logInPage = null;
    OPstgLogInPage oPstgLogInPage = null;
    OperatorPortalPage operatorPortalPage = null;

    String userName = "";
    String password = "";
    String testNumber = "";

    @Parameters({ "no", "User Name", "Password" })
    @Test(description = "Set Tests Data Test", priority = 0)
    public void setTestData(String testNum, String userN, String psw) {

        testNumber = testNum;
        userName = userN;
        password = psw;
    }

    @Test(description = "Open Get package website", priority = 1, enabled = false) // , enabled = true
    public void openWebSite() throws Exception {

        if (logInPage == null) {
            logInPage = new LogInPage(driver, log);
        }

        // Open Get Package page https://stg-web.getpackage.com
        logInPage.openLogInPage();

        Reporter.log("Application Launched successfully ! ");
        logInPage.fillUpEmailAndPassword(userName, password);
        logInPage.pushSignInButton();
    }

    @Test(description = "Open Get package website", priority = 2) // , enabled = true
    public void openOPstgSite() throws Exception {

        if (oPstgLogInPage == null) {
            oPstgLogInPage = new OPstgLogInPage(driver, log);
        }

        // Open Get Package page https://frontend-stg.getpackage-dev.com
        oPstgLogInPage.openLogInPage();

        Reporter.log("Application Launched successfully ! ");
        oPstgLogInPage.fillUpEmailAndPassword(userName, password);
        oPstgLogInPage.pushSignInButton();
        oPstgLogInPage.verifyCorrectLogIn();
        Reporter.log("OP opened.");
    }

    @Test(description = "Log Out from Operator Portal", priority = 3) // , enabled = true
    public void logOut() throws Exception {

        if (operatorPortalPage == null) {
            operatorPortalPage = new OperatorPortalPage(driver, log);
        }

        // Log Out from Operator portal
        //Thread.sleep(10000);
        operatorPortalPage.logOut();
        oPstgLogInPage.verifyCorrectLogOut();
        Reporter.log("Log Out from Operator Portal");
    }
}
