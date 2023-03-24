package is.hi.flight_booking.mocks;

import java.time.LocalDate;

import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.repository.FlightRepositoryInterface;

public class MockFlightRepository implements FlightRepositoryInterface {

  @Override
  public Flight[] getSortedByPrice() {
    // TODO Auto-generated method stu
    throw new UnsupportedOperationException("Unimplemented method 'getSortedByPrice'");
  }

  @Override
  public Flight[] getSortedByTime() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getSortedByTime'");
  }

  @Override
  public Flight[] getSortedByDeparture() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getSortedByDeparture'");
  }

  @Override
  public Flight[] getSortedByArrival() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getSortedByArrival'");
  }

  @Override
  public Flight[] searchFlights(String depAddress, String arrAddrss, LocalDate depTime) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'searchFlights'");
  }

}
