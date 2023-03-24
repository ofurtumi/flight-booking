package is.hi.flight_booking.application;

public class Booking {
    private final Flight flightId;
    private final User userId;
    private final String bookingId;
    private final Seat[] seats;

    public Booking(Flight fId, User uId, String bId, Seat[] s) {
        
        flightId = fId;
        userId = uId;
        bookingId = bId;
        seats = s;

    }

    /*/Gunnar, Það þarf að úrfæra þetta aðeins betur, finna út hversu mörg sæti bookingið er með og
        hvernig við tökum það inn og hvernig við reiknum kostnaðinn */
    private int getPrice() {
        return 0;
    }

}
