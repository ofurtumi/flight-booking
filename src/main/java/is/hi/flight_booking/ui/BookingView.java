package is.hi.flight_booking.ui;

import is.hi.flight_booking.application.Booking;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class BookingView extends HBox {

    @FXML
    private Text fxBookingId;
    @FXML
    private Text fxFlightId;
    @FXML
    private Text fxFlightFrom;
    @FXML
    private Text fxFlightTo;
    @FXML
    private Text fxFlightDate;
    @FXML
    private Text fxNumberOfSeats;
    @FXML
    private Text fxBookingPrice;

    private NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);

    public BookingView(Booking booking) {
        readBookingView();

        fxBookingId.setText(booking.getBookingID());
        fxFlightId.setText(booking.getFlight().getFlightId());
        fxFlightFrom.setText(booking.getFlight().getDepartureAddress());
        fxFlightTo.setText(booking.getFlight().getArrivalAddress());
        fxFlightDate.setText(booking.getFlight().getArrivalTime().toString());
        fxNumberOfSeats.setText(Integer.toString(booking.getSeats().size()));
        fxBookingPrice.setText(numberFormat.format((long) booking.getFlight().getPrice() *Integer.parseInt(fxNumberOfSeats.getText())));


        //String formattedPrice = numberFormat.format(storedFlight.getPrice());
    }

    @FXML
    public void fxBookingClikcedHandler(MouseEvent e) {
        System.out.println("ok");
        e.consume();
    }

    private void readBookingView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/flight_fxml/booking_view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
