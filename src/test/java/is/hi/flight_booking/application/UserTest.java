import org.junit.*;
import static org.junit.Assert.*;

import is.hi.flight_booking.application.User;

public class UserTest {
	User user;
	@Before
	public void setUp() {
		user = new User("", "John Doe");
	}

	@After
	public void tearDown() {
		user = null;
	}

	@Test
	public void testName() {
		assertEquals(user.getName(), "John Doe");
	}
}
