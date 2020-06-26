package com.example.appium.playground.tests;

import com.example.appium.playground.global.AppManager;
import com.example.appium.playground.global.BaseTestFeature;
import com.example.appium.playground.global.PropertiesConfig;
import com.example.appium.playground.pages.FirstPage;
import com.microsoft.appcenter.appium.Factory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;

public class FirstTest extends BaseTestFeature {
    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    //gerek yok gibi
    @Override
    public void beforeEachTest() {
        start();
    }

    @After
    public void TearDown() {
        driver.quit();
    }

    @Test
    public void navigateToSecondPage() {
        new FirstPage().clickFirstButton();

        AppManager.label("####SELECTED ENV:" +System.getenv("TEST_PLATFORM"));
        AppManager.label("####SELECTED PLATFORM:" + PropertiesConfig.getInstance().getPlatform());

        Assert.assertTrue(driver.findElementByAccessibilityId("firstScreenText").isDisplayed());
    }
}
