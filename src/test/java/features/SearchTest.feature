Feature: As a user I want to test search option on Genie Solutions website
  Scenario Outline: Testing search engine in different browsers with different values of search string
    Given I am on home page of Genie Solutions website in "<browser>" browser
    When I click the search magnifying glass
    Then the search input box should appear
    When I input search string "<searchString>" in search box
    And I press the search magnifying glass in search box
    Then I should see "<numberOfSearchResults>" results in result pane

    Examples:
    |browser|searchString|numberOfSearchResults|
    |chrome|I|12|
    |firefox|sol|4|
    |edge|Genie|16|
    |chrome|Gentu|2|
    |firefox|Genie Solutions|16|
    |edge|what's|2|
    |chrome|errored|no|
    |firefox|.|atleast one|
    |edge||error|



