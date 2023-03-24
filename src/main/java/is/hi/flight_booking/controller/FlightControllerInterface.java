package is.hi.flight_booking.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import is.hi.flight_booking.application.Flight;

public interface FlightControllerInterface {
  public ArrayList<Flight> searchFlights(String depAddress, String arrAddress, LocalDate depTime);
}
