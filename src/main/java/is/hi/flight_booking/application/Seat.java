package is.hi.flight_booking.application;

public class Seat {
    private final String id;
    private final String flightId;
    private final boolean reserved;

    public Seat(String sId, String fId, Boolean res) {
        id = sId;
        flightId = fId;
        reserved = res;
    }
}
