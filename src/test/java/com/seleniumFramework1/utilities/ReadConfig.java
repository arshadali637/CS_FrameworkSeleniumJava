package com.seleniumFramework1.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties properties;
	String path = "C:\\Users\\ARSHAD\\eclipse-workspace\\SeleniumFramework1\\Configuration\\config.properties";

	// constructor
	public ReadConfig() {
		try {
			properties = new Properties();
			FileInputStream fis = new FileInputStream(path);
			properties.load(fis);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// to get the url from the config.properties file
	public String getBaseUrl() {
		String value = properties.getProperty("baseUrl");
		if (value != null) {
			return value;
		} else {
			throw new RuntimeException("url not specified in config file");
		}
	}

	// to get the browser from the config.properties file
	public String getBrowser() {
		String value = properties.getProperty("browser");
		if (value != null) {
			return value;
		} else {
			throw new RuntimeException("browser not specified in config file");
		}
	}

	// to get the email from the config.properties file
	public String getEmail() {
		String email = properties.getProperty("email");
		if (email != null)
			return email;
		else
			throw new RuntimeException("email not specified in config file.");

	}

	// to get the password from the config.properties file
	public String getPassword() {
		String password = properties.getProperty("password");
		if (password != null)
			return password;
		else
			throw new RuntimeException("password not specified in config file.");
	}
	

}
