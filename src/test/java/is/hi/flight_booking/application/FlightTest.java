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
    seats.add(new Seat("A1", "F-004", false));
    seats.add(new Seat("A2", "F-004", false));
    seats.add(new Seat("A3", "F-004", false));
    seats.add(new Seat("B1", "F-004", false));
    seats.add(new Seat("B2", "F-004", false));
    seats.add(new Seat("B3", "F-004", false));
    seats.add(new Seat("B4", "F-004", true));
    flight = new Flight(
        "F-004",
        seats,
        "Keflavík",
        "Reykjavík",
        LocalDate.of(2023, 10, 19),
        LocalDate.of(2023, 10, 20),
        24000);
  }

  @Test
  public void testFlightGetFlightId() {
    assertEquals("F-004", flight.getFlightId());
  }

  @Test
  public void testFlightGetSeats() {
    ArrayList<Seat> DBSeats = new ArrayList<>(flight.getSeats());
    assertEquals(seats, DBSeats);
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

  @Test
  public void testGetDepartureTime() {
    assertEquals(LocalDate.of(2023, 10, 19), flight.getDepartureTime());
  }

  @Test
  public void testGetArrivalTime() {
    assertEquals(LocalDate.of(2023, 10, 20), flight.getArrivalTime());
  }

  @Test
  public void testFlightNumSeatsAvailable() {
    assertEquals(6, flight.getNumSeatsAvailable());
  }

  @Test
  public void testFlightNumSeatsReserved() {
    assertEquals(1, flight.getNumSeatsReserved());
  }

  @Test
  public void reserveSeat() {
    flight.reserveSeat("A-3");
    assertTrue(seats.get(2).isReserved());

  }

  // verð að hafa þetta aftast til að bjarga geðheilsunni
  @After
  public void tearDown() {
    seats = null;
    flight = null;
  }
}
