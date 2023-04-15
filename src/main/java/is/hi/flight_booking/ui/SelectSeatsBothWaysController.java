package is.hi.flight_booking.ui;

import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.controller.BookingController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

    private boolean fxDepA1Res = false, fxDepA2Res = false, fxDepB1Res = false,
            fxDepB2Res = false, fxDepC1Res = false, fxDepC2Res = false,
            fxRetA1Res= false, fxRetA2Res= false, fxRetB1Res= false,
            fxRetB2Res= false, fxRetC1Res= false, fxRetC2Res= false;
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
        setStartSeats(departureFlightSeats, returnFlightSeats);
        setSeatSelectedBG(fxDepA1);
    }

    private void setSeatReservedBG(VBox seatIcon) {
        seatIcon.setBackground(new Background(
                new BackgroundFill(Color.RED, new CornerRadii(10.0), null)));
    }

    private void setSeatUnselectedBG(VBox seatIcon) {
        seatIcon.setBackground(new Background(
                new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(10.0), null)));
    }

    private void setSeatSelectedBG(VBox seatIcon) {
        seatIcon.setBackground(new Background(
                new BackgroundFill(Color.LIMEGREEN, new CornerRadii(10.0), null)));
    }

    // Aðferð til þess að stilla upphafsástand sæta í fxml
    private void setStartSeats(List<Seat> depSeats, List<Seat> retSeats) {
        for(Seat seat : depSeats) {
            String seatId = seat.getId();
            boolean reserved = seat.isReserved();
            System.out.println("Departure seatId: " + seatId + "| is reserved: " + reserved);

            switch (seatId) {
                case "A1":
                    if(reserved){ fxDepA1Res = true; Platform.runLater(() -> setSeatReservedBG(fxDepA1));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxDepA1)); }
                    break;
                case "A2":
                    System.out.println("In case 'A2' for dep.");
                    if(reserved){ fxDepA2Res = true; Platform.runLater(() -> setSeatReservedBG(fxDepA2));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxDepA2)); }
                    break;
                case "B1":
                    System.out.println("In case 'B1' for dep.");
                    if(reserved){ fxDepB1Res = true; Platform.runLater(() -> setSeatReservedBG(fxDepB1));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxDepB1)); }
                    break;
                case "B2":
                    System.out.println("In case 'B2' for dep.");
                    if(reserved){ fxDepB2Res = true; Platform.runLater(() -> setSeatReservedBG(fxDepB2));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxDepB2)); }
                    break;
                case "C1":
                    System.out.println("In case 'C1' for dep.");
                    if(reserved){ fxDepC1Res = true; Platform.runLater(() -> setSeatReservedBG(fxDepC1));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxDepC1)); }
                    break;
                case "C2":
                    System.out.println("In case 'C2' for dep.");
                    if(reserved){ fxDepC2Res = true; Platform.runLater(() -> setSeatReservedBG(fxDepC2));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxDepC2)); }
                    break;
                default:
                    System.out.println("Eitthvað fór úrskeiðis í sætalitun Dep");
            }
        }
        for(Seat seat : retSeats) {
            String seatId = seat.getId();
            boolean reserved = seat.isReserved();
            System.out.println("Return seatId: " + seatId + "| is reserved: " + reserved);

            switch (seatId) {
                case "A1":
                    if(reserved){ fxRetA1Res = true; Platform.runLater(() -> setSeatReservedBG(fxRetA1));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxRetA1)); }
                    break;
                case "A2":
                    System.out.println("In case 'A2' for Ret.");
                    if(reserved){ fxRetA2Res = true; Platform.runLater(() -> setSeatReservedBG(fxRetA2));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxRetA2)); }
                    break;
                case "B1":
                    System.out.println("In case 'B1' for Ret.");
                    if(reserved){ fxRetB1Res = true; Platform.runLater(() -> setSeatReservedBG(fxRetB1));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxRetB1)); }
                    break;
                case "B2":
                    System.out.println("In case 'B2' for Ret.");
                    if(reserved){ fxRetB2Res = true; Platform.runLater(() -> setSeatReservedBG(fxRetB2));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxRetB2)); }
                    break;
                case "C1":
                    System.out.println("In case 'C1' for Ret.");
                    if(reserved){ fxRetC1Res = true; Platform.runLater(() -> setSeatReservedBG(fxRetC1));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxRetC1)); }
                    break;
                case "C2":
                    System.out.println("In case 'C2' for Ret.");
                    if(reserved){ fxRetC2Res = true; Platform.runLater(() -> setSeatReservedBG(fxRetC2));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxRetC2)); }
                    break;
                default:
                    System.out.println("Eitthvað fór úrskeiðis í sætalitun Ret");
            }
        }
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
