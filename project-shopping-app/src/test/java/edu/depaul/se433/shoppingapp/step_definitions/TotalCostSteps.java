package edu.depaul.se433.shoppingapp.step_definitions;
import edu.depaul.se433.shoppingapp.ShippingType;
import edu.depaul.se433.shoppingapp.TaxCalculator;
import edu.depaul.se433.shoppingapp.TotalCostCalculator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TotalCostSteps {
  private double initialCost = 0.0;
  private String state = "";
  private ShippingType shipping;

  private double itemCost = 0.0;
  private String taxState = "";

  private TotalCostCalculator totalCostCalculator = new TotalCostCalculator();
  private TaxCalculator taxCalculator = new TaxCalculator();

  @Given("The cart has an initial cost of {double} dollars")
  public void the_cart_has_an_initial_cost_of_dollars(Double double1) {
    // Write code here that turns the phrase above into concrete actions
    initialCost = double1;
  }

  @And("the given state for which to be shipped in is {string}")
  public void the_given_state_for_which_to_be_shipped_in_is(String string) {
    // Write code here that turns the phrase above into concrete actions
    state = string;
  }

  @And("the requested shipping type is {string}")
  public void the_requested_shipping_type_is_shipping_type_standard(String string) {
    // Write code here that turns the phrase above into concrete actions
    if(string.equals("STANDARD"))
      shipping = ShippingType.STANDARD;
    else if(string.equals("NEXT_DAY"))
      shipping = ShippingType.NEXT_DAY;
  }

  @Then("the total cost of the purchase is {double} dollars")
  public void the_total_cost_of_the_purchase_is_dollars(Double double1) {
    // Write code here that turns the phrase above into concrete actions
    double result = totalCostCalculator.calculate(initialCost,state,shipping);
    assertEquals(double1,result);
  }

  @Given("The item has an initial cost of {double} dollars")
  public void the_item_has_an_initial_cost_of_dollars(Double double1) {
    // Write code here that turns the phrase above into concrete actions
    itemCost = double1;
  }

  @And("the given state is {string}")
  public void the_given_state_is(String string) {
    // Write code here that turns the phrase above into concrete actions
    taxState = string;
  }

  @Then("the calculated tax should be {double} cents")
  public void the_calculated_tax_should_be_cents(Double double1) {
    // Write code here that turns the phrase above into concrete actions
    double result = taxCalculator.calculate(itemCost,taxState);
    assertEquals(double1, result);
  }
}
