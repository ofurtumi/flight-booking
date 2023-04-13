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
  private final BooleanProperty selected = new SimpleBooleanProperty(false);
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

        if(!getIsOneWay() && isReturnFlight() && SFBWC.getSelectedReturnFlight() != null) {
            if(this.getStoredFlight().getFlightId().equals(SFBWC.getSelectedReturnFlight().getStoredFlight().getFlightId())) {
                this.setSelected(true);
                setSelectedBG(this);
                SFBWC.setSelectedReturnFlight(this);
                setSelectedBG(SFBWC.getSelectedReturnFlight()); // semi-redundant, partur af visual haxi
                SFBWC.getSelectedReturnFlight().setSelected(true); // semi-redundant, partur af visual haxi
            }
        } else if(!getIsOneWay() && !isReturnFlight() && SFBWC.getSelectedDepartureFlight() != null) {
            if(this.getStoredFlight().getFlightId().equals(SFBWC.getSelectedDepartureFlight().getStoredFlight().getFlightId())) {
                this.setSelected(true);
                setSelectedBG(this);
                SFBWC.setSelectedDepartureFlight(this);
                setSelectedBG(SFBWC.getSelectedDepartureFlight()); // semi-redundant, partur af visual haxi
                SFBWC.getSelectedDepartureFlight().setSelected(true); // semi-redundant, partur af visual haxi
            }
        } else if(getIsOneWay() && SFOWC.getSelectedFlight() != null) {
            if(this.getStoredFlight().getFlightId().equals(SFOWC.getSelectedFlight().getStoredFlight().getFlightId())) {
                this.setSelected(true);
                setSelectedBG(this);
                SFOWC.setSelectedFlight(this);
                setSelectedBG(SFOWC.getSelectedFlight()); // semi-redundant, partur af visual haxi
                SFOWC.getSelectedFlight().setSelected(true); // semi-redundant, partur af visual haxi
            }
        }
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
        // !!! Bæta við möguleikanum að þetta gæti verið OneWay!!!
        SelectFlightsBothWaysController SFBWC = null;
        SelectFlightsOneWayController SFOWC = null;
        if(this.getIsOneWay()) {
            SFOWC = getsFOneWayController();
            if(this.isSelected()){
                System.out.println("Already selected. Currently stored ret. fligth id: "
                        + SFOWC.getSelectedFlight().getStoredFlight().getFlightId());
                e.consume();

            } else {
                FlightView oldFV = SFOWC.getSelectedFlight();
                if(oldFV != null){
                    oldFV.setSelected(false);
                    setUnselectedBG(oldFV);
                    this.setSelected(true);
                    setSelectedBG(this);
                    SFOWC.setSelectedFlight(this);
                    System.out.println("Stored return fligth id: " + SFOWC.getSelectedFlight().getStoredFlight().getFlightId());
                    e.consume();
                } else {
                    this.setSelected(true);
                    setSelectedBG(this);
                    SFOWC.setSelectedFlight(this);
                    System.out.println("Stored return fligth id: " + SFOWC.getSelectedFlight().getStoredFlight().getFlightId());
                    e.consume();
                }
            }
        } else {
            SFBWC = getsFBothWaysController();
            if(this.isReturnFlight()){
                if(this.isSelected()){
                    System.out.println("Already selected. Currently stored ret. fligth id: "
                            + SFBWC.getSelectedReturnFlight().getStoredFlight().getFlightId());
                    e.consume();
                } else {
                    FlightView oldReturnFV = SFBWC.getSelectedReturnFlight();
                    if(oldReturnFV != null) {
                        oldReturnFV.setSelected(false);
                        setUnselectedBG(oldReturnFV);
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
                    System.out.println("Already selected. Currently stored dep. fligth id: "
                            + SFBWC.getSelectedDepartureFlight().getStoredFlight().getFlightId());
                    e.consume();
                } else {
                    FlightView oldDepartureFV = SFBWC.getSelectedDepartureFlight();
                    if (oldDepartureFV != null) {
                        oldDepartureFV.setSelected(false);
                        setUnselectedBG(oldDepartureFV);
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
        if(getsFBothWaysController() != null && SFBWC.getSelectedDepartureFlight() != null){
            setSelectedBG(SFBWC.getSelectedDepartureFlight());
            SFBWC.getSelectedDepartureFlight().setSelected(true);
        }
        if(getsFBothWaysController() != null && SFBWC.getSelectedReturnFlight() != null){
            setSelectedBG(SFBWC.getSelectedReturnFlight());
            SFBWC.getSelectedReturnFlight().setSelected(true);
        }
        if(SFOWC != null && SFOWC.getSelectedFlight() != null){
            setSelectedBG(SFOWC.getSelectedFlight());
            SFOWC.getSelectedFlight().setSelected(true);
        }
    }
    public void setSelectedBG(FlightView flightView) {
        flightView.setBackground(new Background(
                new BackgroundFill(Color.LIGHTSTEELBLUE, new CornerRadii(10.0), null)));
    }
    public void setUnselectedBG(FlightView flightView) {
        flightView.setBackground(new Background(
                new BackgroundFill(Color.web("#FFFFFF"), new CornerRadii(10.0), null)));
    }
    private boolean getIsOneWay() {
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
