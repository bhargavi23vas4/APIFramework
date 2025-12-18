package com.qa.api.schema.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.SchemaValidator;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GoRestAPISchemaTest extends BaseTest{

	
	@BeforeClass
	public void goRestTokenSetup() {
		ConfigManager.set("bearertoken","6ba2edf2bf4ae66151749e38d760722aeca86ac7069a8634ecb82b5e729442d4");		
	}
	
	
	
	
	
	@Test
	public void getUsersAPISchemaTest() {
		
		Response response=restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
		Assert.assertTrue(SchemaValidator.validateSchema(response, "getusersschema.json"));
		
		
	}
	
	
	
	@Test
	public void createUsersAPISchemaTest() {
		//User user=new User("Bhargavi G","bhargavi_16@gmail.com","female","active");

				User user=User.builder()
				.name("Sweetybeauty")
				.email(StringUtils.getRamdomEmailId())
				.status("active")
				.gender("female")
				.build();
		
		
		Response response=restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user,null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
		Assert.assertTrue(SchemaValidator.validateSchema(response, "createuserschema.json"));
		
		
	}
	
	
}
