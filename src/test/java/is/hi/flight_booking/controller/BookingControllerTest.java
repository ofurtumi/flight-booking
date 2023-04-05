package is.hi.flight_booking.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.application.User;

public class BookingControllerTest {
  private BookingController BC;
  private Flight flight;
  private ArrayList<Seat> seats;
  private User user;
  private Booking ref;
  private Booking testBooking;

  @Before
  public void setUp() {
    seats = new ArrayList<>();
    BC = new BookingController("db/test.db");

    user = new User("0609013170", "Tumi");

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
    ref = new Booking(flight, user, "B-0609013170-000", seats);
    testBooking = BC.createBooking(flight, user, seats);
  }

  @Test
  public void TestCreate() {
    assertEquals(ref.getUser(), testBooking.getUser());
    assertEquals(ref.getPrice(), testBooking.getPrice());
    assertEquals(ref.getFlight(), testBooking.getFlight());
    assertEquals(ref.getSeats(), testBooking.getSeats());

    assertEquals(ref, testBooking);
  }

  @Ignore
  @Test
  public void isOnDB() {
    assertTrue(BC.exists(testBooking));
  }

  @Ignore
  @Test
  public void TestDelete() {
    BC.deleteBooking(testBooking);
    assertFalse(BC.exists(testBooking));
  }

  @After
  public void tearDown() {
    BC = null;
    ref = null;
    testBooking = null;
    seats = null;
    flight = null;
  }

}
