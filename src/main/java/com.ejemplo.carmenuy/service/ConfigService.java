// CarmenSandiegoUruguay/src/main/java/com.ejemplo.carmenuy/service/ConfigService.java
package com.ejemplo.carmenuy.service;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ConfigService {
    private static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
