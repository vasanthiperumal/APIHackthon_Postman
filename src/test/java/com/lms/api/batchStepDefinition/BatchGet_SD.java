package com.lms.api.batchStepDefinition;


import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lms.api.utilities.ExcelReader;
import com.lms.api.utilities.ConfigReader;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BatchGet_SD {
	
	RequestSpecification requestSpec;
	Response response;
	String path;
	String sheetGet;
	//String invStatusCode;
	//static String validId;

	ExcelReader excelReaderUtil;
	Scenario scenario;
	Properties properties;
	
	private static final Logger logger = LogManager.getLogger(BatchGet_SD.class);
	
	public BatchGet_SD() {
		ConfigReader config = new ConfigReader();
		properties = config.loadProperties();
	}
	
	@Before
	public void initializeDataTable(Scenario scenario) throws Exception {
		this.scenario = scenario;
		sheetGet = properties.getProperty("sheetGet");
		excelReaderUtil = new ExcelReader(properties.getProperty("batchExcelpath"));
		excelReaderUtil.readSheet(sheetGet);
	}
	
	
	public void requestSpecification() {
		requestSpec.header("Accept", ContentType.JSON.getAcceptHeader()).contentType(ContentType.JSON);
		requestSpec.log().all();
		response = requestSpec.when().get(path);
	}
	
	
	
		
	@Given("User is on GET method with endpoint batch")
	public void user_is_on_get_method_with_endpoint_batch() {
		logger.info("@User is on GET method with endpoint batch");
		RestAssured.baseURI=properties.getProperty("base_uri");
		requestSpec=RestAssured.given();
		path = properties.getProperty("endpointGetallBatches");
	}

	@When("User sends the GET request with valid inputs")
	public void user_sends_the_get_request_with_valid_inputs() {
		logger.info("@User sends the GET request with valid inputs");
		requestSpecification();
	    	}

	@Then("User is able to read all Batch details and receive status code for get")
	public void user_is_able_to_read_all_batch_details_and_receive_status_code_for_get() throws IOException {
		logger.info("@User is able to read all Batch details and receive status code for get");
		String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
		String responseBody = response.prettyPrint();
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		//assertThat(responseBody, matchesJsonSchemaInClasspath("GetAllBatch.json"));
		//logger.info("Response Status code is =>  " + response.statusCode());
			    
	}
	
	
	@Given("User is on GET method with endpoint with single BatchId")
	public void user_is_on_get_method_with_endpoint_with_single_batch_id() throws IOException {
		logger.info("@User is on GET method with endpoint with single BatchId");
		RestAssured.baseURI = properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		String BatchId = excelReaderUtil.getDataFromExcel(scenario.getName(), "BatchId");
		System.out.println("Program id from Excel is==>" + BatchId);
		path = properties.getProperty("endpointGetBatchbyId") +BatchId ;
		logger.info("BatchId from excel : " +BatchId );
		
	}

	@When("User sends the GET request with valid BatchId")
	public void user_sends_the_get_request_with_valid_batch_id() {
		logger.info("@User sends the GET request with valid BatchId");
		requestSpecification();
	}

	@Then("User should see the Batch details for the given BatchId")
	public void user_should_see_the_batch_details_for_the_given_batch_id() throws IOException {
		logger.info("@User should see the Batch details for the given BatchId");
		String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
		String responseBody = response.prettyPrint();
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		logger.info("Actual Response Status code=>  " + response.statusCode() + "  Expected Response Status code=>  "
				+ expStatusCode);
		assertThat(responseBody, matchesJsonSchemaInClasspath("GetAllBatch.json"));
		logger.info("Schema vlaidator is sucess");
		logger.info("Response Status code is =>  " + response.statusCode());
		logger.info("Response Body is => " + responseBody);
		
		JsonPath js = response.jsonPath();
		int rsBatchId = js.get("batchId");
		logger.info("BatchId from json :  " + rsBatchId);
		
	}


@When("User sends the GET request with invalid BatchId")
public void user_sends_the_get_request_with_invalid_batch_id() {
    
	logger.info("@User sends the GET request with invalid BatchId");
	requestSpecification();
}

@Then("User should receive error status code and message for Get")
public void user_should_receive_error_status_code_and_message_for_get() throws IOException {
    
	logger.info("User should receive error status code and message for Get");
	String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
	String responseBody = response.prettyPrint();
	assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
	logger.info("Actual Response Status code=>  " + response.statusCode() + "  Expected Response Status code=>  "
			+ expStatusCode);
}
	

//scenario4
@When("User sends GET the request with alphanumeric BatchId")
public void user_sends_get_the_request_with_alphanumeric_batch_id() {
	logger.info("@User sends GET the request with alphanumeric BatchId");
	requestSpecification();
}
	
//scenario5	
@When("User sends the request with blank BatchId")
public void user_sends_the_request_with_blank_batch_id() {
	logger.info("@User sends the request with blank BatchId");
	requestSpecification();
}

//scenario6
@When("User sends the request with decimal BatchId")
public void user_sends_the_request_with_decimal_batch_id() {
	logger.info("@User sends the request with decimal BatchId");
	requestSpecification();
	
}

//scenario7
@Given("User is on GET method with endpoint ProgramId")
public void user_is_on_get_method_with_endpoint_program_id() throws IOException {
	logger.info("User is on GET method with endpoint ProgramId");
	RestAssured.baseURI = properties.getProperty("base_uri");
	requestSpec = RestAssured.given();
	String BatchId = excelReaderUtil.getDataFromExcel(scenario.getName(), "BatchId");
	System.out.println("Program id from Excel is==>" + BatchId);
	path = properties.getProperty("endpointGetBatchbyProgramId") +BatchId ;
	logger.info("ProgramId from excel : " +BatchId );  
	
}

@When("User sends the request with valid ProgramId")
public void user_sends_the_request_with_valid_program_id() {
    
	logger.info("@User sends the request with valid ProgramId");
	requestSpecification();
}

@Then("User should see the Batch details for the given ProgramId")
public void user_should_see_the_batch_details_for_the_given_program_id() throws IOException {
	logger.info("@User should see the Batch details for the given ProgramId");
	String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
	String responseBody = response.prettyPrint();
	assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
	
}
	
	

//scenario8
@Given("User is on GET method with endpoint batchName")
public void user_is_on_get_method_with_endpoint_batch_name() throws IOException {
	
	logger.info("@User is on GET method with endpoint batchName");
	RestAssured.baseURI = properties.getProperty("base_uri");
	requestSpec = RestAssured.given();
	String BatchId = excelReaderUtil.getDataFromExcel(scenario.getName(), "BatchId");
	System.out.println("Program id from Excel is==>" + BatchId);
	path = properties.getProperty("endpointGetBatchbyName") +BatchId ;
	logger.info("BatchId from excel : " +BatchId );
	
}

@When("User sends GET request with valid BatchName")
public void user_sends_get_request_with_valid_batch_name() {
	logger.info("@User sends GET request with valid BatchName");
	requestSpecification();
}



@Then("User should see the Batch details for the given BatchName")
public void user_should_see_the_batch_details_for_the_given_batch_name() throws IOException {
    
	logger.info("@User should see the Batch details for the given BatchName");
	String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
	String responseBody = response.prettyPrint();
	assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
	
}


//scenario9

@When("User sends the request with Invalid ProgramId")
public void user_sends_the_request_with_invalid_program_id() {
	logger.info("User sends the request with Invalid ProgramId");
	requestSpecification();
}


//scenario10
@When("User sends GET request with Invalid BatchName")
public void user_sends_get_request_with_invalid_batch_name() {
    
	logger.info("@User sends GET request with Invalid BatchName");
	requestSpecification();
}


//scenario11
@When("User sends GET request with blank BatchName")
public void user_sends_get_request_with_blank_batch_name() {
    
	logger.info("@User sends GET request with blank BatchName");
	requestSpecification();
	
}



	
}
