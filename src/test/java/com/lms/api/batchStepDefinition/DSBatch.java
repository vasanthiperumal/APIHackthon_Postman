package com.lms.api.batchStepDefinition;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import com.lms.api.programStepDefinition.DSProgram_SD;
import com.lms.api.utilities.ConfigReader;
import com.lms.api.utilities.DataStoreAsMap;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DSBatch {
	RequestSpecification requestSpec;
	Response response;
	String path;
	Properties properties;

	private static final Logger logger = LogManager.getLogger(DSBatch.class);

	public DSBatch() {
		ConfigReader config = new ConfigReader();
		properties = config.loadProperties();
	}

	@Given("User is on Post Batch Method with endpoint")
	public void user_is_on_post_batch_method_with_endpoint() {
		logger.info("@User is on Post Program Method with endpoint");
		RestAssured.baseURI = properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		path = properties.getProperty("endpointBatchPost");
	}

	@When("User sends Post Batch request with dynamically generated data")
	public void user_sends_post_batch_request_with_dynamically_generated_data() {
		logger.info("@User sends Post Batch request with dynamically generated data");
		requestSpec.header("Content-Type", "application/json");
		String randomnumber = RandomStringUtils.randomNumeric(3);
		String programName = "Jan23-CRUDCarriers-SDET-" + randomnumber;

		System.out.println("Program Name is " + programName);
		JSONObject request = new JSONObject();
		request.put("batchName", programName);
		request.put("batchDescription", "API Testing");
		request.put("batchStatus", "Active");
		request.put("batchNoOfClasses", "12");
		request.put("programId", "305");

		requestSpec.body(request.toJSONString()).log().all();
		response = requestSpec.when().post(path);
	}

	@Then("User should receive Batch created successfully message and  status code")
	public void user_should_receive_batch_created_successfully_message_and_status_code() {
		logger.info("@User should receive Batch created successfully message and  status code");
		String responseBody = response.prettyPrint();
		JsonPath js = response.jsonPath();
		System.out.println("New Batch Id Created is :" + js.get("batchId"));
		DataStoreAsMap.setValue("batchId", js.get("batchId").toString());

		// Status code validation
		assertEquals(201, response.statusCode());
		logger.info("Response Status code is =>  " + response.statusCode());
		logger.info("Response Body is =>  " + responseBody);
	}

	@Given("User is on Get Batch Method with endpoint")
	public void user_is_on_get_batch_method_with_endpoint() {
		logger.info("@Given User is on Get Batch Method with endpoint");
		RestAssured.baseURI = properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		String bId = (String) DataStoreAsMap.getValue("batchId");
		System.out.println("dynamically generatd Program is==>" + bId);
		path = properties.getProperty("endpointGetBatchbyId") + bId;
		logger.info("Dynamically generatd Program is : " + bId);
	}

	@When("User sends Post Batch request Get dyanamically created Batch")
	public void user_sends_post_batch_request_get_dyanamically_created_batch() {
		requestSpec.header("Accept", ContentType.JSON.getAcceptHeader()).contentType(ContentType.JSON);
		requestSpec.log().all();
		response = requestSpec.when().get(path);
	}

	@Then("User should get newly created Batch")
	public void user_should_get_newly_created_batch() {
		logger.info("@User should get newly created Batch");
		String responseBody = response.prettyPrint();
		assertEquals(200, response.statusCode());
		logger.info("Response Status code is =>  " + response.statusCode());
		logger.info("Response Body is => " + responseBody);
	}

	@Given("User is on Put Batch Method with endpoint")
	public void user_is_on_put_batch_method_with_endpoint() {
		RestAssured.baseURI = properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		String bId = (String) DataStoreAsMap.getValue("batchId");
		path = properties.getProperty("endpointBatchPut") + bId;
	}

	@When("User sends Post Batch request Update dyanamically created Batch")
	public void user_sends_post_batch_request_update_dyanamically_created_batch() {
		logger.info("@User sends Post Batch request Update dyanamically created Batch");
		requestSpec.header("Content-Type", "application/json");
		String randomnumber = RandomStringUtils.randomNumeric(3);
		String programName = "Jan23-CRUDCarriers-SDET-" + randomnumber;

		System.out.println("Program Name is " + programName);
		JSONObject request = new JSONObject();
		request.put("batchName", programName);
		request.put("batchDescription", "API Testing updated");
		request.put("batchStatus", "Active");
		request.put("batchNoOfClasses", "12");
		request.put("programId", "305");

		requestSpec.body(request.toJSONString()).log().all();
		response = requestSpec.when().put(path);
	}

	@Then("User should update newly created Batch")
	public void user_should_update_newly_created_batch() {
		String responseBody = response.prettyPrint();
		logger.info("Response body details" +responseBody);
		JsonPath js = response.jsonPath();
		System.out.println("The updated Batch id is :"+js.get("batchId"));
		assertEquals(200, response.statusCode());
	}

	@Given("User is on Delete Batch Method with endpoint")
	public void user_is_on_delete_batch_method_with_endpoint() {
		logger.info("@User is on Delete Batch Method with endpoint");
		RestAssured.baseURI=properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		String bId=(String) DataStoreAsMap.getValue("batchId");
		path = properties.getProperty("endpointBatchDeleteId") + bId;
		logger.info("Dyanmic Program Id is : " + bId);
	}

	@When("User sends Post Batch request Delete dyanamically created Batch")
	public void user_sends_post_batch_request_delete_dyanamically_created_batch() {
		requestSpec.header("Accept", ContentType.JSON.getAcceptHeader()).contentType(ContentType.JSON);
		requestSpec.log().all();
		response = requestSpec.when().delete(path);
	}

	@Then("User should Delete newly created Batch")
	public void user_should_delete_newly_created_batch() {
		logger.info("@Then User should Delete newly created Batch");
		String responseBody = response.prettyPrint();
		logger.info("Response body details" +responseBody);
		assertEquals(200, response.statusCode());
	}
}
