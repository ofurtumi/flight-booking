package is.hi.flight_booking.application;
import java.util.Date;

public class Flight {

    private final String flightId;
    private final Seat[] seats;

    private final int numSeatsAvailable;
    private final int numSeatsReserved;
    private final int price;

    private final Date arrivalTime;
    private final String arrivalAddress;

    private final Date departureTime;
    private final String departureAddress;

    public Flight(String fId, Seat[] s, int nSA, int nSR, int tprice, Date aT, String aA, Date dT, String dA) {
        flightId = fId;
        seats = s;

        numSeatsAvailable = nSA;
        numSeatsReserved = nSR;
        price = tprice;

        arrivalTime = aT;
        arrivalAddress = aA;

        departureTime = dT;
        departureAddress = dA;
    }

    public static void main(String[] args) {
        Seat seat = new Seat("das", "bsl", true);    
    }
}
