package is.hi.flight_booking.application;

import org.junit.*;

import is.hi.flight_booking.mocks.MockFlightRepository;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightTest {
  Flight flight;
  List<Seat> seats;

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
  @Test
  public void testSearchFlight() {
    String dAddr = "Akureyri";
    String aAddr = "Keflavík";
    LocalDate depTime = LocalDate.of(2023, 9, 17);
    // vantar rétt expected í assert og að klára Mock klasan
    assertArrayEquals(null, new MockFlightRepository().searchFlights(dAddr, aAddr, depTime));
  }


  // verð að hafa þetta aftast til að bjarga geðheilsunni
  @After
  public void tearDown() {
    seats = null;
    flight = null;
  }
}
