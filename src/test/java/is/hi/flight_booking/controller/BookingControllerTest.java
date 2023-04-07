package is.hi.flight_booking.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.application.User;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
  public void ATestCreate() {
    Booking testBooking = BC.createBooking(flight, user, seats);
    assertEquals(ref, testBooking);
  }

  @Test
  public void BisOnDB() {
    try {
      assertTrue(BC.exists(ref));
    } catch (Exception e) {
      System.err.printf("Klikkaði að finna á DB: \n%s", e);
    }
  }

  @Ignore
  @Test
  public void CTestDelete() {
    BC.deleteBooking(ref);
  }

  @After
  public void tearDown() {
    BC = null;
    ref = null;
    seats = null;
    flight = null;
  }
}
// private ArrayList<Booking> bookings;
// private ArrayList<Flight> flights;
// private String[] bookingId;
// private final String[] seatnumlist = { "A1", "A2", "B1", "B2", "C1", "C3" };
// private final String[] userId = { "0609013170", "2002203459", "2404016960",
// "3002223010" };
// private final String[] flightIdList = { "F-000", "F-001", "F-002" };
// private final String[] departures = { "Egilsstaðir", "Reykjavík", "Keflavík",
// "Akureyri", "Ísafjörður",
// "Vestmannaeyjar" };
// private final String[] destinations = { "Akureyri", "Ísafjörður",
// "Vestmannaeyjar", "Egilsstaðir", "Reykjavík",
// "Keflavík" };
//
// @Before
// public void setup() {
// BC = new BookingController("db/test.db");
// bookings = new ArrayList<>();
// flights = new ArrayList<>();
// ArrayList<Seat> seats = new ArrayList<>();
// for (int i = 0; i < departures.length; i++) {
// String id = "F-" + String.format("%03d", i);
// for (String seatNum : seatnumlist) {
// seats.add(new Seat(seatNum, bookingId[i], false));
// }
// int day;
// if (i < 3) {
// day = 5;
// } else {
// day = 6;
// }
//
// LocalDate date = LocalDate.of(2023, 2, day);
//
// flights.add(
// new Flight(
// id,
// seats,
// departures[i],
// destinations[i],
// date,
// date,
// 15000 + (i * 1000)));
// }
//
// for (int i = 0; i < bookingId.length; i++) {
// Booking b = new Booking(flight, user, bookingId[i], seats);
// bookings.add(b);
// }
// }
// for (int i = 0; i < userId.length; i++) {
// for (int j = 0; j < flightIdList.length; j++) {
// String[][] tempId;
// tempId[i][j] = "B-" + userId[i] + flightIdList[j];
// bookingId[i] = tempId[i][j];
// }
// }
