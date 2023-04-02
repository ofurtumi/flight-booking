package is.hi.flight_booking.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import is.hi.flight_booking.application.*;
import is.hi.flight_booking.interfaces.BookingControllerInterface;

public class BookingController implements BookingControllerInterface {
  // private BookingRepository BR;

  @Override
  public Booking createBooking(Flight flight, User user, ArrayList<Seat> seats) {
    String bookingID = String.format("B-%s-%s", user.getId(), flight.getFlightId().substring(2));
    Booking booking = new Booking(flight, user, bookingID, seats);

    try {
      // BR.createBooking(booking);
      // hér þarf að uppfæra stöðu á sætum ásamt því að búa til nýja bókun
    } catch (SQLException e) {
      System.err.println(e);
    }

    return booking;
  }

  @Override
  public void deleteBooking(Booking booking) {
    try {
      // BR.deleteBooking(booking);
    } catch (SQLException e) {
      System.err.println(e);
    }
  }

  @Override
  public void updateBooking(Booking booking) {
    try {
      // BR.(booking);
    } catch (SQLException e) {
      System.err.println(e);
    }
  }

  @Override
  public void reserveSeat(Booking booking, Seat seat) {

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
