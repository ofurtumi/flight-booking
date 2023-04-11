package is.hi.flight_booking.ui;

import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.controller.FlightController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SelectFlightsBothWaysController implements Initializable {

    @FXML
    private VBox fxTakeoffFlightsList;
    @FXML
    private VBox fxReturnFlightsList;

    private Flight selectedDepartureFlight;
    private Flight selectedReturnFlightID;
    private BAppController flightListsBAppController;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Upphafs controller clasinn sóttur úr geymslu, og FlightController-inn sóttur þaðan.
        BookingApplication application = BookingApplication.getApplicationInstance();
        setFlightListsBAppController(application.getStoredBAppController());
        FlightController flightController = flightListsBAppController.getFlightController();

        //Hér sæki ég völdu breyturnar úr fyrsta glugga og geymi sérstakleg til sjónrænnar einföldunnar
        int numberOfPassenger = flightListsBAppController.getFxNumPassengers();
        String departureLocation = flightListsBAppController.getFxFromDest();
        String retFlightLocation = flightListsBAppController.getFxToDest();
        LocalDate departureDate = flightListsBAppController.getFxDepDate();
        LocalDate returnDate = flightListsBAppController.getFxRetDate();
        //-------------------------------------------------------------------------------

        //Listar af flugum sóttir
        List<Flight> departureFlights = flightController.searchFlights(departureLocation,
                retFlightLocation, departureDate);
        List<Flight> returnFlights = flightController.searchFlights(retFlightLocation,
                departureLocation, returnDate);

        //Listarnir rúnir flugum sem ekki eru með næg sæti (ef þau skyldu vera til staðar)
        List<Flight> updatedDepartureFlights = new ArrayList<>();
        List<Flight> updatedReturnFlights = new ArrayList<>();
        for (Flight flight : departureFlights) {
            if(flight.getNumSeatsAvailable() >= numberOfPassenger) {
                updatedDepartureFlights.add(flight);
            }
        }
        for (Flight flight : returnFlights) {
            if(flight.getNumSeatsAvailable() >= numberOfPassenger) {
                updatedReturnFlights.add(flight);
            }
        }
        listFlights(updatedDepartureFlights, updatedReturnFlights);

    }

    //Aðferðin sem býr til viðmóts elementið fyrir hvert flug
    public void listFlights(List<Flight> depFlights, List<Flight> retFlights) {
        fxTakeoffFlightsList.getChildren().clear();
        for(Flight flight : depFlights) {
            FlightView newListedDepFlight = new FlightView(flight, false, null, this);
            fxTakeoffFlightsList.getChildren().add(newListedDepFlight);
        }
        fxReturnFlightsList.getChildren().clear();
        for(Flight flight : retFlights) {
            FlightView newListedRetFlight = new FlightView(flight, true, null, this);
            fxReturnFlightsList.getChildren().add(newListedRetFlight);
        }

    }

    public void setFlightListsBAppController(BAppController theFlightListsBAppController) {
        flightListsBAppController = theFlightListsBAppController;
    }

    public BAppController getFlightListsBAppController() {
        return flightListsBAppController;
    }

    public Flight getSelectedDepartureFlight() {
        return selectedDepartureFlight;
    }

    public void setSelectedDepartureFlight(Flight selectedDepartureFlight) {
        this.selectedDepartureFlight = selectedDepartureFlight;
    }

    public Flight getSelectedReturnFlightID() {
        return selectedReturnFlightID;
    }

    public void setSelectedReturnFlightID(Flight selectedReturnFlightID) {
        this.selectedReturnFlightID = selectedReturnFlightID;
    }
}