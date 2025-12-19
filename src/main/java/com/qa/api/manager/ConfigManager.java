package com.qa.api.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	
	//read properties from config prop file using java code
	
	
	
	private static Properties prop=new Properties();
	
	
	static{
		
//		mvn clean install -Denv=qa/dev/stage/uat/prod
//		mvn clean install -Denv=qa
		
		String envName=System.getProperty("env","prod");
		
		System.out.println("running testcases on env:" +envName);
		
		String fileName="config_"+envName+".properties";
		
//		String fileName="config.properties";
		InputStream input=ConfigManager.class.getClassLoader().getResourceAsStream(fileName);
	
		if(input!=null) {
				try {
					prop.load(input);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				System.out.println("config properties=======>"+prop);
				
			}
		}
		
		
	public static String get(String key) {
		return prop.getProperty(key).trim();
	}
	
	
	public static void set(String key, String value) {
		prop.setProperty(key, value);
		
	}
	
}
	
	
	

