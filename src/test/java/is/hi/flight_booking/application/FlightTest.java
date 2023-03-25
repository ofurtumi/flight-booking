package is.hi.flight_booking.application;

import org.junit.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

public class FlightTest {
  Flight flight;
  ArrayList<Seat> seats;

  @Before
  public void setUp() {
    seats = new ArrayList<>();
    seats.add(new Seat("A-1", "F-304", false));
    seats.add(new Seat("A-2", "F-304", false));
    seats.add(new Seat("A-3", "F-304", false));
    seats.add(new Seat("B-1", "F-304", false));
    seats.add(new Seat("B-2", "F-304", false));
    seats.add(new Seat("B-3", "F-304", false));

    flight = new Flight(
        "F-304",
        seats,
        "Keflavík",
        "Reykjavík",
        LocalDate.now(),
        LocalDate.now(),
        24000);
  }

  @Test
  public void testFlightGetArrivalAddress() {
    assertEquals("Reykjavík", flight.getArrivalAddress());
  }

  @Test
  public void testFlightGetDepartureAddress() {
    assertFalse("Reykjavík".equals(flight.getDepartureAddress()));
  }

  @Test
  public void testFlightPrice() {
    assertEquals(24000, flight.getPrice());
    flight.setPrice(30000);
    assertEquals(30000, flight.getPrice());
  }

  // verð að hafa þetta aftast til að bjarga geðheilsunni
  @After
  public void tearDown() {
    seats = null;
    flight = null;
  }
}
