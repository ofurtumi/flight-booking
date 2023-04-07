package is.hi.flight_booking.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;

import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.application.User;

public class BookingControllerTest {
    BookingController BC;
    private ArrayList<Booking> bookings;
    private ArrayList<Flight> flights;
    private Flight flight;
    private User user;
    private String[] bookingId;
    private final String[] seatnumlist = {"A1", "A2", "B1", "B2", "C1", "C3"};
    private final String[] userId = {"0609013170", "2002203459", "2404016960", "3002223010"};
    private final String[] flightIdList = {"F-000", "F-001", "F-002"};
    private final String[] departures = {"Egilsstaðir", "Reykjavík", "Keflavík", "Akureyri", "Ísafjörður",
    "Vestmannaeyjar"};
    private final String[] destinations = {"Akureyri", "Ísafjörður", "Vestmannaeyjar", "Egilsstaðir", "Reykjavík",
    "Keflavík"};
    
    @Before
    public void setup(){
        BC = new BookingController("db/test.db");
        bookings = new ArrayList<>();
        flights = new ArrayList<>();
        ArrayList<Seat> seats = new ArrayList<>();
        for(int i = 0; i < departures.length; i++){
            String id = "F-" + String.format("%03d", i);
            for (String seatNum : seatnumlist) {
                seats.add(new Seat(seatNum, bookingId[i], false));
            }
            int day;
            if (i < 3) {
              day = 5;
            } else {
              day = 6;
            }
      
            LocalDate date = LocalDate.of(2023, 2, day);
      
            flights.add(
                new Flight(
                    id,
                    seats,
                    departures[i],
                    destinations[i],
                    date,
                    date,
                    15000 + (i * 1000)));
        }
        
        for(int i = 0; i < bookingId.length; i++){
            Booking b = new Booking(flight, user, bookingId[i], seats);
            bookings.add(b);
        }
    }
}
// for (int i = 0; i < userId.length; i++) {
        //     for (int j = 0; j < flightIdList.length; j++) {
        //         String[][] tempId;
        //         tempId[i][j] = "B-" + userId[i] + flightIdList[j];
        //         bookingId[i] = tempId[i][j];
        //     }
        // }