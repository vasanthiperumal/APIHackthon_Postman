@BatchDELETE
Feature: Delete Batch feature

  @BatchDELETE_01
  Scenario: To delete existing batch by BatachId
    Given User is on DELETE method with endpoint Batch
    When User sends request with existing batch by BatachId
    Then User receive status code and message for DELETE

  @BatchDELETE_02
  Scenario: To delete batch by nonexisting BatachId
    Given User is on DELETE method with endpoint Batch
    When User sends request with nonexisting BatchId
    Then User receive status code and message for DELETE

  @BatchDELETE_03
  Scenario: To delete the batch without passing the BatchId
    Given User is on DELETE method with endpoint Batch
    When User sends request without passing the BatchId
    Then User receive status code and message for DELETE

  @BatchDELETE_04
  Scenario: To delete existing Batch by BatchName
    Given User is on DELETE method with endpoint Batch
    When User sends request with existing BatchName
    Then User receive status code and message for DELETE

  @BatchDELETE_05
  Scenario: To delete non existing Batch by BatchName
    Given User is on DELETE method with endpoint Batch
    When User sends request with nonexisting BatchName
    Then User receive status code and message for DELETE
