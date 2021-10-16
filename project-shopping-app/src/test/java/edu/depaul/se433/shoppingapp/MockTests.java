package edu.depaul.se433.shoppingapp;
import static edu.depaul.se433.shoppingapp.ShippingType.NEXT_DAY;
import static edu.depaul.se433.shoppingapp.ShippingType.STANDARD;
import static org.mockito.ArgumentMatchers.endsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import org.h2.jdbc.JdbcSQLException;
import org.jdbi.v3.core.Jdbi;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.*;
import javax.management.Query;
import javax.persistence.EntityNotFoundException;
import org.apache.tomcat.jni.Local;
import org.h2.jdbc.JdbcSQLDataException;
import org.jdbi.v3.core.JdbiException;
import org.jdbi.v3.core.result.NoResultsException;
import org.jdbi.v3.core.statement.StatementException;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MockTests {


  @Test
  @DisplayName("Tests that the averagePurchase() function works correctly")
  public void testAvgPurchase(){
    List<Purchase> mockList = new ArrayList<>();
    Purchase fakePurchase1 = Purchase.make("Mike", LocalDate.now(), 45.50, "IL", "STANDARD");
    Purchase fakePurchase2 = Purchase.make("Mike", LocalDate.now(), 20.0, "IL", "STANDARD");
    Purchase fakePurchase3 = Purchase.make("Mike", LocalDate.now(), 15.99, "IL", "NEXT_DAY");
    mockList.add(fakePurchase1);
    mockList.add(fakePurchase2);
    mockList.add(fakePurchase3);
    PurchaseDBO mockDB = mock(PurchaseDBO.class);
    when(mockDB.getPurchases(anyString())).thenReturn(mockList);

    PurchaseAgent testAgent = new PurchaseAgent(mockDB);
    double result = testAgent.averagePurchase("Mike");
    double expected = (fakePurchase1.getCost() + fakePurchase2.getCost() + fakePurchase3.getCost()) / mockList.size();
    assertEquals(expected, result);


  }

  @Test
  @DisplayName("Verifies that PurchaseAgent makes the correct call to PurchaseDBO in the save() function.")
  public void testSave(){
    PurchaseDBO mockDB = mock(PurchaseDBO.class);
    Purchase fakePurchase = Purchase.make("Mike", LocalDate.now(), 75.0, "IL", "STANDARD");
    PurchaseAgent testAgent = new PurchaseAgent(mockDB);
    testAgent.save(fakePurchase);
    verify(mockDB).savePurchase(fakePurchase);
  }

  @Test
  @DisplayName("Tests that getting a nonexistent customer's purchases returns nothing")
  public void testEmptyDB(){
    PurchaseDBO mockDB = mock(PurchaseDBO.class);
    JdbiException e = new UnableToExecuteStatementException("Error");
    when(mockDB.getPurchases(anyString())).thenThrow(e);
    PurchaseAgent testAgent = new PurchaseAgent(mockDB);
    List expected = new ArrayList<>();
    assertEquals(expected,testAgent.getPurchases("Mike"));
  }

  @Test
  @DisplayName("Test the average price is 0 for a customer with no purchases")
  public void testNoPurchases(){
    PurchaseDBO mockDB = mock(PurchaseDBO.class);
    List<Purchase> arg = new ArrayList<>();
    when(mockDB.getPurchases(anyString())).thenReturn(arg);
    PurchaseAgent testAgent = new PurchaseAgent(mockDB);
    assertEquals(0.0, testAgent.averagePurchase("Mike"));
  }
}
