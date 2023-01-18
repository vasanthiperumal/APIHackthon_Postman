@DynamicScenarioProgram
Feature: Parameters to be generated dynamically

  @DSProgram_01
  Scenario: Check if the user able to create dyanamically new Program
    Given User is on Post Program Method with endpoint
    When User sends Post Program request with dynamically generated data
    Then User should receive created successfully message and status code

  @DSProgram_02
  Scenario: Check if the user able to Get dyanamically created Program
    Given User is on Get Program Method with endpoint
    When User sends Post Program request Get dyanamically created Program
    Then User should get newly created programs

  @DSProgram_03
  Scenario: Check if the user able to Update dyanamically created Program
    Given User is on Put Program Method with endpoint
    When User sends Post Program request Update dyanamically created Program
    Then User should update newly created programs

  @DSProgram_04
  Scenario: Check if the user able to Delete dyanamically created Program
    Given User is on Delete Program Method with endpoint
    When User sends Post Program request Delete dyanamically created Program
    Then User should Delete newly created programs
