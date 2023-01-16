Feature: Login in:

  Scenario Outline:Logging in test
    Given browser on logging in page
    When User logs in
    Then User is on address site
    Then He fills in <Address>, <City> and <ZipPostalCode>
    And User is on the addresses page and close browser

    Examples:
    |Address|City|ZipPostalCode|
    |Markowska|Warszawa|32150  |