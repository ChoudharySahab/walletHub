Feature: Facebook posting as per assignment
Background: basic Loader
  Given Load Driver and basic params

  Scenario Outline: Test FACEBOOK post functionality
    Given User logs in to facebook with "<emailId>" as username and "<password>" as password
    And user clicks on login button to move in to profile
    And user posts "Hello World!" in post senciton
    Then user logs out facebook and quit driver session

Examples:
|emailId|password|postBody|
|rohitsingh7991@gmail.com|Rohit7991@|Hello World!|