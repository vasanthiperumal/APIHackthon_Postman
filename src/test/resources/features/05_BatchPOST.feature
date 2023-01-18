@BatchPost
Feature: Batch POST feature

  @BatchPOST_01:
  Scenario: Check if the user able to create new Batch with valid data
    Given User is on POST Method with endpoint
    When User sends request with valid data
    Then User should receive status code and message for POST

 @BatchPOST_02:
  Scenario: Create Batch record with blank fields
    Given User is on POST Method with endpoint
    When User sends request with all inputs blank
    Then User should receive status code and message for POST

 @BatchPOST_03:
  Scenario: Create new Batch with  already existing BatchName
    Given User is on POST Method with endpoint
    When User sends the POST request with available BatchName
    Then User should receive status code and message for POST

  @BatchPOST_04:
  Scenario: Create new Batch with Invalid JSON schema
    Given User is on POST Method with endpoint
   When User sends a POST request with invalid JSON schema
    Then User should receive status code and message for POST

  @BatchPOST_05
  Scenario: Create Batch with numeric BatchName,Description and Status
    Given User is on POST Method with endpoint
   When User sends POST request with numeric BatchName,Description and Status
    Then User should receive status code and message for POST

  @BatchPOST_06
  Scenario: Create a Batch with Alphanumric Batch NoOfClasses
    Given User is on POST Method with endpoint
    When User sends POST request with Alphanumeric Batch NoOfClasses
    Then User should receive status code and message for POST

 @BatchPOST_07
  Scenario: Create a Batch with NoOfClasses as negative number
    Given User is on POST Method with endpoint
    When User sends POST request with Batch NoOfClasses Negative number
    Then User should receive status code and message for POST

  @BatchPOST_08
  Scenario: Create Batch with invalid programId
    Given User is on POST Method with endpoint
    When User sends POST request with invalid programId
    Then User should receive status code and message for POST

  @BatchPOST_09
 Scenario: Create Batch with BatchName as blank
    Given User is on POST Method with endpoint
    When User sends POST request with BatchName as blank
    Then User should receive status code and message for POST

  @BatchPOST_10
  Scenario: Create Batch with Batch description as blank
    Given User is on POST Method with endpoint
    When User sends POST request Batch description as blank
    Then User should receive status code and message for POST

  @BatchPOST_11
  Scenario: Create Batch with status as blank
    Given User is on POST Method with endpoint
    When User sends POST request with Batch status as blank
    Then User should receive status code and message for POST

  @BatchPOST_12
  Scenario: Create Batch with NoOfClasses as blank
    Given User is on POST Method with endpoint
    When User sends POST request with Batch NoOfclasses as blank
    Then User should receive status code and message for POST

  @BatchPOST_13:
  Scenario: Create Batch with programId as blank
    Given User is on POST Method with endpoint
    When User sends POST request with programId as blank
    Then User should receive status code and message for POST

  @BatchPOST_14
  Scenario: Create Batch with valid programId
    Given User is on POST Method with endpoint
    When User sends POST request with valid programId
    Then User should receive status code and message for POST
