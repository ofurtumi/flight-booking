package is.hi.flight_booking.application;

import org.junit.*;
import static org.junit.Assert.*;

public class UserTest {
  User user;

  @Before
  public void setUp() {
    user = new User("0609013170", "John Doe");
  }

  @After
  public void tearDown() {
    user = null;
  }

  @Test
  public void TestUserGet() {
    assertEquals(user.getName(), "John Doe");
    assertEquals(user.getId(), "0609013170");
  }
}
