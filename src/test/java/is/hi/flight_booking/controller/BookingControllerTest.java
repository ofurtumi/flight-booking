package is.hi.flight_booking.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;

import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.application.User;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookingControllerTest {
  private BookingController BC;
  private Flight flight;
  private ArrayList<Seat> seats;
  private User user;
  private Booking ref;

  @Before
  public void setUp() {
    seats = new ArrayList<>();
    BC = new BookingController("db/test.db");

    user = new User("3010853549", "Jón Ragnar Jónsson");

    seats.add(new Seat("A1", "F-000", true));
    seats.add(new Seat("A2", "F-000", true));
    seats.add(new Seat("B1", "F-000", true));
    seats.add(new Seat("B2", "F-000", true));
    flight = new Flight(
        "F-000",
        seats,
        "Egilsstaðir",
        "Akureyri",
        LocalDate.of(2023, 2, 5),
        LocalDate.of(2023, 2, 5),
        15000);
    ref = new Booking(flight, user, "B-3010853549-000", seats);
  }

  @Test
  @Order(1)
  public void TestCreate() {
    System.out.println("Test: 1");
    Booking testBooking = BC.createBooking(flight, user, seats);
    assertEquals(ref, testBooking);
  }

  @Test
  @Order(2)
  public void isOnDB() {
    try {
      System.out.println("Test: 2");
      assertTrue(BC.exists(ref));
    } catch (Exception e) {
      System.err.printf("Klikkaði að finna á DB: \n%s", e);
    }
  }

  @Test
  @Order(3)
  public void getBooking() {
    System.out.println("Test: 3");
    ArrayList<Booking> testBookings = new ArrayList<Booking>(BC.getBookings("B-3010853549-000"));
    try {
      assertEquals(ref, testBookings.get(0));
    } catch (Exception e) {
      System.err.printf("Klikkaði að finna á DB: \n%s", e);
    }
  }

  @Test
  @Order(4)
  public void isNotOnDB() {
    try {
      System.out.println("Test: 4");
      BC.deleteBooking(ref);
      System.out.println("Á að koma Seats do not exist");
      assertFalse(BC.exists(ref));
    } catch (Exception e) {
      System.err.printf("Klikkaði að finna á DB: \n%s", e);
    }
  }

  @After
  public void tearDown() {
    BC = null;
    ref = null;
    seats = null;
    flight = null;
  }
}
