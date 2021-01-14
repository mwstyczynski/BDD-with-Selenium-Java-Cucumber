Feature: Overall reservation check
    @General
    @myTests
    @LearningAutomationTestingIsSooooMuchFun;

    Scenario: User does all the reservation steps from picking the destination place to clicking the payment confirmation button

      Given the user is on the booking page
      When user sets the destination on trip details section of the booking page
      And user sets the starting location on trip details section of the booking page
      And user adds a car rent to be included to the bill
      And user adds one child in travelers section of the booking page
      And user submits the inserted data