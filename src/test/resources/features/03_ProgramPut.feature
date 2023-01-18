@ProgramPut
Feature: Put program feature


  @ProgramPut_01
  Scenario: To update program by program id
    Given User is on PUT method with endpoint Program id
    When User sends put request with valid inputs for id
   	Then User should receive proper status code and valid reponse
    And JSON schema of response should be valid

 @ProgramPut_02
	Scenario: To update program by program name
   Given User is on PUT method with endpoint Program name
   When User sends put request with valid inputs for name
   Then User should receive proper status code and valid reponse
   And JSON schema of response should be valid
    
 @ProgramPut_03 
 Scenario: To update program with already available program name
 Given User is on PUT method with endpoint Program id
 When User sends request with duplicate program name 
 Then User should receive proper status code and message
    
@ProgramPut_04
 Scenario: To update record with alphanumeric program name, description and status
 Given User is on PUT method with endpoint Program id
 When User sends inputs with alphanumeric values in program name, description and status
 Then User should receive proper status code and valid reponse
 And JSON schema of response should be valid

@ProgramPut_05
 Scenario: To update record with invalid creation time and last mod time
 Given User is on PUT method with endpoint Program id
 When User sends request with invalid creation time and last mod time 
 Then User should receive proper status code and message
 
 @ProgramPut_06  
 Scenario: To update record by sending invalid program id
  Given User is on PUT method with endpoint Program id
  When User sends put request with invalid inputs for id
  Then User should receive proper status code and message
 
 @ProgramPut_07   
 Scenario: To update record by sending invalid program name
  Given User is on PUT method with endpoint Program name
  When User sends put request with invalid inputs for name
  Then User should receive proper status code and message
  
  