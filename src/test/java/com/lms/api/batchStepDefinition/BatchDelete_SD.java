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
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BatchDelete_SD {


	RequestSpecification requestSpec;
	Response response;
	String BatchId;
	String BatchName;
	String path;
	String sheetDelete;
	String errorCode;

	ExcelReader excelReaderUtil;
	Scenario scenario;
	Properties properties;

	private static final Logger logger = LogManager.getLogger(BatchDelete_SD.class);

	public BatchDelete_SD()
	{
		ConfigReader config = new ConfigReader();
		properties = config.loadProperties();
	}

	@Before
	public void initializeDataTable(Scenario scenario) throws Exception {
		this.scenario = scenario;
		sheetDelete = properties.getProperty("sheetDelete");
		excelReaderUtil=new ExcelReader(properties.getProperty("batchExcelpath"));
		excelReaderUtil.readSheet(sheetDelete);
	}

	public void requestSpecificationDelete() {
		requestSpec.header("Accept", ContentType.JSON.getAcceptHeader()).contentType(ContentType.JSON);
		requestSpec.log().all();
		response = requestSpec.when().delete(path);
	
	
	//	response = requestSpec.when().delete(path);
	}


	@Given("User is on DELETE method with endpoint Batch")
	public void user_is_on_delete_method_with_endpoint_Batch() throws IOException {

		logger.info("@Given User is on DELETE method with endpoint Batch");
		RestAssured.baseURI=properties.getProperty("base_uri");
		requestSpec = RestAssured.given();
		String batchId = excelReaderUtil.getDataFromExcel(scenario.getName(), "BatchId");
		System.out.println("Batch id from Excel is==>" + batchId);
		path = properties.getProperty("endpointBatchDeleteId") + batchId;
		logger.info("BatchId from excel : " + batchId);
	}

	

	@When("User sends request with existing batch by BatachId")
	public void user_sends_request_with_existing_batch_by_Batch_Id() {
		requestSpecificationDelete();
	}


	@When("User sends request with nonexisting BatchId")
	public void user_sends_request_with_nonexisting_Batch_Id() {
		requestSpecificationDelete();
	}


	@When("User sends request without passing the BatchId")
	public void user_sends_request_without_passing_the_Batch_Id() {
		requestSpecificationDelete();
	}

	
	@When("User sends request with existing BatchName")
	public void user_sends_request_with_existing_Batch_Name() {
		requestSpecificationDelete();
	}


	@When("User sends request with nonexisting BatchName")
	public void User_sends_request_with_nonexisting_BatchName() {
		requestSpecificationDelete();
	}


	@Then("User receive status code and message for DELETE")
	public void user_receive_status_code_and_message_for_delete() throws IOException {
		logger.info("@Then User should receive status code and message for delete");

		String expStatusCode = excelReaderUtil.getDataFromExcel(scenario.getName(), "StatusCode");
		assertEquals(Integer.parseInt(expStatusCode), response.statusCode());
		logger.info("Actual Response Status code=>  " + response.statusCode() + "  Expected Response Status code=>  "
				+ expStatusCode);

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
