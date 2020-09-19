Feature: Testing karros page

  Background: Open browser and login page
    Given open the browser
    And user is on login page

  Scenario Outline: Verify filter Student Access Request with INACTIVE
    When user inputs <username> and <password>
    And user clicks Login button
    And user verifies filter Student Access Request with Inactive status
    Then user closes the browser

    Examples: 
      | username       | password |
      | admin@test.com | test123  |

  Scenario Outline: Verify sorting of First Name column
    When user inputs <username> and <password>
    And user clicks Login button
    Then user clicks on "DSC" arrow button of "First Name" column in the table
    And user verifies sorting of "First Name" column is sorted in "DSC" order
    Then after that user clicks on "ASC" arrow button of "First Name" column in the table
    And user continue verifies sorting of "First Name" column is sorted in "ASC" order
    Then browser is closed

    Examples: 
      | username       | password |
      | admin@test.com | test123  |

  Scenario: Connect to API and get data with GET method
    Given user connects to API and get data
    Then close the browser
