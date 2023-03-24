package is.hi.flight_booking.controller;

import is.hi.flight_booking.application.*;
import is.hi.flight_booking.repository.FlightRepository;

public class FlightController implements FlightInterface {

  private Flight[] flights;
  // Á eftir að búa til FlightRepository
  // private FlightRepository flightRepository;

  public String getFlight(String flightId) {
    // Vantar
    return "Vantar!";
  }
  public Flight[] searchFlight(String depAddress, String arrAddress, LocalDate depTime) {
    return new FlightRepository().searchFlight(depAddress, arrAddress, depTime);
  }
}
