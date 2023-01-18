package com.lms.api.batchStepDefinition;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lms.api.utilities.ConfigReader;
import com.lms.api.utilities.ExcelReader;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BatchPut_SD {
	RequestSpecification requestSpec;
	Response response;
	String path;
	String sheetPut;
	String batchId;

	ExcelReader excelReaderUtil;
	Scenario scenario;
	Properties properties;

	private static final Logger logger = LogManager.getLogger(BatchPut_SD.class);

	public BatchPut_SD() {
		ConfigReader config = new ConfigReader();
		properties = config.loadProperties();
	}
	
	@Before
	public void initializeDataTable(Scenario scenario) throws Exception {
		this.scenario = scenario;
		sheetPut = properties.getProperty("sheetPut");
		excelReaderUtil = new ExcelReader(properties.getProperty("batchExcelpath"));
		excelReaderUtil.readSheet(sheetPut);
		logger.info(" sheetPut" +sheetPut);		
		logger.info(" batchExcelpath " + properties.getProperty("batchExcelpath"));
	}
	
	public void requestSpecificationPut() throws Exception {
		requestSpec.header("Content-Type", "application/json");
		logger.info("scenario.getName: "+scenario.getName());
		String bodyExcel = excelReaderUtil.getDataFromExcelPost(scenario.getName(),"Body");
		//assertThat("Schema Validation Failed", bodyExcel, matchesJsonSchemaInClasspath("BatchPut.json"));
		requestSpec.body(bodyExcel).log().all();
        response = requestSpec.when().put(path);
        		
	}
	
	@Given("User is on PUT method with endpoint")
	public void user_is_on_put_method_with_endpoint() throws IOException {
		logger.info("@Given User is on Put Method with endpoint");
	     RestAssured.baseURI = properties.getProperty("base_uri");
			requestSpec = RestAssured.given();
			 batchId = excelReaderUtil.getDataFromExcel(scenario.getName(),"BatchId");
			 logger.info("batchId:"+batchId);
			 
			path = properties.getProperty("endpointBatchPut")+ batchId;
			logger.info("put batch path:"+path);
	}
	
	@When("User sends request with valid BatchId")
	public void User_sends_request_with_valid_BatchId() throws Exception {
		logger.info("@When User sends request with valid batch inputs");
		requestSpecificationPut();
	}
	@Then("User should receive status code and message for PUT")
	public void user_should_receive_status_code_and_message_for_PUT() throws IOException {
		logger.info("User should receive status code and message for PUT");
		String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
		String expMessage = excelReaderUtil.getDataFromExcel(scenario.getName(), "Message");
		logger.info("Expected response code: " + expStatusCode + " Expected message is: " + expMessage);
		String responseBody = response.prettyPrint();
		JsonPath js = response.jsonPath();
		System.out.println("Batch is updated for batchId :"+js.get("batchId"));
		// Post Schema Validation
		//assertThat(responseBody, matchesJsonSchemaInClasspath("userResponse_schema.json"));
				
		//Status code validation
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		
		//Message validation
		//response.then().assertThat().extract().asString().contains("User successfully Created!");
		logger.info("Batch Put Response Status code is =>  " + response.statusCode());
		logger.info("Batch Put  Response Body is =>  " + responseBody);
	}
	
	@When("User sends the PUT request with invalid Batchid")
	public void user_sends_the_put_request_with_invalid_batchid() throws Exception {
		logger.info("@When User sends request with valid batch inputs");
		requestSpecificationPut();
	}
	@When("User sends the PUT request with Alphanumeric BatchId")
	public void user_sends_the_put_request_with_alphanumeric_batch_id() throws Exception {
		requestSpecificationPut();
	}

	@When("User sends the PUT request with blank BatchId")
	public void user_sends_the_put_request_with_blank_batch_id() throws Exception {
		requestSpecificationPut();
	}

	@When("User sends the PUT request with decimal BatchId")
	public void user_sends_the_put_request_with_decimal_batch_id() throws Exception {
		requestSpecificationPut();
	}

	@When("User sends the PUT request with negative BatchId")
	public void user_sends_the_put_request_with_negative_batch_id()throws Exception {
		requestSpecificationPut();
	}
	@When("User sends the PUT request with valid BatchId and without valid Json Schema")
	public void user_sends_the_put_request_with_valid_batch_id_and_without_valid_json_schema() throws Exception {
		requestSpecificationPut();
	}
	
}
