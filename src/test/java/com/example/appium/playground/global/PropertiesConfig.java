package com.example.appium.playground.global;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

public class PropertiesConfig {
    private static final String APP_CENTER_TEST_PLATFORM_ENV = "TEST_PLATFORM";
    private static PropertiesConfig instance;

    private final String platform;
    private final String iosAppPath;
    private final String iosDeviceName;

    private PropertiesConfig() throws IOException {
        Properties properties = getProperties();
        platform = Optional.of(System.getenv(APP_CENTER_TEST_PLATFORM_ENV)).orElse(properties.getProperty("platform"));
        System.out.println("Selected Platform: " + platform);
        iosAppPath = properties.getProperty("ios.app.path");
        iosDeviceName = properties.getProperty("ios.device.name");
    }

    static public PropertiesConfig getInstance() {
        try {
            if (instance == null) {
                instance = new PropertiesConfig();
            }
            return instance;
        } catch (IOException e) {
            throw new RuntimeException("Could not load properties", e);
        }

    }

    private Properties getProperties() throws IOException {
        Properties properties = getPropertiesFromName("local.properties");
        String platformProperties = System.getProperty("platform.properties");

        if (platformProperties != null) {
            Properties newProperties = getPropertiesFromName(platformProperties);
            properties.putAll(newProperties);
        }
        return properties;
    }

    private Properties getPropertiesFromName(String fileName) throws IOException {
        String propertiesFilePath = "src/test/resources/";
        InputStream file = new FileInputStream(new File(propertiesFilePath + fileName));

        Properties properties = new Properties();
        properties.load(file);
        file.close();
        return properties;

    }

    public String getPlatform() {
        return platform;
    }

    public String getIosAppPath() {
        return iosAppPath;
    }

    public String getIosDeviceName() {
        return iosDeviceName;
    }
}