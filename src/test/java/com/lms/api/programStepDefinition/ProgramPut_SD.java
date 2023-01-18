package com.lms.api.programStepDefinition;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;


import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

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
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class ProgramPut_SD {
	
	RequestSpecification requestSpec;
	Response response;
	String path;
	String sheetPut;
	String ProgramId;
	//String responseBody;

	ExcelReader excelReaderUtil;
	Scenario scenario;
	Properties properties;
	
	private static final Logger logger = LogManager.getLogger(ProgramPut_SD.class);

	public ProgramPut_SD() {
		ConfigReader config = new ConfigReader();
		properties = config.loadProperties();
	}
	
	@Before
	public void initializeDataTable(Scenario scenario) throws Exception {
		this.scenario = scenario;
		sheetPut = properties.getProperty("sheetPut");
		excelReaderUtil = new ExcelReader(properties.getProperty("programExcelpath"));
		excelReaderUtil.readSheet(sheetPut);
	}

	public void requestSpecificationPut() throws Exception {
		requestSpec.header("Content-Type", "application/json");
		String bodyExcel = excelReaderUtil.getDataFromExcel(scenario.getName(), "Body");
		
		requestSpec.body(bodyExcel).log().all();
        response = requestSpec.when().put(path);
	}
	
	public void statusCodeValidation() throws IOException {
		String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
		//String expMessage = excelReaderUtil.getDataFromExcel(scenario.getName(), "Message");
		logger.info("Expected response code => " + expStatusCode );
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		logger.info(" Actual Response code is =>  " + response.statusCode());
		
			
		}
	
	public void schemaValidation() throws IOException {
		// Post Schema Validation
			String responseBody = response.prettyPrint();	
			assertThat(responseBody, matchesJsonSchemaInClasspath("ProgramPut.json"));
			System.out.println("Schema is validated successfully");
			logger.info("Schema validation is success");
			logger.info("Response Body is => " + responseBody);
		
	}
	

	@Given("User is on PUT method with endpoint Program id")
	public void user_is_on_put_method_with_endpoint_program_id() throws IOException {
		RestAssured.baseURI = properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		String programId = excelReaderUtil.getDataFromExcel(scenario.getName(), "ProgramId");
		path = properties.getProperty("endpointPutId") + programId;
	}
	
	@When("User sends put request with valid inputs for id")
	public void user_sends_put_request_with_valid_inputs_for_id() throws Exception {
	    
	    requestSpecificationPut();
	}
	
	@Then("User should receive proper status code and valid reponse")
	public void user_should_receive_proper_status_code_and_valid_reponse() throws IOException {
		String responseBody = response.prettyPrint();
		logger.info("Response body details" +responseBody);
		JsonPath js = response.jsonPath();
		System.out.println("The updated Program id is :"+js.get("programId"));
		
		statusCodeValidation();
	}
	
	@Then("JSON schema of response should be valid")
	public void json_schema_of_response_should_be_valid() throws IOException {
		schemaValidation();
				
	}
	
	@Given("User is on PUT method with endpoint Program name")
	public void user_is_on_put_method_with_endpoint_program_name() throws IOException {
	    RestAssured.baseURI = properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		String programId = excelReaderUtil.getDataFromExcel(scenario.getName(), "ProgramId");
		path = properties.getProperty("endpointPutName") + programId;;
	}
	
	@When("User sends put request with valid inputs for name")
	public void user_sends_put_request_with_valid_inputs_for_name() throws Exception {
		requestSpecificationPut();
	}
	
	@When("User sends request with duplicate program name")
	public void user_sends_request_with_duplicate_program_name() throws Exception {
	    
		requestSpecificationPut();
	}
	
	@Then("User should receive proper status code and message")
	public void user_should_receive_proper_status_code_and_message() throws IOException {
		statusCodeValidation();
		String expMessage = excelReaderUtil.getDataFromExcel(scenario.getName(), "Message");
		String actMessage = response.asPrettyString();
		System.out.println("Actual Message is :" +actMessage);
		//System.out.println("Expected Message contains :" +expMessage);
		assertThat(expMessage,actMessage.contains(expMessage));
						
		
	}
	
	@When("User sends inputs with alphanumeric values in program name, description and status")
	public void user_sends_inputs_with_alphanumeric_values_in_program_name_description_and_status() throws Exception {
		requestSpecificationPut();
		logger.info("@Logging for Alpha Numeric values");
	}
	
	@When("User sends request with invalid creation time and last mod time")
	public void user_sends_request_with_invalid_creation_time_and_last_mod_time() throws Exception {
		requestSpecificationPut();
		logger.info("@Logging for invalid time values");
	}
	
	@When("User sends put request with invalid inputs for id")
	public void user_sends_put_request_with_invalid_inputs_for_id() throws Exception {
		requestSpecificationPut();
		logger.info("@Logging for invalid id");
	}

	@When("User sends put request with invalid inputs for name")
	public void user_sends_put_request_with_invalid_inputs_for_name() throws Exception {
		requestSpecificationPut();
		logger.info("@Logging for invalid name");
	}

}
