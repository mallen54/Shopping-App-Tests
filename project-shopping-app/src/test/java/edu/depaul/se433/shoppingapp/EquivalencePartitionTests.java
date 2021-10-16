package edu.depaul.se433.shoppingapp;
import static edu.depaul.se433.shoppingapp.ShippingType.NEXT_DAY;
import static edu.depaul.se433.shoppingapp.ShippingType.STANDARD;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import java.util.stream.Stream;

public class EquivalencePartitionTests {
  private TotalCostCalculator costCalculator;

  @BeforeEach
  void setup(){
    //sets up the TotalCostCalculator for testing
    costCalculator = new TotalCostCalculator();
  }

  //////////////////Start Strong Normal Test Cases////////////////////

  private static Stream<Arguments> strongNormalInput(){
    ShoppingCart cart = new ShoppingCart();
    PurchaseItem item1 = new PurchaseItem("testItem1",10,1);
    PurchaseItem item2 = new PurchaseItem("testItem2",15,1);
    PurchaseItem item3 = new PurchaseItem("testItem3",20,1);
    cart.addItem(item1);
    cart.addItem(item2);
    cart.addItem(item3);

    return Stream.of(
        Arguments.of(cart, "IL", STANDARD, new Bill(45, 10, 2.7, 47.7)),
        Arguments.of(cart, "IL", NEXT_DAY, new Bill(45, 25, 2.7, 72.7)),
        Arguments.of(cart, "WI", STANDARD, new Bill(45, 10, 0, 55)),
        Arguments.of(cart, "WI", NEXT_DAY, new Bill(45, 25, 0, 70)));
  }

  @ParameterizedTest
  @MethodSource("strongNormalInput")
  @DisplayName("Test strong normal cases")
  public void testStrongNormal(ShoppingCart cart, String state, ShippingType shipping, Bill expected){
    Bill result = costCalculator.calculate(cart, state, shipping);
    assertEquals(expected, result);

  }

  //////////////////Start Weak Robust Test Cases////////////////////

  private static Stream<Arguments> weakRobustInput(){
    ShoppingCart cart1 = new ShoppingCart();
    PurchaseItem item1 = new PurchaseItem("testItem1",10,1);
    PurchaseItem item2 = new PurchaseItem("testItem2",15,1);
    PurchaseItem item3 = new PurchaseItem("testItem3",20,1);
    cart1.addItem(item1);
    cart1.addItem(item2);
    cart1.addItem(item3);
    ShoppingCart cart2 = new ShoppingCart();

    return Stream.of(
        Arguments.of(cart1, "IL", STANDARD, new Bill(45, 10, 2.7, 47.7)),
        Arguments.of(cart1, "WI", NEXT_DAY, new Bill(45, 25, 0, 70)),
        Arguments.of(cart2, "IL", STANDARD, new Bill(0, 0, 0, -50)),
        Arguments.of(cart1, "", STANDARD, new Bill(0, 0, 0, -50)),
        Arguments.of(cart1, "WI", null, new Bill(0, 0, 0, -50)));
  }


  @ParameterizedTest
  @MethodSource("weakRobustInput")
  @DisplayName("Test weak robust cases")
  public void testWeakRobust(ShoppingCart cart, String state, ShippingType shipping, Bill expected){
    if(expected.total() == -50){
      assertThrows(IllegalArgumentException.class, () -> costCalculator.calculate(cart, state, shipping));
    }
    else{
      Bill result = costCalculator.calculate(cart, state, shipping);
      assertEquals(expected, result);
    }
  }

}
