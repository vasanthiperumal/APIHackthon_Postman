@BatchPUT
Feature: Put Batch feature
 
  @BatchPUT_01
  Scenario: To update batch with valid BatchId
  Given User is on PUT method with endpoint
  When User sends request with valid BatchId 
  Then User should receive status code and message for PUT
 	    
  @BatchPUT_02
  Scenario: Update Batch details for a invalid BatchId
  Given User is on PUT method with endpoint
  When User sends the PUT request with invalid Batchid
  Then User should receive status code and message for PUT

  @BatchPUT_03
  Scenario: Update Batch details with Alphanumeric BatchId
  Given User is on PUT method with endpoint
  When User sends the PUT request with Alphanumeric BatchId
  Then User should receive status code and message for PUT
    
  @BatchPUT_04
  Scenario: Update Batch details with blank BatchId
  Given User is on PUT method with endpoint
  When User sends the PUT request with blank BatchId
  Then User should receive status code and message for PUT
  
  @BatchPUT_05
  Scenario: Update Batch details with decimal BatchId
  Given User is on PUT method with endpoint
  When User sends the PUT request with decimal BatchId
  Then User should receive status code and message for PUT
 
  @BatchPUT_06
  Scenario: Update Batch details with Negative BatchId 
  Given User is on PUT method with endpoint
  When User sends the PUT request with negative BatchId
  Then User should receive status code and message for PUT
  
  @BatchPUT_07
  Scenario: Update batch with valid BatchId and without JSON Schema
  Given User is on PUT method with endpoint
  When User sends the PUT request with valid BatchId and without valid Json Schema
  Then User should receive status code and message for PUT
  