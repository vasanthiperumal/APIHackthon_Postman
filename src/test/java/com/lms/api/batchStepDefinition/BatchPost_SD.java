package com.lms.api.batchStepDefinition;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.lms.api.utilities.ConfigReader;
import com.lms.api.utilities.ExcelReader;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class BatchPost_SD {
	
	RequestSpecification requestSpec;
	Response response;
	String path;
	String sheetPost;
	String ProgramId;

	ExcelReader excelReaderUtil;
	Scenario scenario;
	Properties properties;
	
	private static final Logger logger = LogManager.getLogger(BatchPost_SD.class);
	
	public BatchPost_SD() {
		ConfigReader config = new ConfigReader();
		properties = config.loadProperties();
	}
	
	@Before
	public void initializeDataTable(Scenario scenario) throws Exception {
		this.scenario = scenario;
		sheetPost = properties.getProperty("sheetPost");
		excelReaderUtil = new ExcelReader(properties.getProperty("batchExcelpath"));
		excelReaderUtil.readSheet(sheetPost);
	}
	
	public void requestSpecificationPost() throws Exception {
		requestSpec.header("Content-Type", "application/json");
		String bodyExcel = excelReaderUtil.getDataFromExcelPost(scenario.getName(), "Body");
		//assertThat("Schema Validation Failed", bodyExcel, matchesJsonSchemaInClasspath("PorgramPost.json"));
		requestSpec.body(bodyExcel).log().all();
        response = requestSpec.when().post(path);
	}
	
	
	public void statusCodeValidation() throws IOException {
		String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
		//String expMessage = excelReaderUtil.getDataFromExcel(scenario.getName(), "Message");
		logger.info("Expected response code => " + expStatusCode );
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		logger.info(" Actual Response code is =>  " + response.statusCode());
		}
		
	
	@Given("User is on POST Method with endpoint")
	public void user_is_on_post_method_with_endpoint() {
		logger.info("@User is on POST Method with endpoint");
	     RestAssured.baseURI = properties.getProperty("base_uri");
			requestSpec = RestAssured.given();
			path = properties.getProperty("endpointBatchPost");
	   
	}

	@When("User sends request with valid data")
	public void user_sends_request_with_valid_data() throws Exception {
		logger.info("@User sends request with valid data");
		requestSpecificationPost();
	}

	@Then("User should receive status code and message for POST")
	public void user_should_receive_status_code_and_message_for_post() throws IOException {
	    
		String responseBody = response.prettyPrint();
		//logger.info("Response body details" +responseBody);
		JsonPath js = response.jsonPath();
		System.out.println("New Batch created :"+js.get("batchId"));
		statusCodeValidation();
		//status message validation
		//response.then().assertThat().extract().asString().contains("User successfully Created!");
	}

	//scenario2
	@When("User sends request with all inputs blank")
	public void user_sends_request_with_all_inputs_blank() throws Exception {
		logger.info("@User sends request with all inputs blank");
		requestSpecificationPost();
	}

	
	//scenario 3
	@When("User sends the POST request with available BatchName")
	public void user_sends_the_post_request_with_available_batch_name() throws Exception {
		logger.info("@User sends the POST request with available BatchName");
		requestSpecificationPost();
	}
	
	//scenario4
	@When("User sends a POST request with invalid JSON schema")
	public void user_sends_a_post_request_with_invalid_json_schema() throws Exception {
		logger.info("@User sends a POST request with invalid JSON schema");
		requestSpecificationPost();
	}
	
	//scenario5
	@When("User sends POST request with numeric BatchName,Description and Status")
	public void user_sends_post_request_with_numeric_batch_name_description_and_status() throws Exception {
		logger.info("User sends POST request with numeric BatchName,Description and Status");
		requestSpecificationPost(); 
	}
	
	//scenario6
	@When("User sends POST request with Alphanumeric Batch NoOfClasses")
	public void user_sends_post_request_with_alphanumeric_batch_no_of_classes() throws Exception {
		logger.info("@User sends POST request with Alphanumeric Batch NoOfClasses");
		requestSpecificationPost(); 
	}
    
	//scenario7
	@When("User sends POST request with Batch NoOfClasses Negative number")
	public void user_sends_post_request_with_batch_no_of_classes_negative_number() throws Exception {
		logger.info("@User sends POST request with Batch NoOfClasses Negative number");
		requestSpecificationPost();   
	}
	//scenario8
	@When("User sends POST request with invalid programId")
	public void user_sends_post_request_with_invalid_program_id() throws Exception {
		logger.info("@User sends POST request with Batch NoOfClasses Negative number");
		requestSpecificationPost();
	}

	//scenario9
	@When("User sends POST request with BatchName as blank")
	public void user_sends_post_request_with_batch_name_as_blank() throws Exception {
		logger.info("@User sends POST request with Batch NoOfClasses Negative number");
		requestSpecificationPost();	
	}
	
	//scenario10
	@When("User sends POST request Batch description as blank")
	public void user_sends_post_request_batch_description_as_blank() throws Exception {
		logger.info("@User sends POST request with Batch NoOfClasses Negative number");
		requestSpecificationPost();
	}

	//scenario11
	@When("User sends POST request with Batch status as blank")
	public void user_sends_post_request_with_batch_status_as_blank() throws Exception {
		logger.info("@User sends POST request with Batch NoOfClasses Negative number");
		requestSpecificationPost();
	}
	
	//scenario12
	@When("User sends POST request with Batch NoOfclasses as blank")
	public void user_sends_post_request_with_batch_no_ofclasses_as_blank() throws Exception {
		logger.info("@User sends POST request with Batch NoOfClasses Negative number");
		requestSpecificationPost();
	}

	//scenario13
	@When("User sends POST request with programId as blank")
	public void user_sends_post_request_with_program_id_as_blank() throws Exception {
		logger.info("@User sends POST request with programId as blank");
		requestSpecificationPost();
	}
	

	//scenario14
	@When("User sends POST request with valid programId")
	public void user_sends_post_request_with_valid_program_id() throws Exception {
		logger.info("@User sends POST request with Batch NoOfClasses Negative number");
		requestSpecificationPost();
	}
	
}
