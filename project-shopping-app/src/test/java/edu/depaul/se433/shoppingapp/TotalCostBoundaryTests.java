package edu.depaul.se433.shoppingapp;
import static edu.depaul.se433.shoppingapp.ShippingType.NEXT_DAY;
import static edu.depaul.se433.shoppingapp.ShippingType.STANDARD;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import java.util.stream.Stream;


public class TotalCostBoundaryTests {

  private TotalCostCalculator calculator;
  @BeforeEach
  void setup(){
    //sets up the TotalCostCalculator for testing
    calculator = new TotalCostCalculator();
  }

////////////////////// START STANDARD IL TESTS //////////////////////////////////
  @Test
  @DisplayName("Tests total cost calculated on a cart at the minimum allowed total with standard for IL")
  void testMinIL_standard(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",1,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "IL", STANDARD);
    Bill expected = new Bill(1,10,.06,11.06);
    assertEquals(expected, cost);
  }

  @Test
  @DisplayName("Tests total cost calculated on a cart slightly above its minimum allowed total with standard for CA")
  void testAboveMinCA_standard(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",1.01,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "CA", STANDARD);
    Bill expected = new Bill(1.01,10,.06,11.07);
    assertEquals(expected, cost);
  }


  @Test
  @DisplayName("Tests total cost calculated on a cart that is in the nominal range with standard for IL")
  void testNominalIL_standard(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",49999.99,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "IL", STANDARD);
    Bill expected = new Bill(49999.99,0,3000,52999.99);
    assertEquals(expected, cost);
  }

  @Test
  @DisplayName("Tests total cost calculated on a cart that is just below the max allowed cost with standard for IL")
  void testBelowMaxIL_standard(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",99999.98,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "IL", STANDARD);
    Bill expected = new Bill(99999.98,0,6000,105999.98);
    assertEquals(expected, cost);
  }


  @Test
  @DisplayName("Tests total cost calculated on a cart that is at the max allowed cost with standard for NY")
  void testMaxNY_standard(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",99999.99,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "NY", STANDARD);
    Bill expected = new Bill(99999.99,0,6000,105999.99);
    assertEquals(expected, cost);
  }

  //////////////////// START NEXT DAY IL TESTS //////////////////////////////

  @Test
  @DisplayName("Tests total cost calculated on a cart at the minimum allowed total with next day for IL")
  void testMinIL_nextday(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",1,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "IL", NEXT_DAY);
    Bill expected = new Bill(1,25,.06,26.06);
    assertEquals(expected, cost);
  }

  @Test
  @DisplayName("Tests total cost calculated on a cart slightly above its minimum allowed total with next day for CA")
  void testAboveMinCA_nextday(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",1.01,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "CA", NEXT_DAY);
    Bill expected = new Bill(1.01,25,.06,26.07);
    assertEquals(expected, cost);
  }


  @Test
  @DisplayName("Tests total cost calculated on a cart that is in the nominal range with next day for IL")
  void testNominalIL_nextday(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",49999.99,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "IL", NEXT_DAY);
    Bill expected = new Bill(49999.99,25,3000,53024.99);
    assertEquals(expected, cost);
  }

  @Test
  @DisplayName("Tests total cost calculated on a cart that is just below the max allowed cost with next day for IL")
  void testBelowMaxIL_nextday(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",99999.98,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "IL", NEXT_DAY);
    Bill expected = new Bill(99999.98,25,6000,106024.98);
    assertEquals(expected, cost);
  }


  @Test
  @DisplayName("Tests total cost calculated on a cart that is at the max allowed cost with next day for NY")
  void testMaxNY_nextday(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",99999.99,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "NY", NEXT_DAY);
    Bill expected = new Bill(99999.99,25,6000,106024.99);
    assertEquals(expected, cost);
  }


  //////////////////////// START STANDARD WI TESTS ///////////////////////////////////


  @Test
  @DisplayName("Tests total cost calculated on a cart at the minimum allowed total with standard for WI")
  void testMinWI_standard(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",1,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "WI", STANDARD);
    Bill expected = new Bill(1,10,0,11.0);
    assertEquals(expected, cost);
  }

  @Test
  @DisplayName("Tests total cost calculated on a cart slightly above its minimum allowed total with standard for WI")
  void testAboveMinWI_standard(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",1.01,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "WI", STANDARD);
    Bill expected = new Bill(1.01,10,0,11.01);
    assertEquals(expected, cost);
  }


  @Test
  @DisplayName("Tests total cost calculated on a cart that is in the nominal range with standard for WI")
  void testNominalWI_standard(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",49999.99,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "WI", STANDARD);
    Bill expected = new Bill(49999.99,0,0,49999.99);
    assertEquals(expected, cost);
  }

  @Test
  @DisplayName("Tests total cost calculated on a cart that is just below the max allowed cost with standard for WI")
  void testBelowMaxWI_standard(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",99999.98,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "WI", STANDARD);
    Bill expected = new Bill(99999.98,0,0,99999.98);
    assertEquals(expected, cost);
  }


  @Test
  @DisplayName("Tests total cost calculated on a cart that is at the max allowed cost with standard for WI")
  void testMaxWI_standard(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",99999.99,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "WI", STANDARD);
    Bill expected = new Bill(99999.99,0,0,99999.99);
    assertEquals(expected, cost);
  }


  /////////////////////// START NEXT DAY WI TESTS /////////////////////////////////

  @Test
  @DisplayName("Tests total cost calculated on a cart at the minimum allowed total with next day for WI")
  void testMinWI_nextday(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",1,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "WI", NEXT_DAY);
    Bill expected = new Bill(1,25,0,26.0);
    assertEquals(expected, cost);
  }

  @Test
  @DisplayName("Tests total cost calculated on a cart slightly above its minimum allowed total with next day for WI")
  void testAboveMinWI_nextday(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",1.01,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "WI", NEXT_DAY);
    Bill expected = new Bill(1.01,25,0,26.01);
    assertEquals(expected, cost);
  }


  @Test
  @DisplayName("Tests total cost calculated on a cart that is in the nominal range with next day for WI")
  void testNominalWI_nextday(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",49999.99,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "WI", NEXT_DAY);
    Bill expected = new Bill(49999.99,25,0,50024.99);
    assertEquals(expected, cost);
  }

  @Test
  @DisplayName("Tests total cost calculated on a cart that is just below the max allowed cost with next day for WI")
  void testBelowMaxWI_nextday(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",99999.98,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "WI", NEXT_DAY);
    Bill expected = new Bill(99999.98,25,0,100024.98);
    assertEquals(expected, cost);
  }


  @Test
  @DisplayName("Tests total cost calculated on a cart that is at the max allowed cost with next day for WI")
  void testMaxWI_nextday(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item = new PurchaseItem("testItem",99999.99,1);
    cart.addItem(item);
    Bill cost = calculator.calculate(cart, "WI", NEXT_DAY);
    Bill expected = new Bill(99999.99,25,0,100024.99);
    assertEquals(expected, cost);
  }

  @Test
  @DisplayName("Test TotalCostCalculator's alternate calculate method")
  void testCalculate2(){
    double result = calculator.calculate(30, "IL", STANDARD);
    assertEquals(41.8, result);
  }
}
