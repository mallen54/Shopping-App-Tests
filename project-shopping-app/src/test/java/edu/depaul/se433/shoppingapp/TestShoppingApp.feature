Feature: Tests calculation features of ShoppingApp
  Scenario: An initial price of 60 dollars with IL and STANDARD is passed to TotalCostCalculator
    Given The cart has an initial cost of 60.0 dollars
    And the given state for which to be shipped in is "IL"
    And the requested shipping type is "STANDARD"
    Then the total cost of the purchase is 63.6 dollars

    Scenario: An initial price of 15 dollars with FL and NEXT_DAY is passed to TotalCostCalculator
      Given The cart has an initial cost of 15.0 dollars
      And the given state for which to be shipped in is "FL"
      And the requested shipping type is "NEXT_DAY"
      Then the total cost of the purchase is 40.0 dollars

      Scenario: An initial price of 10 dollars with NY is passed to TaxCalculator
        Given The item has an initial cost of 10.0 dollars
        And the given state is "NY"
        Then the calculated tax should be .6 cents