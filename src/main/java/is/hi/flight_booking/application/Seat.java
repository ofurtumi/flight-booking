package is.hi.flight_booking.application;

public class Seat {
  private final String id;
  private String flightId;
  private boolean reserved;

  public Seat(String sId, String fId, Boolean res) {
    id = sId;
    flightId = fId;
    reserved = res;
  }

  public String getId() {
    return id;
  }

  public String getFlightId() {
    return flightId;
  }

  public void setFlightId(String flightId) {
    this.flightId = flightId;
  }

  public boolean isReserved() {
    return reserved;
  }

  public void setReserved(boolean reserved) {
    this.reserved = reserved;
  }

  // equals method that checks if id, flightId and reservation is the same
  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof Seat))
      return false;
    Seat s = (Seat) o;
    return this.id.equals(s.getId()) &&
        this.flightId.equals(s.getFlightId()) &&
        this.reserved == s.isReserved();
  }

  @Override
  public String toString() {
    return String.format("Flight: %s, Seat: %s, Reserved: %b", this.flightId, this.id, this.reserved);
  }
}
