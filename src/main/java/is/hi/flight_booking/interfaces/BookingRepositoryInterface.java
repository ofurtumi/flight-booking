package is.hi.flight_booking.interfaces;

import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.application.Seat;

/**
 * BookingRepositoryInterface
 */
public interface BookingRepositoryInterface {

  /**
   * Takes a {@link Booking} object and creates a version of it on the database
   * 
   * @param booking Booking object to be created on the database
   */
  public void createBooking(Booking booking);

  /**
   * Takes a {@link Booking} object and deletes it from the database
   * needs to recursively remove seat reservations, can be implemented with
   * {@link removeSeat}
   * 
   * @param booking Booking object to be deleted from the database
   */
  public void deleteBooking(Booking booking);

  /**
   * Ease of access method, implements both {@link deletebooking} and
   * {@link createBooking}
   * does not actually update booking rather removes it from the
   * databse and
   * creates it again with minor changes little different
   *
   * @param booking Booking object to be updated on the database
   *
   * @deprecated
   *             since april 3. 9:43 AM, will not be used by T-group, do not
   *             implement
   */
  @Deprecated
  public void updateBooking(Booking booking);

  /**
   * Searches the database for a reference of the given {@link Seat} object and
   * marks it as reserved as well as connecting it to the given {@link Booking} id
   * 
   * @param booking {@link Booking} object to be updated on the database
   * @param seat    {@link Seat} object to be updated on the database
   */
  public void reserveSeat(Booking booking, Seat seat);

  /**
   * Searches the database for a reference to the given {@link Seat} object and
   * marks it as not reserved as well as removing the connection between it and
   * the given {@link Booking} it to the given {@link Booking} id
   * 
   * @param booking {@link Booking} object to be updated on the database
   * @param seat    {@link Seat} object to be updated on the database
   */
  public void removeSeat(Booking booking, Seat seat);

  /**
   * Uses a combination of {@link removeSeat} and {@link reserveSeat}
   * to update
   * seats to fit the given {@link Bookings}
   *
   * @param booking {@link Booking} object to be updated on the database
   * @param oldSeat {@link Seat} object to be updated on the database
   * @param newSeat {@link Seat} object to be updated on the database
   *
   * @deprecated
   *             since april 3. 9:43 AM, will not be used by T-group, do not
   *             implement
   */
  @Deprecated
  public void updateSeat(Booking booking, Seat oldSeat, Seat newSeat);
}
