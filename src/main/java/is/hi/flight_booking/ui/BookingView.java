package is.hi.flight_booking.ui;

import is.hi.flight_booking.application.Booking;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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

    private final Booking storedBooking;

    private BookingListController BLC;

    private NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);

    public BookingView(Booking booking, BookingListController BLC) {
        readBookingView();

        this.BLC = BLC;
        storedBooking = booking;
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
        if (BLC.getSelectedBooking() != null) {
            setUnselectedBookingBG(BLC.getSelectedBooking());
            setSelectedBookingBG(this);
            BLC.setSelectedBooking(this);
            Platform.runLater(() -> setSelectedBookingBG(BLC.getSelectedBooking()));
            Platform.runLater(() -> setSelectedBookingBG(this));
            System.out.println(
                    "Stored departure fligth id: " + BLC.getSelectedBooking().getStoredBooking().getBookingID());
            e.consume();
        } else {
            setSelectedBookingBG(this);
            BLC.setSelectedBooking(this);
            Platform.runLater(() -> setSelectedBookingBG(BLC.getSelectedBooking()));
            Platform.runLater(() -> setSelectedBookingBG(this));
            System.out.println(
                    "Stored departure fligth id: " + BLC.getSelectedBooking().getStoredBooking().getBookingID());
            e.consume();
        }
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

    public Booking getStoredBooking() {
        return storedBooking;
    }


    public void setSelectedBookingBG(BookingView booking) {
        booking.setBackground(new Background(
                new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(10.0), null)));
    }

    public void setUnselectedBookingBG(BookingView booking) {
        booking.setBackground(new Background(
                new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(10.0), null)));
    }
}
