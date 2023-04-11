package is.hi.flight_booking.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import is.hi.flight_booking.application.Flight;


public class FlightView extends HBox {
    // Hér er auðvitað pínu furðulegt í samhengi við það að maður á að geta sorterað
    // eftir tíma
    // að enginn tími er í raun fáanlegur á fluginn- bara dagsetning.
    @FXML
    private Text fxListedFlightID;
    @FXML
    private Text fxListedFlightFrom;
    @FXML
    private Text fxListedFlightDest;
    @FXML
    private Text fxListedFlightSeatsAvailable;
    @FXML
    private Text fxListedFlightTotalSeats;
    @FXML
    private Text fxListedFlightDate;
    @FXML
    private Text fxListedFlightPrice;

    private final Flight storedFlight;
    private final boolean isReturnFlight;
    private final boolean isOneWay;
    private boolean isSelected = false;

    private SelectFlightsOneWayController sFOneWayController;
    private SelectFlightsBothWaysController sFBothWaysController;


    // Flug tekið inn og geymt auk þess sem uppl. eru settar inn
    public FlightView(Flight flight, boolean isRetFlight,
                      SelectFlightsOneWayController SFOWC, SelectFlightsBothWaysController SFBWC) {
        if(SFOWC != null) {
            sFOneWayController = SFOWC;
            isOneWay = true;
        } else {
            sFBothWaysController = SFBWC;
            isOneWay = false;
        }
        readFlightView();
        storedFlight = flight;
        isReturnFlight = isRetFlight;
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
        String formattedPrice = numberFormat.format(storedFlight.getPrice());
        fxListedFlightID.setText(storedFlight.getFlightId());
        fxListedFlightFrom.setText(storedFlight.getDepartureAddress());
        fxListedFlightDest.setText(storedFlight.getArrivalAddress());
        fxListedFlightSeatsAvailable.setText(Integer.toString(storedFlight.getNumSeatsAvailable()));
        fxListedFlightTotalSeats
                .setText(Integer.toString(storedFlight.getNumSeatsAvailable() + storedFlight.getNumSeatsReserved()));
        fxListedFlightDate.setText(storedFlight.getArrivalTime().toString());
        fxListedFlightPrice.setText(formattedPrice);
    }

    private void readFlightView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/flight_view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @FXML
    public void fxListedFlightClickedHandler(MouseEvent e) {
        if(getIsOneWay()) {
            //Hér á eftir að útfæara oneWay
            return;
        } else {
            if(isReturnFlight()){
            //Hér á eftir að útfæra
            }

        }
        e.consume();
    }

    private boolean getIsOneWay(){
        return isOneWay;
    }

    public boolean isReturnFlight() {
        return isReturnFlight;
    }

    public Flight getStoredFlight() {
        return storedFlight;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
    // Hér á eftir að bæta við virkni. Þ.e.a.s hvað gerist þegar það er smellt á
    // FlightView.
}
