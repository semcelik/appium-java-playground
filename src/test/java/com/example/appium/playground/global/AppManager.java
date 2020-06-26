package com.example.appium.playground.global;

import com.example.appium.playground.enums.Platform;
import com.microsoft.appcenter.appium.EnhancedAndroidDriver;
import com.microsoft.appcenter.appium.EnhancedIOSDriver;
import com.microsoft.appcenter.appium.Factory;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppManager {
    public final static int SHORT_TIMEOUT = 5;
    public final static int LONG_TIMEOUT = 30;
    private static final String URL_PATH = "http://127.0.0.1:4723/wd/hub";
    // device name is ignored in Android for now
    private static final String ANDROID_DEVICE_NAME = "Nexus 4 API 29";
    private static final String ANDROID_PLATFORM_NAME = "Android";
    private static final String ANDROID_APP_PACKAGE = "com.rnappiumapp";
    private static final String ANDROID_APP_ACTIVITY = ".MainActivity";
    private static final String ANDROID_AUTOMATION_NAME = "UiAutomator2";

    private static Platform platform;
    private static EnhancedAndroidDriver<MobileElement> androidDriver;
    private static EnhancedIOSDriver<MobileElement> iOSDriver;
    private static WebDriverWait waitDriver;

    @Rule
    public TestWatcher watcher = Factory.createWatcher();

    public static Platform getPlatform() {
        if (platform != null) {
            return platform;
        }
        throw new RuntimeException("You must call AppManager.startApp() before using the platform");
    }

    public static AppiumDriver getAppiumDriver() {
        if (platform == Platform.ANDROID && androidDriver != null) {
            return androidDriver;
        }

        if (platform == Platform.IOS && iOSDriver != null) {
            return iOSDriver;
        }

        throw new RuntimeException("You must call AppManager.startApp() before using the driver");
    }

    public static WebDriverWait getWaitDriver() {
        if ((platform == Platform.ANDROID && androidDriver != null)
                || (platform == Platform.IOS && iOSDriver != null)) {
            return waitDriver;
        }

        throw new RuntimeException("You must call AppManager.startApp() before using the waitDriver");
    }

    public static void stopApp() {
        if (androidDriver != null) {
            androidDriver.quit();
            androidDriver = null;
        }
        if (iOSDriver != null) {
            iOSDriver.quit();
            iOSDriver = null;
        }
        waitDriver = null;
    }

    public static void label(String label) {
        if (platform == Platform.ANDROID && androidDriver != null) {
            androidDriver.label(label);
            return;
        }

        if (platform == Platform.IOS && iOSDriver != null) {
            iOSDriver.label(label);
            return;
        }

        throw new RuntimeException("You must call AppManager.startApp() before using label");
    }

    public static void startApp() {
        stopApp();
        setPlatform();


        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        URL driverUrl = getUrl();

        switch (platform) {
            case ANDROID:
                // desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, ANDROID_DEVICE_NAME);
                // desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, ANDROID_PLATFORM_NAME);
                desiredCapabilities.setCapability("appPackage", ANDROID_APP_PACKAGE);
                desiredCapabilities.setCapability("appActivity", ANDROID_APP_ACTIVITY);
                // desiredCapabilities.setCapability(MobileCapabilityType.APPLICATION_NAME, ANDROID_AUTOMATION_NAME);

                androidDriver = Factory.createAndroidDriver(driverUrl, desiredCapabilities);
                androidDriver.manage().timeouts().implicitlyWait(SHORT_TIMEOUT, TimeUnit.SECONDS);
                waitDriver = new WebDriverWait(androidDriver, LONG_TIMEOUT);
                break;
            case IOS:
                desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone X");
                //desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
                //desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.1");
                desiredCapabilities.setCapability(MobileCapabilityType.APP, "/Users/semih.celik/Library/Developer/Xcode/DerivedData/RNAppiumApp-eeqnuftwgafxwhcutdhesttxbtbd/Build/Products/Release-iphonesimulator/RNAppiumApp.app");
                //desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");

                iOSDriver = Factory.createIOSDriver(driverUrl, desiredCapabilities);
                iOSDriver.manage().timeouts().implicitlyWait(SHORT_TIMEOUT, TimeUnit.SECONDS);
                waitDriver = new WebDriverWait(iOSDriver, LONG_TIMEOUT);
                break;
        }


    }

    private static void setPlatform() {
        String envPlatform = PropertiesConfig.getInstance().getPlatform();
        if (envPlatform == null) {
            throw new RuntimeException("The environment variable is not set");
        } else if (envPlatform.equals("android")) {
            platform = Platform.ANDROID;
        } else if (envPlatform.equals("ios")) {
            platform = Platform.IOS;
        } else {
            throw new RuntimeException("Platform not supported: " + envPlatform);
        }
    }

    private static URL getUrl() {
        try {
            return new URL(URL_PATH);
        } catch (MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }


}
