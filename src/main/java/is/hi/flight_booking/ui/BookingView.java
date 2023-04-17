package is.hi.flight_booking.ui;

import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.application.Flight;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.w3c.dom.events.MouseEvent;

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

    public BookingView(Booking booking) {
        readBookingView();

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
        //String formattedPrice = numberFormat.format(storedFlight.getPrice());
    }

    private void readBookingView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/flight_fxml/flight_view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void fxBookingClikcedHandler(MouseEvent mouseEvent) {

    }
}
