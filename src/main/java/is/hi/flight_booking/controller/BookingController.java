package is.hi.flight_booking.controller;

import java.util.List;
import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.application.User;
import is.hi.flight_booking.interfaces.BookingControllerInterface;
import is.hi.flight_booking.repository.BookingRepository;

public class BookingController implements BookingControllerInterface {
  private final BookingRepository BR;

  public BookingController(String URL) {
    BR = new BookingRepository(URL);
  }

  public Booking createBooking(Flight flight, User user, List<Seat> seats) {
    String bookingID = String.format("B-%s-%s", user.getId(), flight.getFlightId().substring(2));
    Booking booking = new Booking(flight, user, bookingID, seats);

    try {
      BR.createBooking(booking);
      // hér þarf að uppfæra stöðu á sætum ásamt því að búa til nýja bókun
    } catch (Exception e) {
      System.err.println(e);
    }

    return booking;
  }

  public void deleteBooking(Booking booking) {
    try {
      BR.deleteBooking(booking);
    } catch (Exception e) {
      System.err.println(e);
    }
  }

  public void updateBooking(Booking booking) {
    throw new UnsupportedOperationException("Unimplemented method 'updateBooking', fuck you");
  }

  public void reserveSeat(Booking booking, Seat seat) {
    try {
      booking.addSeats(seat);
      BR.reserveSeat(booking, seat);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  public void removeSeat(Booking booking, Seat seat) {

    try {
      booking.removeSeats(seat);
      BR.removeSeat(booking, seat);
    } catch (Exception e) {
      // TODO: handle exception
    }
  }

  public void updateSeat(Booking booking, Seat oldSeat, Seat newSeat) {
    throw new UnsupportedOperationException("Unimplemented method 'updateSeat', fuck you");
  }
}
