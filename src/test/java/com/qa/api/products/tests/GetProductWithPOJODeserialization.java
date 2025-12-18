package com.qa.api.products.tests;

import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.Product;
import com.qa.api.utils.ObjectMapperUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetProductWithPOJODeserialization extends BaseTest {

	
	
	@Test
	public void getAllProductsTest() {
		Response response=restClient.get(BASE_URL_FAKE_PRODUCT, FAKE_PRODUCTS_ENDPOINT, null, null, AuthType.NO_AUTH, ContentType.JSON);
	
		Product[] allProducts=ObjectMapperUtil.deserialize(response,Product[].class);
	
		for(Product p: allProducts) {
			System.out.println("id:"+p.getId());
			System.out.println("title:"+p.getTitle());
			System.out.println("price:"+p.getPrice());
			System.out.println("category:"+p.getCategory());
			System.out.println("Description:"+p.getDescription());
			System.out.println("image:"+p.getImage());
			System.out.println("rating rate :"+p.getRating().getRate());
			System.out.println("rating count:"+p.getRating().getCount());
			System.out.println("-------------------------");
		}
	
	
	
	}
	
}
