package is.hi.flight_booking.ui;

import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.controller.FlightController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SelectFlightsOneWayController implements Initializable {
    @FXML
    private VBox fxTakeoffFlightsList;

    private FlightView selectedFlight;
    private BAppController flightListBAppController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BookingApplication application = BookingApplication.getApplicationInstance();
        setFlightListBAppController(application.getStoredBAppController());
        FlightController flightController = flightListBAppController.getFlightController();

        //Hér sæki ég völdu breyturnar úr fyrsta glugga og geymi sérstakleg til sjónrænnar einföldunnar
        int numberOfPassenger = getFlightListBAppController().getFxNumPassengers();
        String departureLocation = getFlightListBAppController().getFxFromDest();
        String destinationFlightLocation = getFlightListBAppController().getFxToDest();
        LocalDate departureDate = getFlightListBAppController().getFxDepDate();

        List<Flight> departureFlights = flightController.searchFlights(departureLocation,
                destinationFlightLocation, departureDate);

        List<Flight> updatedDepartureFlights = new ArrayList<>();
        for (Flight flight : departureFlights) {
            if(flight.getNumSeatsAvailable() >= numberOfPassenger) {
                updatedDepartureFlights.add(flight);
            }
        }
        listFlights(updatedDepartureFlights);
    }

    @FXML
    public void fxSingleBack(ActionEvent backPressed) throws IOException {
        BookingApplication application = BookingApplication.getApplicationInstance();
        application.setStoredOneWayController(null);
        application.changeScene("/fxml/bookingApplication_View.fxml");
        backPressed.consume();
    }

    @FXML
    public void fxChooseSeatsSF(ActionEvent actionEvent) throws IOException {
        if(getSelectedFlight() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Óvalið flug");
            alert.setHeaderText("Val á flugi vantar.");
            alert.setContentText("Þú átt eftir að velja flug.");
            alert.showAndWait();
        } else {
            BookingApplication application = BookingApplication.getApplicationInstance();
            application.setStoredOneWayController(this);
            application.changeScene("/fxml/selectSeatsOneWay_View.fxml");
            actionEvent.consume();
        }
    }

    private void listFlights(List<Flight> depFlights) {
        fxTakeoffFlightsList.getChildren().clear();
        for(Flight flight : depFlights) {
            FlightView newListedDepFlight = new FlightView(flight, false, this, null);
            fxTakeoffFlightsList.getChildren().add(newListedDepFlight);
        }
    }

    public FlightView getSelectedFlight() {
        return selectedFlight;
    }

    public void setSelectedFlight(FlightView selectedFlight) {
        this.selectedFlight = selectedFlight;
    }

    public BAppController getFlightListBAppController() {
        return flightListBAppController;
    }

    public void setFlightListBAppController(BAppController flightListBAppController) {
        this.flightListBAppController = flightListBAppController;
    }
}
