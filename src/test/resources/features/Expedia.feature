Feature: Overall reservation check

  @General
  @myTests
  @LearningAutomationTestingIsSooooMuchFun;

  Scenario: User does all the reservation steps from picking the destination place to clicking the payment confirmation button

    Given the user is on the booking page
    When user is in "Warsaw"
    And user wants to go to "New York, NY"
    And user will need a car
    And user is traveling with one child
    And user submits the inserted data


  Scenario Outline: User is going from starting location to destination

    Given the user is on the booking page
    When user sets the destination city as <destination>
    And user sets starting city as <startingLocationCity>

    Examples:
      | destination  | startingLocationCity |
      | New York, NY | Warsaw               |
      | Ohio         | Krakow               |


# @When("user is in {string}") - Regular Expression is NOT used
# {string} variable is a input for the step
# When user is in "London"

# @When("^user sets the destination city as (/*)$")  - Regular Expression (/*) is used
# declared with ^ and & inside double quotes in step declaration
# When user sets the destination city as <destination>