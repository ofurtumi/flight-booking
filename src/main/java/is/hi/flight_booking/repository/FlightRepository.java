package is.hi.flight_booking.repository;

import is.hi.flight_booking.database.*;
import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.interfaces.FlightRepositoryInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FlightRepository implements FlightRepositoryInterface {
  private ArrayList<Flight> sortBy(String comparator) throws SQLException {
    DB db = new DB();
    db.open();
    ResultSet rs = db.query("select * from Flight order by ?;", comparator);

    ArrayList<Flight> flights = new ArrayList<>();
    while (rs.next()) {
      String flightId = rs.getString("flightId");
      ArrayList<Seat> seatlist = new ArrayList<>();

      ResultSet seatResult = db.query("select * from Seats where flightId = ?", flightId);
      while (seatResult.next()) {
        seatlist.add(new Seat(seatResult.getString("position"), flightId, seatResult.getBoolean("reserved")));
      }

      flights.add(new Flight(
          flightId,
          seatlist,
          rs.getString("departureAddress"),
          rs.getString("arrivalAddress"),
          rs.getObject("departureTime", LocalDate.class),
          rs.getObject("arrivalTime", LocalDate.class),
          rs.getInt("price")));
    }

    db.close();

    return flights;
  }

  public ArrayList<Flight> getSortedByPrice() {
    ArrayList<Flight> flights = null;
    try {
      flights = sortBy("price");
    } catch (SQLException e) {
      System.err.println(e);
    }
    return flights;
  }

  public ArrayList<Flight> getSortedByTime() {
    ArrayList<Flight> flights = null;
    try {
      flights = sortBy("departureTime");
    } catch (SQLException e) {
      System.err.println(e);
    }
    return flights;
  }

  public ArrayList<Flight> getSortedByDeparture() {
    ArrayList<Flight> flights = null;
    try {
      flights = sortBy("departureAddress");
    } catch (SQLException e) {
      System.err.println(e);
    }
    return flights;
  }

  public ArrayList<Flight> getSortedByArrival() {
    ArrayList<Flight> flights = null;
    try {
      flights = sortBy("arrivalAddress");
    } catch (SQLException e) {
      System.err.println(e);
    }
    return flights;
  }

  public ArrayList<Flight> searchFlights(String depAddress, String arrAddress, LocalDate depTime) {
    try {
      DB db = new DB();
      db.open();

      String[] values = new String[2];
      values[0] = depAddress;
      values[1] = arrAddress;
      ArrayList<Flight> flights = new ArrayList<>();

      ResultSet rs = db.query("select * from Flight where departureAddress like ?");
      while (rs.next()) {
        LocalDate tempDate = rs.getObject("departureTime", LocalDate.class);
        if (tempDate == depTime) {
          String flightId = rs.getString("flightId");
          ArrayList<Seat> seatlist = new ArrayList<>();

          ResultSet seatResult = db.query("select * from Seats where flightId = ?", flightId);
          while (seatResult.next()) {
            seatlist.add(new Seat(seatResult.getString("position"), flightId, seatResult.getBoolean("reserved")));
          }

          flights.add(new Flight(
              flightId,
              seatlist,
              rs.getString("departureAddress"),
              rs.getString("arrivalAddress"),
              rs.getObject("departureTime", LocalDate.class),
              rs.getObject("arrivalTime", LocalDate.class),
              rs.getInt("price")));
        }
      }
      return flights;
    } catch (SQLException e) {
      return new ArrayList<Flight>();
    }
  }
}
