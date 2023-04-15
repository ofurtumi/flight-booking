package is.hi.flight_booking.ui;

import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.controller.BookingController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SelectSeatsBothWaysController implements Initializable {

    // ----------- FMXL breytur for Departure Flight -----------------
    // ---------------------------------------------------------------
    @FXML
    private Text fxDepHeaderFlightNum;
    @FXML
    private Text fxDepFromLocation, fxDepToLocation;
    @FXML
    private Text fxDepDate;
    @FXML
    private Text fxDepSeatsSelected, fxDepSeatsTotal;
    @FXML
    private VBox fxDepA1, fxDepA2, fxDepB1, fxDepB2, fxDepC1, fxDepC2;
    @FXML
    private Text fxDepMinorFlightNum;
    @FXML
    private Text fxDepTotalPrice;
    // ---------------------------------------------------------------

    // ----------- FMXL breytur for Return Flight --------------------
    // ---------------------------------------------------------------
    @FXML
    private Text fxRetHeaderFlightNum;
    @FXML
    private Text fxRetFromLocation, fxRetToLocation;
    @FXML
    private Text fxRetDate;
    @FXML
    private Text fxRetSeatsSelected, fxRetSeatsTotal;
    @FXML
    private VBox fxRetA1, fxRetA2, fxRetB1, fxRetB2, fxRetC1, fxRetC2;
    @FXML
    private TextField fxRetUserID;
    @FXML
    private Text fxRetMinorFlightNum;
    @FXML
    private Text fxRetTotalPrice;
    // ---------------------------------------------------------------

    @FXML
    private Text fxFinalPrice;

    // ----------- Controller breytur --------------------------------
    // ---------------------------------------------------------------
    private SelectFlightsBothWaysController storedSFBWC;
    private BookingController BC;
    private Flight departureFlight, returnFlight;
    // ---------------------------------------------------------------

    // ----------- Upplýsinga breytur --------------------------------
    // ---------------------------------------------------------------
    private String departureFlightId, returnFlightId;
    private String departureFlightFrom, departureFlightTo;
    private String returnFlightFrom, returnFlightTo;
    private LocalDate departureDate, returnDate;
    private List<Seat> departureFlightSeats, returnFlightSeats;
    private int numberOfPassengers;
    private int departureTotalPrice, returnTotalPrice;
    // ---------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Controller breytur stilltar -----------------------------------
        BC = new BookingController("db/flightBooking.db");
        BookingApplication application = BookingApplication.getApplicationInstance();
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
        storedSFBWC = application.getStoredBothWaysController();
        departureFlight = storedSFBWC.getSelectedDepartureFlight().getStoredFlight();
        returnFlight = storedSFBWC.getSelectedReturnFlight().getStoredFlight();

        //Upplýsingabreytur stilltar -------------------------------------
        departureFlightId = departureFlight.getFlightId();
        returnFlightId = returnFlight.getFlightId();
        departureFlightFrom = departureFlight.getDepartureAddress();
        departureFlightTo = departureFlight.getArrivalAddress();
        returnFlightFrom = returnFlight.getDepartureAddress();
        returnFlightTo = returnFlight.getArrivalAddress();
        departureDate = departureFlight.getArrivalTime();
        returnDate = returnFlight.getArrivalTime();
        departureFlightSeats = departureFlight.getSeats();
        returnFlightSeats = returnFlight.getSeats();
        numberOfPassengers = application.getStoredBAppController().getFxNumPassengers();
        departureTotalPrice = departureFlight.getPrice() * numberOfPassengers;
        returnTotalPrice = returnFlight.getPrice() * numberOfPassengers;

        // FXML upplýsingabreytur stilltar --------------------------------
        // ###### Departure flugið ######
        fxDepHeaderFlightNum.setText(departureFlightId);
        fxDepFromLocation.setText(departureFlightFrom);
        fxDepToLocation.setText(departureFlightTo);
        fxDepDate.setText(departureDate.toString());
        fxDepSeatsSelected.setText("0");
        fxDepSeatsTotal.setText(Integer.toString(numberOfPassengers));
        fxDepMinorFlightNum.setText(departureFlightId);
        fxDepTotalPrice.setText(numberFormat.format(departureTotalPrice));

        // ###### Return flugið ######
        fxRetHeaderFlightNum.setText(returnFlightId);
        fxRetFromLocation.setText(returnFlightFrom);
        fxRetToLocation.setText(returnFlightTo);
        fxRetDate.setText(returnDate.toString());
        fxRetSeatsSelected.setText("0");
        fxRetSeatsTotal.setText(Integer.toString(numberOfPassengers));
        fxRetMinorFlightNum.setText(returnFlightId);
        fxRetTotalPrice.setText(numberFormat.format(returnTotalPrice));

        // ###### Annað FXML Stillt ######
        fxFinalPrice.setText(numberFormat.format(departureTotalPrice+returnTotalPrice));
    }

    public void fxBackHandler(ActionEvent actionEvent) {
    }

    public void fxMakeBookingHandler(ActionEvent actionEvent) {
    }

    // ------===== Departure Flight Seat Mouse Events ======-----------
    // ----------------------------------------------------------------
    public void fxDepA1Click(MouseEvent mouseEvent) {
    }

    public void fxDepA2Click(MouseEvent mouseEvent) {
    }

    public void fxDepB1Click(MouseEvent mouseEvent) {
    }

    public void fxDepB2Click(MouseEvent mouseEvent) {
    }

    public void fxDepC1Click(MouseEvent mouseEvent) {
    }

    public void fxDepC2Click(MouseEvent mouseEvent) {
    }
    // ----------------------------------------------------------------

    // -------====== Return Flight Seats Mouse Events =======----------
    // ----------------------------------------------------------------

    public void fxRetA1Click(MouseEvent mouseEvent) {
    }

    public void fxRetA2Click(MouseEvent mouseEvent) {
    }

    public void fxRetB1Click(MouseEvent mouseEvent) {
    }

    public void fxRetB2Click(MouseEvent mouseEvent) {
    }

    public void fxRetC1Click(MouseEvent mouseEvent) {
    }

    public void fxRetC2Click(MouseEvent mouseEvent) {
    }
    // ----------------------------------------------------------------
}
