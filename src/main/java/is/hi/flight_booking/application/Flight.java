package is.hi.flight_booking.application;
import java.util.Date;

public class Flight {

    private String flightId;
    private Seat[] seats;

    private int numSeatsAvailable;
    private int numSeatsReserved;
    private int price;

    private Date arrivalTime;
    private String arrivalAddress;

    private Date departureTime;
    private String departureAddress;

    public Flight(String fId, Seat[] s, int nSA, int nSR,int tprice, Date aT, String aA, Date dT, String dA){
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
        Seat seat = new Seat("das","bsl", true);    
    }
}
