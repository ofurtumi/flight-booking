package is.hi.flight_booking.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

import is.hi.flight_booking.application.Flight;

public interface FlightRepositoryInterface {

  /**
   * Takes in a reference to a flight ID and returns a {@link Flight} object with
   * the same flight ID
   *
   * @param flightId  a string containing a flight ID
   * @return          a {@link Flight} object with the specified ID
   */
  public Flight getFlight(String flightId);

  /**
   * Searches the database in use for flights that fulfill the search criteria set
   * using the parameters
   * 
   * @param depAddress a string containing the place of departure
   * @param arrAddress a string containing the place of arrival
   * @param depTime    a {@link LocalDate} object holding the date of departure
   * @return an {@link ArrayList} of {@link Flight} objects fulfilling the search
   *         criteria
   */
  public ArrayList<Flight> searchFlights(String depAddress, String arrAddrss, LocalDate depTime);

  /**
   * @return ArrayList of {@link Flight} objects in ascending order of price
   */
  public ArrayList<Flight> getSortedByPrice();

  /**
   * @return ArrayList of {@link Flight} objects in ascending time order
   */
  public ArrayList<Flight> getSortedByTime();

  /**
   * @return ArrayList of {@link Flight} objects in alphabetical order by
   *         departure address
   */
  public ArrayList<Flight> getSortedByDeparture();

  /**
   * @return ArrayList of {@link Flight} objects in alphabetical order arrival
   *         address
   */
  public ArrayList<Flight> getSortedByArrival();
}
