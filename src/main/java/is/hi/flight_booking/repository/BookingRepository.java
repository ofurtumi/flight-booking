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


  public boolean checkIfExists(Booking booking) {
    try {
      DB db = new DB(connectionURL);
      db.open();

      ResultSet userRS = db.query("SELECT * FROM Users WHERE userId = ?", booking.getUserID());
      User dbUser = new User(userRS.getString("userId"), userRS.getString("name"));
      if (!booking.getUser().equals(dbUser)) {
        throw new SQLException();
      }

      ResultSet seatRS = db.query("SELECT * FROM Seats WHERE bookingId = ?", booking.getBookingID());
      ArrayList<Seat> dbSeats = new ArrayList<>();
      while (seatRS.next()) {
        Seat tempSeat = new Seat(seatRS.getString("seatId"), seatRS.getString("flightId"),
            seatRS.getBoolean("reserved"));
        dbSeats.add(tempSeat);
      }
      if (!booking.getSeats().equals(dbSeats)) {
        throw new SQLException();

      }

      ResultSet flightRS = db.query("SELECT * FROM Flights WHERE flightId = ?", booking.getFlightID());
      Flight dbFlight = new Flight(
          flightRS.getString("flightId"),
          dbSeats,
          flightRS.getString("departureAddress"),
          flightRS.getString("arrivalAddress"),
          LocalDate.parse(flightRS.getString("departureTime")),
          LocalDate.parse(flightRS.getString("arrivalTime")),
          flightRS.getInt("price"));
      if (!booking.getFlight().equals(dbFlight)) {

        throw new SQLException();

      }

      ResultSet bookingRS = db.query("SELECT * FROM Bookings WHERE bookingId = ?", booking.getBookingID());
      Booking dbBooking = new Booking(dbFlight, dbUser, bookingRS.getString("bookingId"), dbSeats);
      if (!booking.equals(dbBooking)) {
        throw new SQLException();
      }

      db.close();
    } catch (SQLException e) {
      System.err.println(e);
    }

    return true;
  }

  @Override
  public void createBooking(Booking booking) {
    DB db = new DB(connectionURL);
    db.open();

    String[] values = {booking.getBookingID(), booking.getFlightID(), booking.getUserID()};
    db.execute("INSERT INTO Bookings (userId, flightId, bookingId) VALUES (?, ?, ?)", values);

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
    db.execute("UPDATE Seats set reserved = false, bookingId = '' WHERE bookingId = ?", bookingId);
    db.execute("DELETE FROM Bookings WHERE bookingId = ?", bookingId);

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
    db.execute("UPDATE Seats set reserved = true, bookingId = ? WHERE flightId = ? and position = ?", values);
  }

  private void removeSeat(DB db, Booking booking, Seat seat) {
    String[] values = {booking.getFlightID(), seat.getId()};
    db.execute("UPDATE Seats set reserved = false, bookingId = '' WHERE flightId = ? and position = ?", values);
  }

  @Deprecated
  public void updateSeat(Booking booking, Seat oldSeat, Seat newSeat) {
    throw new UnsupportedOperationException("ekki nota þessa aðferð");
  }
}
