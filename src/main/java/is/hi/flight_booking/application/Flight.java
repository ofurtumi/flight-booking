package is.hi.flight_booking.application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Flight {

  private String flightId;
  private final List<Seat> seats;

  private int numSeatsAvailable;
  private int numSeatsReserved;
  private int price;

  private LocalDate arrivalTime;
  private String arrivalAddress;

  private LocalDate departureTime;
  private String departureAddress;

  /**
   * @param flightId         id of current flight as a String
   * @param seats            ArrayList of Seat objects
   * @param departureAddress address of departure as a String
   * @param arrivalAddress   address of arrival as a String
   * @param departureTime    time of departure as LocalDate
   * @param arrivalTime      time of arrival as LocalDate
   * @param price            price of a single seat as an integer
   */
  public Flight(String flightId, ArrayList<Seat> seats, String departureAddress, String arrivalAddress,
      LocalDate departureTime, LocalDate arrivalTime, int price) {
    this.flightId = flightId;
    this.seats = seats;

    numSeatsAvailable = seats.size();
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

  public List<Seat> getSeats() {
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
    for (int i = 0; i < seats.size(); i++) {
      if (seats.get(i).getId() == seatId && seats.get(i).isReserved()) {
        seats.get(i).setReserved(true);
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

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Flight)) {
      return false;
    }

    // typecast o to Complex so that we can compare data members
    Flight f = (Flight) o;

    // Compare the data members and return accordingly
    return flightId.equals(f.flightId);
  }
}
