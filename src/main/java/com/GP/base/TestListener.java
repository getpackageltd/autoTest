package com.GP.base;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class TestListener extends TestListenerAdapter {

    private static final Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestSuccess(ITestResult tr) {
        Reporter.log(tr.getTestContext().getCurrentXmlTest().getName() + "Test Success.");
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        Reporter.log(tr.getTestContext().getCurrentXmlTest().getName() + "Test Failure.");
        String filePath = "/Users/dan.perman/Documents/code/Automation_Demo_Test_Errors_SnapShot"
                + tr.getTestContext().getCurrentXmlTest().getName() + "/";
        try {
            BaseTest.takeSnapShot(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(ITestContext testContext) {
        log.info(testContext.getCurrentXmlTest().getName() + "Test Started.");
    }

    @Override
    public void onFinish(ITestContext testContext) {
        log.info(testContext.getCurrentXmlTest().getName() + "Test Finished.");
    }
}
