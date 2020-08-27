package com.GP;

import com.GP.base.BaseTest;
import com.GP.pages.OPstgLogInPage;
import com.GP.pages.OperatorPortalPage;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OPSanityTest extends BaseTest {
    OPstgLogInPage oPstgLogInPagePOs = null;
    OperatorPortalPage operatorPortalPagePos = null;

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


    @Test(description = "LogIn to Operator Portal", priority = 1) // , enabled = true
    public void openOPstgSite() throws Exception {

        if (oPstgLogInPagePOs == null) {
            oPstgLogInPagePOs = new OPstgLogInPage(driver, log);
        }

        // Open Operator Portal LogIn Page https://frontend-stg.getpackage-dev.com
        oPstgLogInPagePOs.openLogInPage();

        Reporter.log("Application Launched successfully ! ");
        oPstgLogInPagePOs.fillUpEmailAndPassword(userName, password);
        oPstgLogInPagePOs.pushSignInButton();
        oPstgLogInPagePOs.verifyCorrectLogIn();
        Reporter.log("OP opened.");
    }


    @Test(description = "Select Deliveries Tab", priority = 2) // , enabled = true
    public void selectDeliveriesTab() throws Exception {

        if (operatorPortalPagePos == null) {
            operatorPortalPagePos = new OperatorPortalPage(driver, log);
        }

        // Select Deliveries Tab
        operatorPortalPagePos.clickOnDeliveriesTab();
        Reporter.log("Deliveries Tab selected");
    }

    @Test(description = "Select Routes Tab", priority = 3) // , enabled = true
    public void selectRoutesTab() throws Exception {

        if (operatorPortalPagePos == null) {
            operatorPortalPagePos = new OperatorPortalPage(driver, log);
        }

        // Select Routes Tab
        operatorPortalPagePos.clickOnRoutesTab();
        Reporter.log("Routes Tab selected");
    }

    @Test(description = "Select Excluded Tab", priority = 4) // , enabled = true
    public void selectExcludedTab() throws Exception {

        if (operatorPortalPagePos == null) {
            operatorPortalPagePos = new OperatorPortalPage(driver, log);
        }

        // Select Excluded Tab
        operatorPortalPagePos.clickOnExcludedTab();
        Reporter.log("Excluded Tab selected");
    }

    @Test(description = "Return to Deliveries Tab", priority = 5) // , enabled = true
    public void returnToDeliveriesTab() throws Exception {

        if (operatorPortalPagePos == null) {
            operatorPortalPagePos = new OperatorPortalPage(driver, log);
        }

        // Select Deliveries Tab
        operatorPortalPagePos.clickOnDeliveriesTab();
        Reporter.log("Deliveries Tab selected");
    }

    @Test(description = "Log Out from Operator Portal", priority = 6) // , enabled = true
    public void logOut() throws Exception {

        if (operatorPortalPagePos == null) {
            operatorPortalPagePos = new OperatorPortalPage(driver, log);
        }

        // Log Out from Operator portal
        operatorPortalPagePos.logOut();
        oPstgLogInPagePOs.verifyCorrectLogOut();
        Reporter.log("Log Out from Operator Portal");
    }
}
