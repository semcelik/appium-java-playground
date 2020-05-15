package com.example.appium.playground;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SampleTest {

    private AndroidDriver<MobileElement> driver;

    // device name is ignored in Android for now
    private static final String DEVICE_NAME = "Nexus 4 API 29";
    private static final String PLATFORM_NAME = "Android";
    private static final String APP_PACKAGE = "com.rnappiumapp";
    private static final String APP_ACTIVITY = ".MainActivity";
    private static final String AUTOMATION_NAME = "UiAutomator2";
    private static final String URL_PATH = "http://127.0.0.1:4723/wd/hub";


    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", DEVICE_NAME);
        desiredCapabilities.setCapability("platformName", PLATFORM_NAME);
        desiredCapabilities.setCapability("appPackage", APP_PACKAGE);
        desiredCapabilities.setCapability("appActivity", APP_ACTIVITY);
        desiredCapabilities.setCapability("automationName", AUTOMATION_NAME);

        URL url = new URL(URL_PATH);
        driver = new AndroidDriver(url, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void first_test() {
        driver.findElementByAccessibilityId("firstButton").click();
    }
}