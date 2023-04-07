package is.hi.flight_booking.demo;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.application.User;
import is.hi.flight_booking.controller.BookingController;
import is.hi.flight_booking.controller.FlightController;

// Hugsað sem test fyrir heilt ferli í bæði leit, vali og bókun í kerfinu

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DemoTest {
  private User user;
  private List<Flight> flights;
  private Flight flight;
  private Booking booking;
  private List<Seat> seats;

  private BookingController BC;
  private FlightController FC;

  private String databaseURL = "db/test.db";

  @Before
  public void Setup() {
    BC = new BookingController(databaseURL);
    FC = new FlightController(databaseURL);

    flights = FC.searchFlights("Egilsstaðir", "Akureyri");
    flight = flights.get(0);

    seats = new ArrayList<>();
    seats.add(flight.getSeats().get(0));
    seats.add(flight.getSeats().get(1));
    seats.add(flight.getSeats().get(2));

    user = new User("0000006969", "Testur");

    booking = BC.createBooking(flight, user, seats);
  }

  @Test
  public void A_SearchAndChooseFlight() {
    assertEquals("F-000", flight.getFlightId());
    assertEquals(15000, flight.getPrice());
  }

  @Test
  public void B_ChooseSeats() {
    assertEquals("A1", seats.get(0).getId());
    assertEquals("A2", seats.get(1).getId());
    assertEquals("B1", seats.get(2).getId());
  }

  @Test
  public void C_MakeUser() {
    assertEquals("0000006969", user.getId());
    assertEquals("Testur", user.getName());
    assertArrayEquals(new String[]{"0000006969", "Testur"}, user.getInfo());
  }

  @Test
  public void D_CreateBooking() {
    assertTrue(BC.exists(booking));
  }

  @After
  public void tearDown() {
    BC = null;
    FC = null;
    booking = null;
    flight = null;
    flights = null;
    seats = null;
  }
}
