Feature: WalletHub Testing as per assignment
Background: basic Loader
  Given Load Driver and basic params

  Scenario: Test WalletHub Functionality
    Given User moves to url "https://wallethub.com/profile/test_insurance_company/"
    When User clicks on review section
    And User howers over start and view change in clour on hovering over stars
    And User clicks on 4 star
    Then user moves to policy dropdown and select "Health Insurance" from dropdown menu
    And User enters random value for review of 201 words
    And user clicks on submit button
    And User logs into the webpage
    And on successful submission user to be able to see "You have reviewed the institution"
    Then User move to profile and confirm review feed got updated
    And User move to "https://wallethub.com/profile/" and asset your review got updated

