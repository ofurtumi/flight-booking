package is.hi.flight_booking.application;

import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class FlightTest {
  Flight flight;
  Seat[] seats;

  @Before
  public void setUp() {
    seats = new Seat[] {
        new Seat("A-1", "F-304", false), new Seat("A-2", "F-304", false),
        new Seat("A-3", "F-304", false), new Seat("B-1", "F-304", false),
        new Seat("B-2", "F-304", false), new Seat("B-3", "F-304", false) };

    flight = new Flight(
        "F-304",
        seats,
        "Keflavík",
        "Reykjavík",
        LocalDate.now(),
        LocalDate.now(),
        24000);
  }

  @After
  public void tearDown() {
    seats = null;
    flight = null;
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
}
