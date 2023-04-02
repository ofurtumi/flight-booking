package is.hi.flight_booking.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.database.DB;

public class BookingRepository {
    private final String connectionURL;
    
    public BookingRepository(String connectionURL){
        this.connectionURL = connectionURL;
    }

    public Booking getBooking(String bookingId){
        Booking found = null;
        // try {
        //     DB db = new DB(connectionURL);
        //     db.open();
        //     ResultSet bookingRS = db.query("SELECT * FROM bookings WHERE bookingId = ?",bookingId);
            
        // }
        // catch (SQLException e) {
        //     System.err.println(e);
        // }
        return found;
    }
}
