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

    @Test(description = "Log Out from Operator Portal", priority = 2) // , enabled = true
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
