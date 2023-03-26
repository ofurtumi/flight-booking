package is.hi.flight_booking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.mocks.MockFlightRepository;

public class FlightControllerTest {

  private ArrayList<Flight> flights;
  private final String[] departures = { "Reykjavík", "Keflavík", "Húsavík", "Reykjavík", "Keflavík", "Keflavík",
      "Keflavík" };
  private final String[] destinations = { "Egilsstaðir", "Akureyri", "Vestmannaeyjar", "Egilsstaðir", "Akureyri",
      "Vestmannaeyjar", "Egilsstaðir" };
  MockFlightRepository MFR;

  @Before
  public void setUp() {
    MFR = new MockFlightRepository();
    flights = new ArrayList<>();
    for (int i = 0; i < departures.length; i++) {
      String id = "F-" + (100 + i);
      ArrayList<Seat> seats = new ArrayList<>();
      seats.add(new Seat("A-1", id, false));
      seats.add(new Seat("A-2", id, false));
      seats.add(new Seat("A-3", id, false));
      seats.add(new Seat("A-4", id, false));
      seats.add(new Seat("B-1", id, false));
      seats.add(new Seat("B-2", id, false));
      seats.add(new Seat("B-3", id, false));
      seats.add(new Seat("B-4", id, false));
      flights.add(
          new Flight(id, seats, departures[i], destinations[i], LocalDate.of(2023, 4, i + 1),
              LocalDate.of(2023, 4, i + 1), 1000 * (i + 2)));
    }
  }

  @Test
  public void GetFlight() {
    Flight expected = flights.get(3);
    assertEquals(expected, MFR.getFlight("F-103"));
  }

  @Test
  public void SortByTime() {
    ArrayList<Flight> sorted = MFR.getSortedByTime();
    assertEquals(flights, sorted);
  }

  @Test
  public void SortByArrival() {
    ArrayList<Flight> sorted = MFR.getSortedByArrival();

    // þetta eru svo fá flug að þægilegast að raða bara í höndunum
    ArrayList<Flight> correct = new ArrayList<>();
    correct.add(flights.get(1));
    correct.add(flights.get(4));
    correct.add(flights.get(0));
    correct.add(flights.get(3));
    correct.add(flights.get(6));
    correct.add(flights.get(2));
    correct.add(flights.get(5));

    assertEquals(correct, sorted);
  }

  @Test
  public void SortByPrice() {
    ArrayList<Flight> sorted = MFR.getSortedByPrice();
    assertEquals(flights, sorted);
  }

  @Test
  public void SearchForFlight() {
    ArrayList<Flight> filtered = MFR.searchFlights("Keflavík", "Akureyri", LocalDate.of(2023, 4, 2));
    ArrayList<Flight> correct = new ArrayList<>();
    correct.add(flights.get(1));

    assertEquals(correct, filtered);
  }

  @After
  public void tearDown() {
    flights = null;
    MFR = null;
  }
}
