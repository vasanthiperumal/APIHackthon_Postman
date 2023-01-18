@DynamicScenarioBatch
Feature: Parameters to be generated dynamically

  @DSBatch_01
  Scenario: Check if the user able to create dyanamically new Batch
    Given User is on Post Batch Method with endpoint
    When User sends Post Batch request with dynamically generated data
    Then User should receive Batch created successfully message and  status code

  @DSBatch_02
  Scenario: Check if the user able to Get dyanamically created Batch
    Given User is on Get Batch Method with endpoint
    When User sends Post Batch request Get dyanamically created Batch
    Then User should get newly created Batch

  @DSBatch_03
  Scenario: Check if the user able to Update dyanamically created Batch
    Given User is on Put Batch Method with endpoint
    When User sends Post Batch request Update dyanamically created Batch
    Then User should update newly created Batch

  @DSBatch_04
  Scenario: Check if the user able to Delete dyanamically created Batch
    Given User is on Delete Batch Method with endpoint
    When User sends Post Batch request Delete dyanamically created Batch
    Then User should Delete newly created Batch
