package is.hi.flight_booking.application;

import java.util.List;

public class Booking {
  private final Flight flight;
  private final User user;
  private final String bookingID;
  private final List<Seat> seats;

  public Booking(Flight flightID, User user, String bookingID, List<Seat> s) {
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

  public List<Seat> getSeats() {
    return seats;
  }

  public void addSeats(Seat seat) {
    this.seats.add(seat);
  }

  public void removeSeats(Seat seat) {
    this.seats.remove(seat);
  }
}
