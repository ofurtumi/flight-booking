package is.hi.flight_booking.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.database.DB;
import is.hi.flight_booking.interfaces.BookingRepositoryInterface;

public class BookingRepository implements BookingRepositoryInterface {
  private final String connectionURL;

  public BookingRepository(String connectionURL) {
    this.connectionURL = connectionURL;
  }

  public Booking getBooking(String bookingId) {
    Booking found = null;
    try {
      DB db = new DB(connectionURL);
      db.open();
      ResultSet bookingRS = db.query("SELECT * FROM Bookings WHERE bookingId = ?", bookingId);
      bookingRS.getString(bookingId);
      // ResultSet userRS = db.query(
      // "SELECT * FROM User WHERE userId = SELELCT userId from Bookings where
      // bookingId = ?", bookingId);
      // ResultSet flightRS = db.query(
      // "SELECT * FROM Flights WHERE flightId = SELELCT flightId from Bookings where
      // bookingId = ?",bookingId);
      // ArrayList<Booking> bookings = new ArrayList<>();
      // User userid = userRS.getString("userId");
      // Flights flightid = flightRS.getString("flightId");
      // found = new Booking(userid, flightid, bookingId);
      db.close();
    } catch (SQLException e) {
      System.err.println(e.getSQLState());
      System.err.println(e.getMessage());
      System.err.println(e.getErrorCode());
    }
    return found;
  }

  @Override
  public void createBooking(Booking booking) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createBooking'");
  }

  @Override
  public void deleteBooking(Booking booking) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteBooking'");
  }

  @Override
  public void updateBooking(Booking booking) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateBooking'");
  }

  @Override
  public void reserveSeat(Booking booking, Seat seat) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'reserveSeat'");
  }

  @Override
  public void removeSeat(Booking booking, Seat seat) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'removeSeat'");
  }

  @Override
  public void updateSeat(Booking booking, Seat oldSeat, Seat newSeat) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateSeat'");
  }
}
