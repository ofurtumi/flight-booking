package is.hi.flight_booking.interfaces;

import is.hi.flight_booking.application.Booking;

public interface BookingControllerInterface {
  /**
   * Creates a booking on the database
   * 
   * @param booking Booking object with all the relevant info
   */
  public void createBooking(Booking booking);

  /**
   * Deletes a booking from the database
   * 
   * @param booking Booking object with all the relevant info
   */
  public void deleteBooking(Booking booking);
}
