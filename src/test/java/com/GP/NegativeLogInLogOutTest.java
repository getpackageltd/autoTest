package com.GP;
import com.GP.base.BaseTest;
import com.GP.pages.OPstgLogInPage;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeLogInLogOutTest extends BaseTest{

    OPstgLogInPage oPstgLogInPageNeg = null;

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


    @Test(description = "Open Get Package Operator Portal LogIn Page", priority = 1) // , enabled = true
    public void openOPstgSite() throws Exception {

        if (oPstgLogInPageNeg == null) {
            oPstgLogInPageNeg = new OPstgLogInPage(driver, log);
        }

        // Open Get Package page https://frontend-stg.getpackage-dev.com
        oPstgLogInPageNeg.openLogInPage();

        Reporter.log("Application Launched successfully ! ");

        //Reporter.log("OP opened.");
    }

    @Test(description = "LogIn to Operator Portal WithOut email ==> Error presented", priority = 2) // , enabled = true
    public void logInWithOutEmail() throws Exception{

        if (oPstgLogInPageNeg == null) {
            oPstgLogInPageNeg = new OPstgLogInPage(driver, log);
        }

        oPstgLogInPageNeg.fillUpEmailAndPassword("", password);
        oPstgLogInPageNeg.pushSignInButton();
        oPstgLogInPageNeg.verifyNoEmailErrorPresented();
        Reporter.log("Error presented: "+oPstgLogInPageNeg.returnErrorMessageText());
    }

    @Test(description = "LogIn to Operator Portal WithOut password ==> Error presented", priority = 3) // , enabled = true
    public void logInWithOutPassword() throws Exception{

        if (oPstgLogInPageNeg == null) {
            oPstgLogInPageNeg = new OPstgLogInPage(driver, log);
        }

        oPstgLogInPageNeg.reRefreshPage();
        Thread.sleep(1000);
        oPstgLogInPageNeg.fillUpEmailAndPassword(userName, "");
        oPstgLogInPageNeg.pushSignInButton();
        oPstgLogInPageNeg.verifyNoPasswordErrorPresented();
        Reporter.log("Error presented: "+oPstgLogInPageNeg.returnErrorMessageText());
    }

    @Test(description = "LogIn to Operator Portal With wrong username ==> Error presented", priority = 5) // , enabled = true
    public void logInWithWrongUserName() throws Exception{

        if (oPstgLogInPageNeg == null) {
            oPstgLogInPageNeg = new OPstgLogInPage(driver, log);
        }

        oPstgLogInPageNeg.reRefreshPage();
        Thread.sleep(1000);
        oPstgLogInPageNeg.fillUpEmailAndPassword(userName+"q", password);
        oPstgLogInPageNeg.pushSignInButton();
        oPstgLogInPageNeg.verifyWrongEmailOrPasswordErrorPresented();
        Reporter.log("Error presented: "+oPstgLogInPageNeg.returnErrorMessageText());
    }

    @Test(description = "LogIn to Operator Portal With wrong password ==> Error presented", priority = 4) // , enabled = true
    public void logInWithWrongPassword() throws Exception{

        if (oPstgLogInPageNeg == null) {
            oPstgLogInPageNeg = new OPstgLogInPage(driver, log);
        }

        oPstgLogInPageNeg.reRefreshPage();
        Thread.sleep(1000);
        oPstgLogInPageNeg.fillUpEmailAndPassword(userName, password+"q");
        oPstgLogInPageNeg.pushSignInButton();
        oPstgLogInPageNeg.verifyWrongEmailOrPasswordErrorPresented();
        Reporter.log("Error presented: "+oPstgLogInPageNeg.returnErrorMessageText());
    }
}
