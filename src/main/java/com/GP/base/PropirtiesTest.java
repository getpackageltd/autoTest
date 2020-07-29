package com.GP.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import com.GP.pages.LogInPage;

public class PropirtiesTest {
    private Properties prop = new Properties();
    private static PropirtiesTest instance = null;

    protected PropirtiesTest() {

        String propertiesFile = "test_data/properties.ini";
        URL url = LogInPage.class.getClassLoader().getResource(propertiesFile);

        if (url != null) {

            File file = null;
            try {
                file = new File(url.toURI());
            } catch (URISyntaxException e1) {

                e1.printStackTrace();
            }
            FileInputStream fileInput = null;
            try {
                fileInput = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try {
                prop.load(fileInput);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static PropirtiesTest getInstance() {
        if (instance == null) {
            instance = new PropirtiesTest();
        }
        return instance;
    }

    public String getProperty(String property) {
        return prop.getProperty(property);
    }
}