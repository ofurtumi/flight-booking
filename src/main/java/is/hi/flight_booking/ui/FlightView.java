package is.hi.flight_booking.ui;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
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
    private BooleanProperty selected = new SimpleBooleanProperty(false);

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
        SelectFlightsBothWaysController SFBWC = getsFBothWaysController();
        if(this.getIsOneWay()) {
            e.consume();
            //Hér á eftir að útfæara oneWay
            return;
        } else {
            if(this.isReturnFlight()){
                if(this.isSelected()){
                    e.consume();
                } else {
                    FlightView oldReturnFW = SFBWC.getSelectedReturnFlight();
                    if(oldReturnFW != null) {
                        oldReturnFW.setSelected(false);
                        setUnselectedBG(oldReturnFW);
                        this.setSelected(true);
                        setSelectedBG(this);
                        SFBWC.setSelectedReturnFlight(this);
                        System.out.println("Stored return fligth id: " + SFBWC.getSelectedReturnFlight().getStoredFlight().getFlightId());
                        e.consume();
                    } else {
                        this.setSelected(true);
                        setSelectedBG(this);
                        SFBWC.setSelectedReturnFlight(this);
                        System.out.println("Stored return fligth id: " + SFBWC.getSelectedReturnFlight().getStoredFlight().getFlightId());
                        e.consume();
                    }
                }

            } else {
                if(this.isSelected()){
                    e.consume();
                } else {
                    FlightView oldDepartureFW = SFBWC.getSelectedDepartureFlight();
                    if (oldDepartureFW != null) {
                        oldDepartureFW.setSelected(false);
                        setUnselectedBG(oldDepartureFW);
                        this.setSelected(true);
                        setSelectedBG(this);
                        SFBWC.setSelectedDepartureFlight(this);
                        System.out.println("Stored departure fligth id: " + SFBWC.getSelectedDepartureFlight().getStoredFlight().getFlightId());
                        e.consume();
                    } else {
                        this.setSelected(true);
                        setSelectedBG(this);
                        SFBWC.setSelectedDepartureFlight(this);
                        System.out.println("Stored departure fligth id: " + SFBWC.getSelectedDepartureFlight().getStoredFlight().getFlightId());
                        e.consume();
                    }
                }
            }
        }
        if(SFBWC.getSelectedDepartureFlight() != null){
            setSelectedBG(SFBWC.getSelectedDepartureFlight());
            SFBWC.getSelectedDepartureFlight().setSelected(true);
        }
        if(SFBWC.getSelectedReturnFlight() != null){
            setSelectedBG(SFBWC.getSelectedReturnFlight());
            SFBWC.getSelectedReturnFlight().setSelected(true);
        }
    }
    public void setSelectedBG(FlightView flightView) {
        flightView.setBackground(new Background(
                new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(10.0), null)));
    }
    public void setUnselectedBG(FlightView flightView) {
        flightView.setBackground(new Background(
                new BackgroundFill(Color.web("#F4F4F4"), new CornerRadii(10.0), null)));
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
        return selected.get();
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public SelectFlightsOneWayController getsFOneWayController() {
        return sFOneWayController;
    }

    public SelectFlightsBothWaysController getsFBothWaysController() {
        return sFBothWaysController;
    }
    // Hér á eftir að bæta við virkni. Þ.e.a.s hvað gerist þegar það er smellt á
    // FlightView.
}
