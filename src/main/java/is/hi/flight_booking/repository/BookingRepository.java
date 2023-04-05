package is.hi.flight_booking.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.application.User;
import is.hi.flight_booking.database.DB;
import is.hi.flight_booking.interfaces.BookingRepositoryInterface;

public class BookingRepository implements BookingRepositoryInterface {
  private final String connectionURL;

  public BookingRepository(String connectionURL) {
    this.connectionURL = connectionURL;
  }

  /*
   * public Booking getBooking(Booking booking) {
   * Booking found = null;
   * try {
   * DB db = new DB(connectionURL);
   * db.open();
   * 
   * ArrayList<Seat> seatlist = new ArrayList<>();
   * ResultSet seats = db.query("select * from Seats where bookingId = ?",
   * bookingId);
   * while (seats.next()) {
   * Seat seat = new Seat(seats.getString("position"),
   * seats.getString("flightId"), seats.getBoolean("reserved"));
   * seatlist.add(seat);
   * }
   * 
   * ResultSet bookingRS = db.query("SELECT * FROM Bookings WHERE bookingId = ?",
   * bookingId);
   * 
   * Flight flight = new
   * FlightRepository(connectionURL).getFlight(bookingRS.getString("flightId"));
   * 
   * // ResultSet userRS = db.query(
   * // "SELECT * FROM User WHERE userId = SELELCT userId from Bookings where
   * // bookingId = ?", bookingId);
   * // ResultSet flightRS = db.query(
   * // "SELECT * FROM Flights WHERE flightId = SELELCT flightId from Bookings
   * where
   * // bookingId = ?",bookingId);
   * // ArrayList<Booking> bookings = new ArrayList<>();
   * // User userid = userRS.getString("userId");
   * // Flights flightid = flightRS.getString("flightId");
   * // found = new Booking(userid, flightid, bookingId);
   * db.close();
   * } catch (SQLException e) {
   * System.err.println(e.getSQLState());
   * System.err.println(e.getMessage());
   * System.err.println(e.getErrorCode());
   * }
   * return found;
   * }
   */

  public boolean checkIfExists(Booking booking) {
    try {
      DB db = new DB(connectionURL);
      db.open();

      ResultSet userRS = db.query("select * from Users where userId = ?", booking.getUserID());
      User dbUser = new User(userRS.getString("userId"), userRS.getString("name"));
      if (!booking.getUser().equals(dbUser)) {
        System.err.println("Booking does not exist");
        db.close();
        throw new SQLException();
      }

      ResultSet seatRS = db.query("select * from Seats where bookingId = ?", booking.getBookingID());
      ArrayList<Seat> dbSeats = new ArrayList<>();
      while (seatRS.next()) {
        Seat tempSeat = new Seat(seatRS.getString("seatId"), seatRS.getString("flightId"),
            seatRS.getBoolean("reserved"));
        dbSeats.add(tempSeat);
      }
      if (!booking.getSeats().equals(dbSeats)) {
        System.err.println("Seats does not exist");
        db.close();
        throw new SQLException();

      }

      ResultSet flightRS = db.query("select * from Flights where flightId = ?", booking.getFlightID());
      Flight dbFlight = new Flight(
          flightRS.getString("flightId"),
          dbSeats,
          flightRS.getString("departureAddress"),
          flightRS.getString("arrivalAddress"),
          LocalDate.parse(flightRS.getString("departureTime")),
          LocalDate.parse(flightRS.getString("arrivalTime")),
          flightRS.getInt("price"));

      if (!booking.getFlight().equals(dbFlight)) {
        System.err.println("Flight does not exist");
        db.close();
        throw new SQLException();
      }

      ResultSet bookingRS = db.query("select * from Bookings where bookingId = ?", booking.getBookingID());
      Booking dbBooking = new Booking(dbFlight, dbUser, bookingRS.getString("bookingId"), dbSeats);
      if (!booking.equals(dbBooking)) {
        db.close();
        throw new SQLException();
      }

      db.close();
    } catch (SQLException e) {
      System.err.println(e);
      return false;
    }

    return true;
  }

  @Override
  public void createBooking(Booking booking) {
    DB db = new DB(connectionURL);
    db.open();

    db.execute("insert or ignore into Users (userId, name) values (?, ?)", booking.getUser().getInfo());

    String[] values = {booking.getBookingID(), booking.getFlightID(), booking.getUserID()};
    db.execute("insert into Bookings (bookingId, flightId, userId) values (?, ?, ?)", values);

    for (Seat seat : booking.getSeats()) {
      reserveSeat(db, booking, seat);
    }

    db.close();
  }

  @Override
  public void deleteBooking(Booking booking) {
    DB db = new DB(connectionURL);
    db.open();

    String bookingId = booking.getBookingID();
    db.execute("update Seats set reserved = false, bookingId = '' where bookingId = ?", bookingId);
    db.execute("delete from Bookings where bookingId = ?", bookingId);

    db.close();
  }

  @Deprecated
  public void updateBooking(Booking booking) {
    throw new UnsupportedOperationException("ekki nota þessa aðferð");
  }

  @Override
  public void reserveSeat(Booking booking, Seat seat) {
    DB db = new DB(connectionURL);
    reserveSeat(db, booking, seat);
  }

  @Override
  public void removeSeat(Booking booking, Seat seat) {
    DB db = new DB(connectionURL);
    removeSeat(db, booking, seat);
  }

  private void reserveSeat(DB db, Booking booking, Seat seat) {
    String[] values = {booking.getBookingID(), booking.getFlightID(), seat.getId()};
    db.execute("update Seats set reserved = true, bookingId = ? where flightId = ? and position = ?", values);
  }

  private void removeSeat(DB db, Booking booking, Seat seat) {
    String[] values = {booking.getFlightID(), seat.getId()};
    db.execute("update Seats set reserved = false, bookingId = '' where flightId = ? and position = ?", values);
  }

  @Deprecated
  public void updateSeat(Booking booking, Seat oldSeat, Seat newSeat) {
    throw new UnsupportedOperationException("ekki nota þessa aðferð");
  }
}
