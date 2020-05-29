package com.example.appium.playground;

import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.Factory;
import io.appium.java_client.MobileElement;
import org.junit.*;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SampleTest {

    private EnhancedAndroidDriver<MobileElement> driver;

    // device name is ignored in Android for now
    private static final String DEVICE_NAME = "Nexus 4 API 29";
    private static final String PLATFORM_NAME = "Android";
    private static final String APP_PACKAGE = "com.rnappiumapp";
    private static final String APP_ACTIVITY = ".MainActivity";
    private static final String AUTOMATION_NAME = "UiAutomator2";
    private static final String URL_PATH = "http://127.0.0.1:4723/wd/hub";


    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    @Before
    public void setup() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", DEVICE_NAME);
        desiredCapabilities.setCapability("platformName", PLATFORM_NAME);
        desiredCapabilities.setCapability("appPackage", APP_PACKAGE);
        desiredCapabilities.setCapability("appActivity", APP_ACTIVITY);
        desiredCapabilities.setCapability("automationName", AUTOMATION_NAME);

        URL url = new URL(URL_PATH);
        driver = Factory.createAndroidDriver(url, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void TearDown(){
        driver.label("Stopping App");
        driver.quit();
    }

    @Test
    public void first_test() {
        driver.label("click firstButton");
        driver.findElementByAccessibilityId("firstButton").click();
        Assert.assertTrue(driver.findElementByAccessibilityId("firstScreenText").isDisplayed());
        driver.label("finished");
    }
}