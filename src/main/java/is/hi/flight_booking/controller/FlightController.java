package is.hi.flight_booking.controller;

import is.hi.flight_booking.application.*;
import is.hi.flight_booking.repository.FlightRepository;
import java.time.LocalDate;
import java.util.List;

public class FlightController implements FlightControllerInterface {

  private List<Flight> flights;
  // Á eftir að búa til FlightRepository
  // private FlightRepository flightRepository;

  public String getFlight(String flightId) {
    // Vantar
    return "Vantar!";
  }

  public List<Flight> searchFlights(String depAddress, String arrAddress, LocalDate depTime) {
    return flights;
  }
}
