package main.java.is.hi.flight_booking.application;
import java.time.LocalDate;

public class Flight {

    private String flightId;
    private Seat[] seats;

    private int numSeatsAvailable;
    private int numSeatsReserved;
    private int price;

    private LocalDate arrivalTime;
    private String arrivalAddress;

    private LocalDate departureTime;
    private String departureAddress;

    public Flight(String fId, Seat[] s, int nSA, int nSR,int tprice, LocalDate aT, String aA, LocalDate dT, String dA){
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
