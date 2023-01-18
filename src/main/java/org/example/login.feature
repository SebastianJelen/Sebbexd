Feature: Login in:

  Scenario Outline:Logging in test
    Given browser on logging in page
    When User logs in
    Then User is on address site
    Then He fills in <Address>, <City>, <ZipPostalCode>, <Alias>, <Country> and <Phone>
    And User is on the addresses page and closes browser

    Examples:
    |Address|City|ZipPostalCode|Alias|Country|Phone|
    |Markowska|Warszawa|32150  |Eric |United |123-456-789|