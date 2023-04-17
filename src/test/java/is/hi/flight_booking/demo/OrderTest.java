package is.hi.flight_booking.demo;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * OrderTest
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderTest {

  @Test
  @Order(1)
  public void test1() {
    System.out.println("1");
  }

  @Test
  @Order(2)
  public void test2() {
    System.out.println("2");
  }

  @Test
  @Order(3)
  public void test3() {
    System.out.println("3");
  }
}
