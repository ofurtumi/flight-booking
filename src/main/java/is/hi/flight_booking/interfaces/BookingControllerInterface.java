package is.hi.flight_booking.interfaces;

import java.util.ArrayList;

import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.application.User;

public interface BookingControllerInterface {

  /**
   * Creates a new booking on the database and returns a {@link Booking} object
   * with the same values
   * 
   * @param flightID ID of the flight
   * @param userID   ID of the user - follows the standard for icelandic SSN
   * @param seats    An ArrayList of {@link Seat} objects corresponding to the
   *                 current flight
   * @return {@link Booking} object holding the current booking info
   */
  public Booking createBooking(Flight flight, User user, ArrayList<Seat> seats);

  /**
   * Deletes a booking from the database
   * 
   * @param booking Booking object with all the relevant info
   */
  public void deleteBooking(Booking booking);

  /**
   * Updates a booking on the database
   * 
   * @param booking Booking object that has already been put on the databse
   * 
   * @deprecated
   *             since april 3. 9:43 AM, will not be used by T-group, do not
   *             implement
   */
  @Deprecated
  public void updateBooking(Booking booking);

  /**
   * Adds a seat to the given booking
   * 
   * @param booking Booking object that will get the new seat
   * @param seat    Seat object that will be added to the booking
   * 
   */
  public void reserveSeat(Booking booking, Seat seat);

  /**
   * Removes a seat from the given booking
   * 
   * @param booking Booking object which will have its seat removed
   * @param seat    Seat object that will be removed from the booking
   */
  public void removeSeat(Booking booking, Seat seat);

  /**
   * Takes a booking and changes an already chosen seat to another new
   * seat
   * 
   * @param booking Booking object which will have its seat changed
   * @param oldSeat Seat object that will be removed from the booking
   * @param newSeat Seat object that will be added to the booking
   *
   * @deprecated
   *             since april 3. 9:43 AM, will not be used by T-group, do not
   *             implement
   */
  @Deprecated
  public void updateSeat(Booking booking, Seat oldSeat, Seat newSeat);
}
