package is.hi.flight_booking.application;

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

  public Flight(String flightId, Seat[] seats, String departureAddress, String arrivalAddress,
      LocalDate departureTime, LocalDate arrivalTime, int price) {
    this.flightId = flightId;
    this.seats = seats;

    numSeatsAvailable = seats.length;
    numSeatsReserved = 0;
    this.price = price;

    this.arrivalTime = arrivalTime;
    this.arrivalAddress = arrivalAddress;

    this.departureTime = departureTime;
    this.departureAddress = departureAddress;
  }

  public String getFlightId() {
    return flightId;
  }

  public Seat[] getSeats() {
    return seats;
  }

  public int getNumSeatsAvailable() {
    return numSeatsAvailable;
  }

  public int getNumSeatsReserved() {
    return numSeatsReserved;
  }

  public int getPrice() {
    return price;
  }

  public LocalDate getArrivalTime() {
    return arrivalTime;
  }

  public String getArrivalAddress() {
    return arrivalAddress;
  }

  public LocalDate getDepartureTime() {
    return departureTime;
  }

  public String getDepartureAddress() {
    return departureAddress;
  }

  public void setFlightId(String flightId) {
    this.flightId = flightId;
  }

  public void reserveSeat(String seatId) {
    for (int i = 0; i < seats.length; i++) {
      if (seats[i].getId() == seatId && seats[i].isReserved()) {
        seats[i].setReserved(true);
        break;
      }
    }
  }

  public void setNumSeatsAvailable(int numSeatsAvailable) {
    this.numSeatsAvailable = numSeatsAvailable;
  }

  public void setNumSeatsReserved(int numSeatsReserved) {
    this.numSeatsReserved = numSeatsReserved;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void setArrivalTime(LocalDate arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  public void setArrivalAddress(String arrivalAddress) {
    this.arrivalAddress = arrivalAddress;
  }

  public void setDepartureTime(LocalDate departureTime) {
    this.departureTime = departureTime;
  }

  public void setDepartureAddress(String departureAddress) {
    this.departureAddress = departureAddress;
  }
}
