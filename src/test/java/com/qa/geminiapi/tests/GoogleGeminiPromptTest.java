package com.qa.geminiapi.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoogleGeminiPromptTest extends BaseTest {

	
	@Test
	public void geminiPromptTest() {
		
		
String requestBody="{\r\n"
		+ "	\"contents\": [{\r\n"
		+ "		\"parts\": [{\r\n"
		+ "			\"text\": \"What is  xpath in selenium?\"\r\n"
		+ "		}]\r\n"
		+ "	}]\r\n"
		+ "}";

ConfigManager.set("apikey","AIzaSyCTJihMNUSs");
		Response response=restClient.post(BASE_URL_GEMINI, GEMINI_ENDPOINT,requestBody,null,null,AuthType.API_KEY,ContentType.JSON);
	
		
		
	}
	
}
