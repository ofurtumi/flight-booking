package is.hi.flight_booking.ui;

import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.controller.BookingController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
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

    private BookingView selectedBooking;

    private String userId;

    private BookingController BC = new BookingController("db/flightBooking.db");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BookingApplication application = BookingApplication.getApplicationInstance();
        userId = application.getStoredBAppController().getKt();
        fxMainUserId.setText(userId);
        List<Booking> userBookings = BC.getBookings(userId);
        listBookings(userBookings);
    }

    public void fxBackHandler(ActionEvent actionEvent) throws IOException {
        BookingApplication application = BookingApplication.getApplicationInstance();
        application.changeScene("/flight_fxml/bookingApplication_view.fxml");
        application.setStoredBAppController(null);
        application.setUseStoredFalse();
        actionEvent.consume();
    }

    private void listBookings(List<Booking> bookings) {
        if(bookings.isEmpty()) {
            fxBookingsList.getChildren().clear();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ekkert fannst");
            alert.setHeaderText("Engin bókun til");
            alert.setContentText("Engin bókun finnst fyrir kt: " + userId);
            alert.showAndWait();
        } else {
            fxBookingsList.getChildren().clear();
            for (Booking booking : bookings) {
                BookingView newListedDepFlight = new BookingView(booking, this);
                fxBookingsList.getChildren().add(newListedDepFlight);
            }
        }
    }

    public void fxDeleteBookingHandler(ActionEvent actionEvent) {
        if(selectedBooking != null) {
            BC.deleteBooking(getSelectedBooking().getStoredBooking());
            List<Booking> userBookings = BC.getBookings(userId);
            listBookings(userBookings);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bókun eytt");
            alert.setHeaderText("Bókuninni hefur verið eytt");
            alert.setContentText("Bókunarnúmer: " + getSelectedBooking().getStoredBooking().getBookingID());
            alert.showAndWait();
            setSelectedBooking(null);
            actionEvent.consume();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ekkert valið");
            alert.setHeaderText("Bókun til eyðingar ekki valinn");
            alert.setContentText("Vertu viss um að bókun til eyðingar sé valin.");
            alert.showAndWait();
            actionEvent.consume();
        }
    }

    public BookingView getSelectedBooking() {
        return selectedBooking;
    }

    public void setSelectedBooking(BookingView selectedBooking) {
        this.selectedBooking = selectedBooking;
    }
}
