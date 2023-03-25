package is.hi.flight_booking.mocks;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.ArrayList;
// import java.util.Arrays;
// import java.time.LocalDateTime;

import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.interfaces.FlightRepositoryInterface;

public class MockFlightRepository implements FlightRepositoryInterface {

  // private Flight[] mockFlights;
  private final ArrayList<Flight> flights;
  private final String[] departures = {"Reykjavík", "Keflavík", "Húsavík", "Reykjavík", "Keflavík", "Keflavík",
      "Keflavík"};
  private final String[] destinations = {"Egilsstaðir", "Akureyri", "Vestmannaeyjar", "Egilsstaðir", "Akureyri",
      "Vestmannaeyjar", "Egilsstaðir"};

  public MockFlightRepository() {
    // notum hér sömu gögn og voru skilgrein í testinu
    flights = new ArrayList<>();
    for (int i = 0; i < departures.length; i++) {
      String id = "F-" + (100 + i);
      ArrayList<Seat> seats = new ArrayList<>();
      seats.add(new Seat("A-1", id, false));
      seats.add(new Seat("A-2", id, false));
      seats.add(new Seat("A-3", id, false));
      seats.add(new Seat("A-4", id, false));
      seats.add(new Seat("B-1", id, false));
      seats.add(new Seat("B-2", id, false));
      seats.add(new Seat("B-3", id, false));
      seats.add(new Seat("B-4", id, false));
      flights.add(
          new Flight(id, seats, departures[i], destinations[i], LocalDate.of(2023, 4, i + 1),
              LocalDate.of(2023, 4, i + 1), 1000 * (i + 2)));
    }
    /*
     * Flight[] mocFlights = new Flight[7];
     * mocFlights[0] = new Flight(
     * "F-304",
     * Arrays.asList(new Seat("A-1", "F-304", false), new Seat("A-2", "F-304",
     * false), new Seat("A-3", "F-304", false),
     * new Seat("B-1", "F-304", false), new Seat("B-2", "F-304", false), new
     * Seat("B-3", "F-304", false)),
     * "Keflavík",
     * "Reykjavík",
     * LocalDate.now(),
     * LocalDate.now(),
     * 24000);
     * 
     * mocFlights[1] = new Flight(
     * "F-305",
     * Arrays.asList(new Seat("A-1", "F-305", false), new Seat("A-2", "F-305",
     * false), new Seat("A-3", "F-305", false),
     * new Seat("B-1", "F-305", false), new Seat("B-2", "F-305", false), new
     * Seat("B-3", "F-305", false)),
     * "Keflavík",
     * "Reykjavík",
     * LocalDate.now(),
     * LocalDate.now(),
     * 26000);
     * 
     * mocFlights[2] = new Flight(
     * "F-306",
     * Arrays.asList(new Seat("A-1", "F-306", false), new Seat("A-2", "F-306",
     * false), new Seat("A-3", "F-306", false),
     * new Seat("B-1", "F-306", false), new Seat("B-2", "F-306", false), new
     * Seat("B-3", "F-306", false)),
     * "Keflavík",
     * "Reykjavík",
     * LocalDate.now(),
     * LocalDate.now(),
     * 28000);
     * 
     * mocFlights[3] = new Flight(
     * "F-307",
     * Arrays.asList(new Seat("A-1", "F-307", false), new Seat("A-2", "F-307",
     * false), new Seat("A-3", "F-307", false),
     * new Seat("B-1", "F-307", false), new Seat("B-2", "F-307", false), new
     * Seat("B-3", "F-307", false)),
     * "Keflavík",
     * "Reykjavík",
     * LocalDate.now(),
     * LocalDate.now(),
     * 30000);
     * mocFlights[4] = new Flight(
     * "F-308",
     * Arrays.asList(new Seat("A-1", "F-308", false), new Seat("A-2", "F-308",
     * false), new Seat("A-3", "F-308", false),
     * new Seat("B-1", "F-308", false), new Seat("B-2", "F-308", false), new
     * Seat("B-3", "F-308", false)),
     * "Keflavík",
     * "Reykjavík",
     * LocalDate.now(),
     * LocalDate.now(),
     * 32000);
     * 
     * mocFlights[5] = new Flight(
     * "F-309",
     * Arrays.asList(new Seat("A-1", "F-309", false), new Seat("A-2", "F-309",
     * false), new Seat("A-3", "F-309", false),
     * new Seat("B-1", "F-309", false), new Seat("B-2", "F-309", false), new
     * Seat("B-3", "F-309", false)),
     * "Keflavík",
     * "Reykjavík",
     * LocalDate.now(),
     * LocalDate.now(),
     * 34000);
     * 
     * mocFlights[6] = new Flight(
     * "F-310",
     * Arrays.asList(new Seat("A-1", "F-310", false), new Seat("A-2", "F-310",
     * false), new Seat("A-3", "F-310", false),
     * new Seat("B-1", "F-310", false), new Seat("B-2", "F-310", false), new
     * Seat("B-3", "F-310", false)),
     * "Keflavík",
     * "Reykjavík",
     * LocalDate.now(),
     * LocalDate.now(),
     * 36000);
     */

  }

  @Override
  public ArrayList<Flight> getSortedByPrice() {
    // ef við notum sömu gögn og eru sett upp í testinu þá eru flugin í réttri röð
    return flights;
    /*
     * Arrays.sort(mockFlights, new Comparator<Flight>() {
     * 
     * @Override
     * public int compare(Flight f1, Flight f2) {
     * return Integer.compare(f1.getPrice(), f2.getPrice());
     * }
     * });
     * return mockFlights;
     */
  }

  @Override
  public ArrayList<Flight> getSortedByTime() {
    ArrayList<Flight> sorted = flights;
    sorted.sort(new Comparator<Flight>() {
      @Override
      public int compare(Flight f1, Flight f2) {
        return f1.getDepartureTime().compareTo(f2.getDepartureTime());
      }
    });
    return sorted;
  }

  @Override
  public ArrayList<Flight> getSortedByDeparture() {
    ArrayList<Flight> sorted = flights;
    sorted.sort(new Comparator<Flight>() {
      @Override
      public int compare(Flight f1, Flight f2) {
        return f1.getDepartureAddress().compareTo(f2.getDepartureAddress());
      }
    });
    return sorted;
  }

  @Override
  public ArrayList<Flight> getSortedByArrival() {
    ArrayList<Flight> sorted = flights;
    sorted.sort(new Comparator<Flight>() {
      @Override
      public int compare(Flight f1, Flight f2) {
        return f1.getArrivalAddress().compareTo(f2.getArrivalAddress());
      }
    });
    return sorted;
  }

  @Override
  public ArrayList<Flight> searchFlights(String departureAddress, String arrivalAddress, LocalDate departureTime) {
    ArrayList<Flight> filteredFlights = new ArrayList<>();

    for (Flight flight : flights) {
      boolean isDepartureAddressMatch = flight.getDepartureAddress().equals(departureAddress);
      boolean isArrivalAddressMatch = flight.getArrivalAddress().equals(arrivalAddress);
      boolean isDepartureTimeMatch = flight.getDepartureTime().equals(departureTime);

      if (isDepartureAddressMatch && isArrivalAddressMatch && isDepartureTimeMatch) {
        filteredFlights.add(flight);
      }
    }

    return filteredFlights;
  }

}
