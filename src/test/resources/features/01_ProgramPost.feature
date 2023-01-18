@ProgramPost
Feature: Post Program Feature

  @ProgramPost_01
  Scenario: Check if the user able to create new Program
    Given User is on Post Method with endpoint
    When User sends request with valid inputs
    Then User should receive status code and message for post

  @ProgramPost_02
  Scenario: Create new Program with duplicate record
    Given User is on Post Method with endpoint
    When User sends the request with already available program name
    Then User should receive status code and message for post

  @ProgramPost_03
  Scenario: To create Program with blank fields
    Given User is on Post Method with endpoint
    When User sends the request with blank fields
    Then User should receive status code and message for post

  @ProgramPost_04
  Scenario: To create Program record with blank creationTime and lasModTime
    Given User is on Post Method with endpoint
    When User sends the request with blank creationTime and lasModTime
    Then User should receive status code and message for post

  @ProgramPost_05
  Scenario: To create Program record with blank ProgramName
    Given User is on Post Method with endpoint
    When User sends the request with blank ProgramName
    Then User should receive status code and message for post

  @ProgramPost_06
  Scenario: To create record with blank ProgramStatus
    Given User is on Post Method with endpoint
    When User sends the request with blank ProgramStatus
    Then User should receive status code and message for post

  @ProgramPost_07
  Scenario: To create record with invalid crationTime and lastModTime
    Given User is on Post Method with endpoint
    When User sends the request with invalid crationTime and lastModTime
    Then User should receive status code and message for post

  @ProgramPost_08
  Scenario: To create record with Numeric ProgramName
    Given User is on Post Method with endpoint
    When User sends the request with Numeric ProgramName
    Then User should receive status code and message for post

  @ProgramPost_08
  Scenario: Check if the user able to create dyanamically new Program
    Given User is on Post Method with endpoint
    When User sends request with dynamically generated data
    Then User should receive success status code and Message for post
