package com.example.appium.playground.global;

import com.example.appium.playground.enums.Platform;
import com.microsoft.appcenter.appium.Factory;
import io.appium.java_client.AppiumDriver;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTestFeature {
    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    public boolean onAndroid;
    public boolean oniOS;
    public AppiumDriver driver;
    public WebDriverWait wait;

    @Before
    public void beforeEachTest() {
        start();
    }

    public void start() {
        AppManager.startApp();
        AppManager.label("App Launched");

        onAndroid = AppManager.getPlatform() == Platform.ANDROID;
        oniOS = AppManager.getPlatform() == Platform.IOS;
        driver = AppManager.getAppiumDriver();
        wait = AppManager.getWaitDriver();
    }

}
