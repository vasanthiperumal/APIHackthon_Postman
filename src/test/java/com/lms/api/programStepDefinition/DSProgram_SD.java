package com.lms.api.programStepDefinition;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import com.lms.api.utilities.ConfigReader;
import com.lms.api.utilities.DataStoreAsMap;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DSProgram_SD {
	
	RequestSpecification requestSpec;
	Response response;
	String path;
	Properties properties;
	
	private static final Logger logger = LogManager.getLogger(DSProgram_SD.class);

	public DSProgram_SD() {
		ConfigReader config = new ConfigReader();
		properties = config.loadProperties();
	}
	
	@Given("User is on Post Program Method with endpoint")
	public void user_is_on_post_program_method_with_endpoint() {
		logger.info("@User is on Post Program Method with endpoint");
		RestAssured.baseURI = properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		path = properties.getProperty("endpointPost");
	}

	@When("User sends Post Program request with dynamically generated data")
	public void user_sends_post_program_request_with_dynamically_generated_data() {
		logger.info("@User sends Post Program request with dynamically generated data");
		requestSpec.header("Content-Type", "application/json");
		String randomnumber = RandomStringUtils.randomNumeric(3);
		String programName = "Jan23-CRUDCarriers-SDET-" + randomnumber;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
		Date date = new Date();
		String creationTime = dateFormat.format(date);
		System.out.println("CrationTime" + creationTime);
		System.out.println("Program Name is " + programName);
		JSONObject request = new JSONObject();
		request.put("programName", programName);
		request.put("programDescription", "Update Program By Name");
		request.put("programStatus", "InActive");
		request.put("creationTime", creationTime);
		request.put("lastModTime", creationTime);

		requestSpec.body(request.toJSONString()).log().all();
		response = requestSpec.when().post(path);
	}
	@Then("User should receive created successfully message and status code")
	public void user_should_receive_created_successfully_message_and_status_code() {
		logger.info("@User should receive created successfully message and  status code");
		String responseBody = response.prettyPrint();
		JsonPath js = response.jsonPath();
		System.out.println("New Program Created is :" + js.get("programId"));
		DataStoreAsMap.setValue("programId",js.get("programId").toString());
		// Post Schema Validation
		//assertThat(responseBody, matchesJsonSchemaInClasspath("ProgramPost.json"));
		
		// Status code validation
		assertEquals(201, response.statusCode());
		logger.info("Response Status code is =>  " + response.statusCode());
		logger.info("Response Body is =>  " + responseBody);
	}
	
	@Given("User is on Get Program Method with endpoint")
	public void user_is_on_get_program_method_with_endpoint() {
		logger.info("@Given User is on Get Method with end point for single program");
		RestAssured.baseURI = properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		String pId=(String) DataStoreAsMap.getValue("programId");
		System.out.println("dynamically generatd Program is==>" + pId);
		path = properties.getProperty("endpointGet") + pId;
		logger.info("Dynamically generatd Program is : " + pId);
		
	}

	@When("User sends Post Program request Get dyanamically created Program")
	public void user_sends_program_request_get_dyanamically_created_program() {
		requestSpec.header("Accept", ContentType.JSON.getAcceptHeader()).contentType(ContentType.JSON);
		requestSpec.log().all();
		response = requestSpec.when().get(path);
	}

	@Then("User should get newly created programs")
	public void user_should_get_newly_created_programs() {
		logger.info("@User should see the program details for the given programId");
		String responseBody = response.prettyPrint();
		assertEquals(200, response.statusCode());
		//assertThat(responseBody, matchesJsonSchemaInClasspath("GetProgram.json"));
		logger.info("Response Status code is =>  " + response.statusCode());
		logger.info("Response Body is => " + responseBody);

	}

	@Given("User is on Put Program Method with endpoint")
	public void user_is_on_put_program_method_with_endpoint() {
		RestAssured.baseURI = properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		String pId=(String) DataStoreAsMap.getValue("programId");
		path = properties.getProperty("endpointPutId") + pId ;
	}

	@When("User sends Post Program request Update dyanamically created Program")
	public void user_sends_post_program_request_update_dyanamically_created_program() {
		requestSpec.header("Content-Type", "application/json");
		String randomnumber = RandomStringUtils.randomNumeric(3);
		String programName = "Jan23-CRUDCarriers-SDET-" + randomnumber;
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS");
		Date date = new Date();
		String creationTime = dateFormat.format(date);
		System.out.println("CrationTime" + creationTime);
		System.out.println("Program Name is " + programName);
		JSONObject request = new JSONObject();
		request.put("programName", programName);
		request.put("programDescription", "Update Program description");
		request.put("programStatus", "InActive");
		request.put("creationTime", creationTime);
		request.put("lastModTime", creationTime);

		requestSpec.body(request.toJSONString()).log().all();
        response = requestSpec.when().put(path);
	}

	@Then("User should update newly created programs")
	public void user_should_update_newly_created_programs() {
		String responseBody = response.prettyPrint();
		logger.info("Response body details" +responseBody);
		JsonPath js = response.jsonPath();
		System.out.println("The updated Program id is :"+js.get("programId"));
		assertEquals(200, response.statusCode());
	}

	@Given("User is on Delete Program Method with endpoint")
	public void user_is_on_delete_program_method_with_endpoint() {
		logger.info("@Given User is on DELETE method with endpoint");
		RestAssured.baseURI=properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		String pId=(String) DataStoreAsMap.getValue("programId");
		path = properties.getProperty("endpointDeleteId") + pId;
		logger.info("Dyanmic Program Id is : " + pId);
	}

	@When("User sends Post Program request Delete dyanamically created Program")
	public void user_sends_post_program_request_delete_dyanamically_created_program() {
		requestSpec.header("Accept", ContentType.JSON.getAcceptHeader()).contentType(ContentType.JSON);
		requestSpec.log().all();
		response = requestSpec.when().delete(path);
	}

	@Then("User should Delete newly created programs")
	public void user_should_delete_newly_created_programs() {
		logger.info("@Then User should receive status code and message for delete");
		String responseBody = response.prettyPrint();
		logger.info("Response body details" +responseBody);
		assertEquals(200, response.statusCode());
	}

}
