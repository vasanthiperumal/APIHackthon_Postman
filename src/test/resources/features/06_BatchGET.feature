@BatchGET
Feature: Batch GET feature

  @BatchGET_01
  Scenario: Check if the user able to read all the Batches
    Given User is on GET method with endpoint batch
    When User sends the GET request with valid inputs
    Then User is able to read all Batch details and receive status code for get

 @BatchGET_02
  Scenario: Get Batch details for a specific BatchId
    Given User is on GET method with endpoint with single BatchId
    When User sends the GET request with valid BatchId
    Then User should see the Batch details for the given BatchId

  @BatchGET_03
  Scenario: Get Batch details for a invalid BatchId
    Given User is on GET method with endpoint with single BatchId
    When User sends the GET request with invalid BatchId
    Then User should receive error status code and message for Get

  @BatchGET_04
  Scenario: Get Batch details for a BatchId as alphanumeric
    Given User is on GET method with endpoint with single BatchId
    When User sends GET the request with alphanumeric BatchId
    Then User should receive error status code and message for Get

  @BatchGET_05
  Scenario: Get Batch details with BatchId as blank
    Given User is on GET method with endpoint with single BatchId
    When User sends the request with blank BatchId
    Then User should receive error status code and message for Get

  @BatchGET_06
  Scenario: Get Batch details with BatchId as Decimal
    Given User is on GET method with endpoint with single BatchId
    When User sends the request with decimal BatchId
    Then User should receive error status code and message for Get

 @BatchGET_07
  Scenario: GET Batch details with a specific ProgramId
   Given User is on GET method with endpoint ProgramId
    When User sends the request with valid ProgramId
    Then User should see the Batch details for the given ProgramId

  @BatchGET_08
  Scenario: Get Batch details with valid BatchName
    Given User is on GET method with endpoint batchName
    When User sends GET request with valid BatchName
    Then User should see the Batch details for the given BatchName

  @BatchGET_09
  Scenario: GET Batch details for a Invalid ProgramId
    Given User is on GET method with endpoint ProgramId
    When User sends the request with Invalid ProgramId
    Then User should receive error status code and message for Get

  @BatchGET_10
  Scenario: Get Batch details with Invalid BatchName
    Given User is on GET method with endpoint batchName
    When User sends GET request with Invalid BatchName
    Then User should receive error status code and message for Get

  @BatchGET_11
  Scenario: Get Batch details with blank BatchName
    Given User is on GET method with endpoint batchName
    When User sends GET request with blank BatchName
    Then User should receive error status code and message for Get
