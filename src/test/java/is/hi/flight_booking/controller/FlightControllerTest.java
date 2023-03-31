package is.hi.flight_booking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.repository.FlightRepository;

public class FlightControllerTest {

  private ArrayList<Flight> flights;
  private String[] seatnumlist = { "A1", "A2", "B1", "B2", "C1", "C3" };
  private final String[] departures = { "Egilsstaðir", "Reykjavík", "Keflavík", "Akureyri", "Ísafjörður",
      "Vestmannaeyjar" };
  private final String[] destinations = { "Akureyri", "Ísafjörður", "Vestmannaeyjar", "Egilsstaðir", "Reykjavík",
      "Keflavík" };
  FlightRepository flightRepo;

  @Before
  public void setUp() {
    flightRepo = new FlightRepository("db/test.db");
    flights = new ArrayList<>();
    for (int i = 0; i < departures.length; i++) {
      String id = "F-" + String.format("%03d", i);
      ArrayList<Seat> seats = new ArrayList<>();
      for (String seatNum : seatnumlist) {
        seats.add(new Seat(seatNum, id, false));
      }

      int day;
      if (i < 3) {
        day = 5;
      } else {
        day = 6;
      }

      LocalDate date = LocalDate.of(2023, 2, day);

      flights.add(
          new Flight(
              id,
              seats,
              departures[i],
              destinations[i],
              date,
              date,
              15000 + (i * 1000)));
    }
  }

  @Test
  public void GetFlight() {
    Flight expected = flights.get(0);
    assertEquals(expected, flightRepo.getFlight("F-000"));
  }

  @Test
  public void SortByTime() {
    ArrayList<Flight> sorted = flightRepo.getSortedByTime();
    assertEquals(flights, sorted);
  }

  @Test
  public void SortByArrival() {
    ArrayList<Flight> sorted = flightRepo.getSortedByArrival();

    // þetta eru svo fá flug að þægilegast að raða bara í höndunum
    ArrayList<Flight> correct = new ArrayList<>();
    correct.add(flights.get(0)); // Akureyri
    correct.add(flights.get(3)); // Egilsstaðir
    correct.add(flights.get(5)); // Keflavík
    correct.add(flights.get(4)); // Reykjavík
    correct.add(flights.get(2)); // Vestmannaeyjar
    correct.add(flights.get(1)); // Ísafjörður

    for (int i = 0; i < correct.size(); i++) {
      assertEquals(correct.get(i).getFlightId(), sorted.get(i).getFlightId());
    }
  }

  @Test
  public void SortByPrice() {
    ArrayList<Flight> sorted = flightRepo.getSortedByPrice();
    assertEquals(flights, sorted);
  }

  @Test
  public void SearchForFlight() {
    ArrayList<Flight> filtered = flightRepo.searchFlights("Egilsstaðir", "Akureyri", LocalDate.of(2023, 2, 5));

    assertEquals(flights.get(0).getFlightId(), filtered.get(0).getFlightId());
  }

  @After
  public void tearDown() {
    flights = null;
    flightRepo = null;
  }
}
