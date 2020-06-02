package com.example.appium.playground.pages;

import com.example.appium.playground.global.BasePage;
import com.example.appium.playground.global.PlatformQuery;
import com.microsoft.appcenter.appium.Factory;
import io.appium.java_client.MobileBy;
import org.junit.Rule;
import org.junit.rules.TestWatcher;

public class FirstPage extends BasePage {
    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    @Override
    public PlatformQuery trait() {
        return new PlatformQuery()
                .setAndroid(MobileBy.AccessibilityId("firstButton"))
                .setIOS(MobileBy.AccessibilityId("firstButton"));
    }

    public FirstPage clickFirstButton() {
        driver.findElementByAccessibilityId("firstButton").click();
        label("FirstButton clicked");
        return this;
    }

}
