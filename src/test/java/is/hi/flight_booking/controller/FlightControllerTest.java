package is.hi.flight_booking.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;

public class FlightControllerTest {

  private ArrayList<Flight> flights;
  private String[] departures = { "Reykjavík", "Keflavík", "Húsavík", "Reykjavík", "Keflavík", "Keflavík", "Keflavík" };
  private String[] destinations = { "Egilsstaðir", "Akureyri", "Vestmannaeyjar", "Egilsstaðir", "Akureyri",
      "Vestmannaeyjar", "Egilsstaðir" };
  FlightController fc;

  @Before
  public void setUp() {
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

  @After
  public void tearDown() {
  }

  @Test
  public void TodoTest() {

  }
}
