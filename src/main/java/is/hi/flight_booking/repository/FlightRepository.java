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
  private final String connectionURL;

  public FlightRepository(String connectionURL) {
    this.connectionURL = connectionURL;
  }

  public Flight getFlight(String flightId) {
    Flight found = null;
    try {
      DB db = new DB(connectionURL);
      // todo - útfæra þessi skil fyrir öll DB connections

      db.open();
      ResultSet flightRS = db.query("select * from Flights where flightId = ?", flightId);
      ResultSet seatRS = db.query("select * from Seats where flightId = ?", flightId);

      ArrayList<Seat> seatlist = new ArrayList<>();
      while (seatRS.next()) {
        seatlist
            .add(new Seat(seatRS.getString(2), seatRS.getString(1), seatRS.getBoolean(3)));
      }

      String departureAddress = flightRS.getString("departureAddress");
      String arrivalAddress = flightRS.getString("arrivalAddress");
      LocalDate departureTime = LocalDate.parse(flightRS.getString("departureTime"));
      LocalDate arrivalTime = LocalDate.parse(flightRS.getString("arrivalTime"));

      int price = flightRS.getInt("price");

      found = new Flight(
          flightId,
          seatlist,
          departureAddress,
          arrivalAddress,
          departureTime,
          arrivalTime,
          price);

      db.close();
    } catch (SQLException e) {
      System.err.println(e.getSQLState());
      System.err.println(e.getMessage());
      System.err.println(e.getErrorCode());
    }
    return found;
  }

  private ArrayList<Flight> sortBy(String comparator) throws SQLException {
    DB db = new DB(connectionURL);
    db.open();
    ResultSet rs = db.query(String.format("select * from Flights order by %s;", comparator));

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
          LocalDate.parse(rs.getString("departureTime")),
          LocalDate.parse(rs.getString("arrivalTime")),
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
      DB db = new DB(connectionURL);
      db.open();

      String[] values = new String[2];
      values[0] = depAddress;
      values[1] = arrAddress;
      ArrayList<Flight> flights = new ArrayList<>();

      ResultSet rs = db.query("select * from Flights where departureAddress = ? and arrivalAddress = ?", values);
      while (rs.next()) {
        LocalDate tempDate = LocalDate.parse(rs.getString("departureTime"));
        if (depTime == null || tempDate.equals(depTime)) {
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
              LocalDate.parse(rs.getString("departureTime")),
              LocalDate.parse(rs.getString("arrivalTime")),
              rs.getInt("price")));
        }
      }
      return flights;
    } catch (SQLException e) {
      return new ArrayList<Flight>();
    }
  }

  public ArrayList<Flight> searchFlights(String depAddress, String arrAddress) {
    try {
      DB db = new DB(connectionURL);
      db.open();

      String[] values = new String[2];
      values[0] = depAddress;
      values[1] = arrAddress;
      ArrayList<Flight> flights = new ArrayList<>();

      ResultSet rs = db.query("select * from Flights where departureAddress = ?", depAddress);
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
            LocalDate.parse(rs.getString("departureTime")),
            LocalDate.parse(rs.getString("arrivalTime")),
            rs.getInt("price")));
      }
      return flights;
    } catch (SQLException e) {
      return new ArrayList<Flight>();
    }
  }
}
