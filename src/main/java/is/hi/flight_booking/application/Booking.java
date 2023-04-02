package is.hi.flight_booking.application;

import java.util.ArrayList;

public class Booking {
  private final Flight flight;
  private final User user;
  private final String bookingID;
  private ArrayList<Seat> seats;

  public Booking(Flight flightID, User user, String bookingID, ArrayList<Seat> s) {
    this.flight = flightID;
    this.user = user;
    this.bookingID = bookingID;
    this.seats = s;
  }

  public int getPrice() {
    return flight.getPrice() * seats.size();
  }

  public User getUser() {
    return user;
  }

  public String getBookingID() {
    return bookingID;
  }

  public ArrayList<Seat> getSeats() {
    return seats;
  }

  public void addSeats(Seat seat) {
    this.seats.add(seat);
  }

  public void removeSeats(Seat seat) {
    this.seats.remove(seat);
  }
}
