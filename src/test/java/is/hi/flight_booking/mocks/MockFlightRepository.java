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
  private final String[] departures = { "Reykjavík", "Keflavík", "Húsavík", "Reykjavík", "Keflavík", "Keflavík",
      "Keflavík" };
  private final String[] destinations = { "Egilsstaðir", "Akureyri", "Vestmannaeyjar", "Egilsstaðir", "Akureyri",
      "Vestmannaeyjar", "Egilsstaðir" };

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
  }

  @Override
  public ArrayList<Flight> getSortedByPrice() {
    ArrayList<Flight> sorted = flights;
    sorted.sort(new Comparator<Flight>() {

      @Override
      public int compare(Flight f1, Flight f2) {
        return Integer.compare(f1.getPrice(), f2.getPrice());
      }
    });
    return sorted;

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
