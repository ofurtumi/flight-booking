package is.hi.flight_booking.controller;

import java.time.LocalDate;
import is.hi.flight_booking.application.Flight;

public interface FlightInterface {
    public Flight[] searchFlight(String depAddress, String arrAddress, LocalDate depTime);
}
