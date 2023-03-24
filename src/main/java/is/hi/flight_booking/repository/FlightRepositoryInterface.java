package is.hi.flight_booking.repository;

import java.time.LocalDate;

import is.hi.flight_booking.application.Flight;

public interface FlightRepositoryInterface {
  public Flight[] searchFlights(String depAddress, String arrAddrss, LocalDate depTime);

  public Flight[] getSortedByPrice();

  public Flight[] getSortedByTime();

  public Flight[] getSortedByDeparture();

  public Flight[] getSortedByArrival();
}
