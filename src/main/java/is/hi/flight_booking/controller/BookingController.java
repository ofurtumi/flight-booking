package is.hi.flight_booking.controller;

import is.hi.flight_booking.application.*;
import is.hi.flight_booking.interfaces.BookingControllerInterface;

public class BookingController implements BookingControllerInterface {

  /* Það vantar að tengja controlerinn við BookingRepository */

  private Flight flightId;
  private User userId;
  private String bookingId;
  private Seat[] seats;

  public BookingController() {

  }

  // Getter og setter fyrir flightId
  public Flight getFlightId() {
    return this.flightId;
  }

  public void setFlightId(Flight id) {
    this.flightId = id;
  }

  // getter og setter fyrir userId
  public User getUserId() {
    return this.userId;
  }

  public void setUserId(User id) {
    this.userId = id;
  }

  // Getter og setter fyrir bookingId
  public String getBookingId() {
    return this.bookingId;
  }

  public void setBookingId(String id) {
    this.bookingId = id;
  }

  // getter og setter fyrir seats, gæti þurft að laga return þar sem þetta er
  // fylki
  public Seat[] getSeats() {
    return this.seats;
  }

  public void setSeats(Seat[] s) {
    this.seats = s;
  }

  public void deleteBooking(String bookingId) {
    // Vantar
  }

  public void createBooking() {
    // Vantar
  }

  public void updateBooking() {
    // Vantar
  }

  public void reserveSeat(Seat newSeat) {
    // vantar
  }

  public void removeseat(Seat oldSeat) {
    // Vantar
  }

  public void updateSeat(Seat newSeat, Seat oldSeat) {
    // Vantar
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

}
