package com.qa.api.gorest.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.User;
import com.qa.api.utils.CSVUtil;
import com.qa.api.utils.ExcelUtil;
import com.qa.api.utils.StringUtils;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@Epic("EP 100 -- Create User feature for Go Rest API")	
@Feature("FF 50- create userstory with json,pojo and file")	
@Story("US 200- create user with jso, pojo and file")
public class CreateUserTest extends BaseTest {

	@Test
	public void createUsersTest() {

	String userJson="{\r\n"
			+ "    \"name\": \"Bhargavi test\",\r\n"
			+ "    \"email\": \"achari_bhargavi_test16799@thompson-murphy.test\",\r\n"
			+ "    \"gender\": \"female\",\r\n"
			+ "    \"status\": \"active\"\r\n"
			+ "}";	
Response response= restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, userJson, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);	
	
Assert.assertEquals(response.statusCode(), 201);

	}
	
	
	
	@BeforeClass
	public void goRestTokenSetup() {
	
		ConfigManager.set("bearertoken","6ba2edf2bf4ae66151749e38d760722aeca86ac7069a8634ecb82b5e729442d4");	
		
	}
	
	
	
	
	@DataProvider
	public Object[][] getUserData() {
		return new Object[][] {
			{"bhargavi","female","active"},
			{"somu","male","active"},
			{"lokesh","male","active"}
		};
	}
	
	
	
	
	@DataProvider
	public Object[][] getUserSheetData() {
		return ExcelUtil.readData("gorest");
	}
	
	
	@DataProvider
	public Object[][] getUserCSVData() {
		return CSVUtil.csvData("TestData");
	}
	
	@Owner("Bhargavi")
	@Description("create a user using POST call with POJO class, get the data from CSV..")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider="getUserCSVData")
	public void createUserWithPOJOTest(String name, String gender, String status) {


//User user=new User("Bhargavi G","bhargavi_16@gmail.com","female","active");

		User user=User.builder()
		.name(name)
		.email(StringUtils.getRamdomEmailId())
		.status(status)
		.gender(gender)
		.build();
//		ConfigManager.set("bearertoken","6ba2edf2bf4ae66151749e38d760722aeca86ac7069a8634ecb82b5e729442d4");	
		
		
Response response= restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, user, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);		
ChainTestListener.log(response.getBody().asString());
ChainTestListener.log("Status code"+response.statusCode());
ChainTestListener.log(response.getHeaders().get("Content-Type").getValue());
Assert.assertEquals(response.statusCode(), 201);

	}
	
//@Epic("EP 100 -- Create User feature for Go Rest API")	
//@Feature("Ff 50- create userstory with json,pojo and file")	
@Test
	public void createUserWithJSONFileTest() {

File userFile=new File("src/test/resources/jsons/user.json");
		Response response= restClient.post(BASE_URL_GOREST, GOREST_USERS_ENDPOINT, userFile, null, null, AuthType.BEARER_TOKEN, ContentType.JSON);		
		Assert.assertEquals(response.statusCode(), 201);

			}
				
	
	
}
