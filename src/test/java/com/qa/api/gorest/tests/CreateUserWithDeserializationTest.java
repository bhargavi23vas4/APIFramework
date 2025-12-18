package com.qa.api.gorest.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.ObjectMapperUtil;
import com.qa.api.utils.StringUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserWithDeserializationTest extends BaseTest {


	@BeforeClass
	public void goRestTokenSetup() {
		ConfigManager.set("bearertoken","6ba2edf2bf4ae66151749e38d760722aeca86ac7069a8634ecb82b5e729442d4");		
	}
	
	@Test
	public void createUserTest() {
		
		User user=new User(null,"Bhargavi UG",StringUtils.getRamdomEmailId(),"female","active");

		Response response=restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user,null, null, AuthType.BEARER_TOKEN,ContentType.JSON);
		Assert.assertEquals(response.jsonPath().getString("name"), "Bhargavi UG");
		
		int userId=response.jsonPath().getInt("id");
		System.out.println("user id :"+userId);	
		
//GET
	Response getResponse=	restClient.get(BASE_URL_GOREST, GOREST_USERS_ENDPOINT+"/"+userId,null,null,AuthType.BEARER_TOKEN,ContentType.ANY);
//		response json===>pojo
	User userResponse=ObjectMapperUtil.deserialize(getResponse,User.class);
	
	Assert.assertEquals(userResponse.getId(),userId);
	Assert.assertEquals(userResponse.getName(), user.getName());
	Assert.assertEquals(userResponse.getEmail(), user.getEmail());
	Assert.assertEquals(userResponse.getStatus(), user.getStatus());
	Assert.assertEquals(userResponse.getGender(), user.getGender());
	
		
		
		
	}
	
	
	
}
