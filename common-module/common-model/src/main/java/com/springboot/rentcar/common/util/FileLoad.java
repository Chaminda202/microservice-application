package com.springboot.rentcar.common.util;

import java.io.*;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class FileLoad {

    public static ResourceBundle loadResourceBundle(String file){
        return ResourceBundle.getBundle(file);
    }

    public Properties loadProperties(String file) {
        Properties properties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(file);
        try {
            properties.load(new FileReader(new File(resource.getFile())));
        }catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }

    public Properties readProperties(String file) {
        Properties properties = new Properties();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("configuration.properties");
        try {
            properties.load(inputStream);
        }catch (IOException e){
            e.printStackTrace();
        }
        return properties;
    }
}
