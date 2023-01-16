Feature: buying
  Scenario:
    Given browser on loggin in page
    When user logs in and goes to the clothes section
    Then Selects item he wants to buy and fixes all parameters
    When User selects all the necessary delivery and paytment options
    And User sees order confirmation

