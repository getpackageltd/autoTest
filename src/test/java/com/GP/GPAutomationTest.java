package com.GP;

import com.GP.base.BaseTest;
import com.GP.pages.LogInPage;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GPAutomationTest extends BaseTest {
    LogInPage logInPage = null;

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

    @Test(description = "Open Get package website", priority = 1) // , enabled = true
    public void openWebSite() throws Exception {

        if (logInPage == null) {
            logInPage = new LogInPage(driver, log);
        }

        // Open Get Package page https://stg-web.getpackage.com
        logInPage.openLogInPage();

        Reporter.log("Application Lauched successfully ! ");
        logInPage.fillUpEmailAndPassword(userName, password);
        logInPage.pushSignInButton();
    }
}
