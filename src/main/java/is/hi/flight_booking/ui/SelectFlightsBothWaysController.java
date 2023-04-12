package is.hi.flight_booking.ui;

import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.controller.FlightController;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    private FlightView selectedDepartureFlight;
    private FlightView selectedReturnFlight;
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

    /*
    public void deselect(boolean isReturnList) {
        if(isReturnList) {
            ObservableList<Node> listedRetFlights = fxReturnFlightsList.getChildren();
            for(Node node : listedRetFlights) {
                FlightView fw = (FlightView) node;
                if(fw.isSelected()) {
                    fw.setSelected(false);
                    setSelectedReturnFlight(null);
                    fw.setUnselectedBG();
                }
            }
        } else {
            ObservableList<Node> listedDepFlights = fxTakeoffFlightsList.getChildren();
            for(Node node : listedDepFlights) {
                FlightView fw = (FlightView) node;
                if(fw.isSelected()) {
                    fw.setSelected(false);
                    setSelectedDepartureFlight(null);
                    fw.setUnselectedBG();
                }
            }
        }
    }
     */

    public void setFlightListsBAppController(BAppController theFlightListsBAppController) {
        flightListsBAppController = theFlightListsBAppController;
    }

    public BAppController getFlightListsBAppController() {
        return flightListsBAppController;
    }

    public FlightView getSelectedDepartureFlight() {
        return selectedDepartureFlight;
    }

    public void setSelectedDepartureFlight(FlightView selectedDepartureFlight) {
        this.selectedDepartureFlight = selectedDepartureFlight;
    }

    public FlightView getSelectedReturnFlight() {
        return selectedReturnFlight;
    }

    public void setSelectedReturnFlight(FlightView selectedReturnFlight) {
        this.selectedReturnFlight = selectedReturnFlight;
    }
}
