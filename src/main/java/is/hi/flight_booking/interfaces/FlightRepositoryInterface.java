package is.hi.flight_booking.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

import is.hi.flight_booking.application.Flight;

public interface FlightRepositoryInterface {
  public ArrayList<Flight> searchFlights(String depAddress, String arrAddrss, LocalDate depTime);

  public ArrayList<Flight> getSortedByPrice();

  public ArrayList<Flight> getSortedByTime();

  public ArrayList<Flight> getSortedByDeparture();

  public ArrayList<Flight> getSortedByArrival();
}
