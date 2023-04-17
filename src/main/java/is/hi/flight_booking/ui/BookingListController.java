package is.hi.flight_booking.ui;

import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.controller.BookingController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookingListController implements Initializable {
    @FXML
    private Text fxMainUserId;
    @FXML
    private VBox fxBookingsList;

    private String userId;

    private BookingController BC = new BookingController("db/flightBooking.db");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BookingApplication application = BookingApplication.getApplicationInstance();
        userId = application.getStoredBAppController().getKt();
        fxMainUserId.setText(userId);
        List<Booking> userBookings = BC.getBookings(userId);
    }

    public void fxBackHandler(ActionEvent actionEvent) throws IOException {
        BookingApplication application = BookingApplication.getApplicationInstance();
        application.changeScene("/flight_fxml/bookingApplication_view.fxml");
        application.setStoredBAppController(null);
        application.setUseStoredFalse();
        actionEvent.consume();
    }

    public void fxDeleteBookingHandler(ActionEvent actionEvent) {
    }
}
