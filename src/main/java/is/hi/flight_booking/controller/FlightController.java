package is.hi.flight_booking.controller;

import is.hi.flight_booking.application.*;
import java.time.LocalDate;
import java.util.List;
import is.hi.flight_booking.interfaces.FlightControllerInterface;
import is.hi.flight_booking.repository.FlightRepository;

public class FlightController implements FlightControllerInterface {

  private final FlightRepository FR;

  public FlightController(String URL) {
    FR = new FlightRepository(URL);
  }

  public Flight getFlight(String flightId) {
    return FR.getFlight(flightId);
  }

  public List<Flight> searchFlights(String depAddress, String arrAddress, LocalDate depTime) {
    List<Flight> found = FR.searchFlights(depAddress, arrAddress, depTime);
    return found;
  }

  public List<Flight> searchFlights(String depAddress, String arrAddress) {
    List<Flight> found = FR.searchFlights(depAddress, arrAddress);
    return found;
  }

  public List<Flight> getSortedByPrice() {
    return FR.getSortedByPrice();
  }

  public List<Flight> getSortedByTime() {
    return FR.getSortedByTime();
  }

  public List<Flight> getSortedByDeparture() {
    return FR.getSortedByDeparture();
  }

  public List<Flight> getSortedByArrival() {
    return FR.getSortedByArrival();
  }
}
