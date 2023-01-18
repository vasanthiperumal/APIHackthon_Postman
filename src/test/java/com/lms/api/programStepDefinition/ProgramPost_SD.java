package com.lms.api.programStepDefinition;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.LocalDate;
import org.json.simple.JSONObject;

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

public class ProgramPost_SD {
	RequestSpecification requestSpec;
	Response response;
	String path;
	String sheetPost;
	String ProgramId;

	ExcelReader excelReaderUtil;
	Scenario scenario;
	Properties properties;

	private static final Logger logger = LogManager.getLogger(ProgramPost_SD.class);

	public ProgramPost_SD() {
		ConfigReader config = new ConfigReader();
		properties = config.loadProperties();
	}

	@Before
	public void initializeDataTable(Scenario scenario) throws Exception {
		this.scenario = scenario;
		sheetPost = properties.getProperty("sheetPost");
		excelReaderUtil = new ExcelReader(properties.getProperty("programExcelpath"));
		excelReaderUtil.readSheet(sheetPost);
	}

	public void requestSpecificationPost() throws Exception {
		requestSpec.header("Content-Type", "application/json");
		String bodyExcel = excelReaderUtil.getDataFromExcel(scenario.getName(), "Body");
		// assertThat("Schema Validation Failed", bodyExcel,
		// matchesJsonSchemaInClasspath("PorgramPost.json"));
		requestSpec.body(bodyExcel).log().all();
		response = requestSpec.when().post(path);
	}

	@Given("User is on Post Method with endpoint")
	public void user_is_on_post_method_with_endpoint() {
		logger.info("@Given User is on Post Method with endpoint");
		RestAssured.baseURI = properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		path = properties.getProperty("endpointPost");
	}

	@When("User sends request with valid inputs")
	public void user_sends_request_with_valid_inputs() throws Exception {
		logger.info("@When User sends request with valid inputs");
		requestSpecificationPost();
	}

	@When("User sends the request with already available program name")
	public void user_sends_the_request_with_already_available_program_name() throws Exception {
		logger.info("@When User sends the request with already available program name");
		requestSpecificationPost();
	}

	@Then("User should receive status code and message for post")
	public void user_should_receive_status_code_and_message_for_post() throws IOException {
		logger.info("@Then User should receive status code and message for post");
		String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
		String expMessage = excelReaderUtil.getDataFromExcel(scenario.getName(), "Message");
		logger.info("Expected response code: " + expStatusCode + " Expected message is: " + expMessage);
		String responseBody = response.prettyPrint();
		JsonPath js = response.jsonPath();
		System.out.println("New Program Created is :" + js.get("programId"));
		// Post Schema Validation
		// assertThat(responseBody, matchesJsonSchemaInClasspath("ProgramPost.json"));

		// Status code validation
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());

		// Message validation
		response.then().assertThat().extract().asString().contains(expMessage);

		logger.info("Response Status code is =>  " + response.statusCode());
		logger.info("Response Body is =>  " + responseBody);
	}

	@When("User sends the request with blank fields")
	public void user_sends_the_request_with_blank_fields() throws Exception {
		logger.info("@When User sends the request with blank fields");
		requestSpecificationPost();
	}

	@When("User sends the request with blank creationTime and lasModTime")
	public void user_sends_the_request_with_blank_creation_time_and_las_mod_time() throws Exception {
		logger.info("@User sends the request with blank creationTime and lasModTime");
		requestSpecificationPost();
	}

	@When("User sends the request with blank ProgramName")
	public void user_sends_the_request_with_blank_program_name() throws Exception {
		logger.info("@User sends the request with blank creationTime and lasModTime");
		requestSpecificationPost();
	}

	@When("User sends the request with blank ProgramStatus")
	public void user_sends_the_request_with_blank_program_status() throws Exception {
		logger.info("@User sends the request with blank creationTime and lasModTime");
		requestSpecificationPost();
	}

	@When("User sends the request with invalid crationTime and lastModTime")
	public void user_sends_the_request_with_invalid_cration_time_and_last_mod_time() throws Exception {
		logger.info("@User sends the request with blank creationTime and lasModTime");
		requestSpecificationPost();
	}

	@When("User sends the request with Numeric ProgramName")
	public void user_sends_the_request_with_numeric_program_name() throws Exception {
		logger.info("@User sends the request with Numeric ProgramName");
		requestSpecificationPost();
	}

	@Then("User should receive success status code and Message for post")
	public void user_should_receive_success_status_code_and_message_for_post() {
		logger.info("@Then User should receive success status code and message for post");
		String responseBody = response.prettyPrint();
		JsonPath js = response.jsonPath();
		System.out.println("New Program Created is :" + js.get("programId"));
		// Post Schema Validation
		// assertThat(responseBody, matchesJsonSchemaInClasspath("ProgramPost.json"));

		// Status code validation
		assertEquals(201, response.statusCode());
		logger.info("Response Status code is =>  " + response.statusCode());
		logger.info("Response Body is =>  " + responseBody);
	}

	@When("User sends request with dynamically generated data")
	public void user_sends_request_with_dynamically_generated_data() {
		logger.info("@User sends the request with Numeric ProgramName");
		requestSpec.header("Content-Type", "application/json");
		String randomnumber = RandomStringUtils.randomNumeric(4);
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

}
