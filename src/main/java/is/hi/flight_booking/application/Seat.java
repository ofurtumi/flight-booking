package is.hi.flight_booking.application;

public class Seat {
<<<<<<< HEAD
    private final String id;
    private final String flightId;
    private final boolean reserved;

    public Seat(String sId, String fId, Boolean res) {
        id = sId;
        flightId = fId;
        reserved = res;
    }
=======
  private String id;
  private String flightId;
  private boolean reserved;

  public Seat(String sId, String fId, Boolean res) {
    id = sId;
    flightId = fId;
    reserved = res;
  }

  public String toString() {
    return "ID: " + id + " reserved: " + reserved;
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
>>>>>>> c91721e5d035080a59c51614a2770d2b3d41d564
}
