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
    // Upphafs controller clasinn sóttur úr geymslu, og FlightController-inn sóttur
    // þaðan.
    BookingApplication application = BookingApplication.getApplicationInstance();
    setFlightListsBAppController(application.getStoredBAppController());
    FlightController flightController = flightListsBAppController.getFlightController();

    // Hér sæki ég völdu breyturnar úr fyrsta glugga og geymi sérstakleg til
    // sjónrænnar einföldunnar
    int numberOfPassenger = flightListsBAppController.getFxNumPassengers();
    String departureLocation = flightListsBAppController.getFxFromDest();
    String retFlightLocation = flightListsBAppController.getFxToDest();
    LocalDate departureDate = flightListsBAppController.getFxDepDate();
    LocalDate returnDate = flightListsBAppController.getFxRetDate();
    // -------------------------------------------------------------------------------

    // Listar af flugum sóttir
    List<Flight> departureFlights = flightController.searchFlights(departureLocation,
        retFlightLocation, departureDate);
    List<Flight> returnFlights = flightController.searchFlights(retFlightLocation,
        departureLocation, returnDate);

    // Listarnir rúnir flugum sem ekki eru með næg sæti (ef þau skyldu vera til
    // staðar)
    List<Flight> updatedDepartureFlights = new ArrayList<>();
    List<Flight> updatedReturnFlights = new ArrayList<>();

    // Flug með ekki næg sæti filteruð út
    for (Flight flight : departureFlights) {
      if (flight.getNumSeatsAvailable() >= numberOfPassenger) {
        updatedDepartureFlights.add(flight);
      }
    }
    for (Flight flight : returnFlights) {
      if (flight.getNumSeatsAvailable() >= numberOfPassenger) {
        updatedReturnFlights.add(flight);
      }
    }
    // ---------------------------

    // Passað upp á að ekki séu birt flug sem ekki passa saman (ef dags er valin
    // fyrir aðra leiðina)
    if ((updatedDepartureFlights.size() == 1 && updatedReturnFlights.size() > 1)
        || (updatedReturnFlights.size() == 1 && updatedDepartureFlights.size() > 1)) {
      if (updatedDepartureFlights.size() == 1) {
        LocalDate fromFlightDate = updatedDepartureFlights.get(0).getArrivalTime();
        List<Flight> newRetList = new ArrayList<>();
        for (Flight flight : updatedReturnFlights) {
          if (flight.getArrivalTime().isAfter(fromFlightDate)) {
            newRetList.add(flight);
          }
        }
        updatedReturnFlights = newRetList;
      } else {
        LocalDate retFlightDate = updatedReturnFlights.get(0).getArrivalTime();
        List<Flight> newDepList = new ArrayList<>();
        for (Flight flight : updatedDepartureFlights) {
          if (flight.getArrivalTime().isBefore(retFlightDate)) {
            newDepList.add(flight);
          }
        }
        updatedDepartureFlights = newDepList;
      }
    } else { // passað að ekki séu bakaflug með dagsetningu sem er >= dagsetning fyrsta
             // brottfaraflugs
      LocalDate fromFlightMinDate = null;
      List<Flight> newRetList = new ArrayList<>();
      for (Flight flight : updatedDepartureFlights) {
        if (fromFlightMinDate == null || flight.getArrivalTime().isBefore(fromFlightMinDate)) {
          fromFlightMinDate = flight.getArrivalTime();
        }
      }
      for (Flight flight : updatedReturnFlights) {
        if (flight.getArrivalTime().isAfter(fromFlightMinDate)) {
          newRetList.add(flight);
        }
      }
      updatedReturnFlights = newRetList;
    }
    listFlights(updatedDepartureFlights, updatedReturnFlights);

  }

  @FXML
  public void fxBack(ActionEvent backPressed) throws IOException {
    BookingApplication application = BookingApplication.getApplicationInstance();
    application.setStoredBothWaysController(null);
    application.changeScene("/flight_fxml/bookingApplication_view.fxml");
    backPressed.consume();
  }

  public void fxSelectSeats(ActionEvent actionEvent) throws IOException {
    if ((getSelectedDepartureFlight() != null && getSelectedReturnFlight() != null)
        && getSelectedDepartureFlight().getStoredFlight().getArrivalTime()
            .isBefore(getSelectedReturnFlight().getStoredFlight().getArrivalTime())) {
      BookingApplication application = BookingApplication.getApplicationInstance();
      application.setStoredBothWaysController(this);
      application.changeScene("/flight_fxml/selectSeatsBothWays_view.fxml");
      actionEvent.consume();
    } else {
      if (getSelectedDepartureFlight() == null || getSelectedReturnFlight() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Óvalið flug");
        alert.setHeaderText("Val á flugi vantar.");
        alert.setContentText("Þú átt eftir að velja flug fyrir bæði brottför og/eða heimferð.");
        alert.showAndWait();
        if (getSelectedDepartureFlight() != null) {
          getSelectedDepartureFlight().setSelectedBG(getSelectedDepartureFlight()); // visual hax
        }
        if (getSelectedReturnFlight() != null) {
          getSelectedReturnFlight().setSelectedBG(getSelectedReturnFlight()); // visual hax
        }
        actionEvent.consume();
      } else {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ógilt Val");
        alert.setHeaderText("Misræmi í dags. valinna fluga");
        alert.setContentText("Dagsetning brottfarar verður að vera á undan dagsetning heimkomu");
        alert.showAndWait();
        getSelectedDepartureFlight().setSelectedBG(getSelectedDepartureFlight()); // visual hax
        getSelectedReturnFlight().setSelectedBG(getSelectedReturnFlight()); // visual hax
        actionEvent.consume();
      }
    }
  }

  // Aðferðin sem býr til viðmóts elementið fyrir hvert flug
  private void listFlights(List<Flight> depFlights, List<Flight> retFlights) {
    fxTakeoffFlightsList.getChildren().clear();
    for (Flight flight : depFlights) {
      FlightView newListedDepFlight = new FlightView(flight, false, null, this);
      fxTakeoffFlightsList.getChildren().add(newListedDepFlight);
    }
    fxReturnFlightsList.getChildren().clear();
    for (Flight flight : retFlights) {
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
