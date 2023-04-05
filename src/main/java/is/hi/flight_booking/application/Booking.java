package is.hi.flight_booking.application;

import java.util.List;

public class Booking {
  private final Flight flight;
  private final User user;
  private final String bookingID;
  private final List<Seat> seats;

  public Booking(Flight flight, User user, String bookingID, List<Seat> s) {
    this.flight = flight;
    this.user = user;
    this.bookingID = bookingID;
    this.seats = s;
  }

  public String getFlightID() {
    return flight.getFlightId();
  }

  public String getUserID() {
    return user.getId();
  }

  public int getPrice() {
    return flight.getPrice() * seats.size();
  }

  public User getUser() {
    return user;
  }

  public Flight getFlight() {
    return flight;
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

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Booking)) {
      return false;
    }

    // typecast o to Complex so that we can compare data members
    Booking b = (Booking) o;

    // Compare the data members and return accordingly
    return bookingID.equals(b.bookingID)
        && flight.equals(b.getFlight())
        && seats.equals(b.getSeats())
        && this.getPrice() == b.getPrice();
  }
}
