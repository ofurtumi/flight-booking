//Þetta er JavaFX controllerinn fyrir upphafssenuna

package is.hi.flight_booking.ui;

import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.controller.FlightController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class BAppController implements Initializable {

    @FXML
    private ComboBox<String> fxNumPassengers;
    @FXML
    private CheckBox fxBothWays;
    @FXML
    private ComboBox<String> fxFromDest;
    @FXML
    private ComboBox<String> fxToDest;
    @FXML
    private DatePicker fxDepDate;
    @FXML
    private DatePicker fxRetDate;
    @FXML
    private Text fxHeimkomaTxt;
    @FXML
    private Button fxFindBooking;
    @FXML
    private Button fxSearchFlights;

    private final String[] destinations = {"Akureyri", "Egilsstaðir", "Ísafjörður", "Keflavík", "Reykjavík",
            "Vestmannaeyjar"};
    private final String[] numberOfPassengers = {"1", "2", "3", "4", "5", "6"};

    private FlightController flightController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // "Báðar leiðir:" stillt á sjálfvalið
        BookingApplication theApplication = BookingApplication.getApplicationInstance();

        if(!theApplication.getUseStored()) { // Fyrir fyrstu ræsingu og ef bókun tókst
            fxBothWays.setAllowIndeterminate(false);
            fxBothWays.setSelected(true);

            // Dropdown menu stillt
            ObservableList<String> numberOfPassengersList = FXCollections.observableArrayList(numberOfPassengers);
            fxNumPassengers.setItems(numberOfPassengersList);
            fxNumPassengers.getSelectionModel().selectFirst();
            ObservableList<String> destinationsList = FXCollections.observableArrayList(destinations);
            fxFromDest.setItems(destinationsList);
            fxToDest.setItems(destinationsList);
        }
    }

    @FXML // Það sem gerist þegar ýtt er á "Báðar leiðir:" check-boxið
    public void fxBothWaysCheckbox(ActionEvent actionEvent) throws IOException {
        if (fxBothWays.isSelected()) {
            fxHeimkomaTxt.setVisible(true);
            fxRetDate.setVisible(true);
        } else {
            fxHeimkomaTxt.setVisible(false);
            fxRetDate.setVisible(false);
        }
        actionEvent.consume();
    }

    @FXML // Það sem gerist þegar ýtt er á "LEITA" takkann
    public void fxSearchButton(ActionEvent actionEvent) throws IOException {
        if (getFxBothWays()) { // Það sem gerist ef hakað er í "Báðar leiðir" þegar ýtt er á "LEITA" takkann
            if (getFxFromDest() == null || getFxToDest() == null // ef reitir ekki útfylltir
                    || getFxDepDate() == null || getFxRetDate() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Villa við leit");
                alert.setHeaderText("Óútfylltir reitir");
                alert.setContentText("Vinsamlegast kláraðu val í alla viðeigandi reiti.");
                alert.showAndWait();
                actionEvent.consume();
            } else { // ef reitir eru útfylltir
                // Leitað af flugum eftir útfylltum reitum og athugað hvort að flug finnist með nægum sætum.
                flightController = new FlightController("db/test.db");
                List<Flight> fromFlights = flightController.searchFlights(getFxFromDest(),
                        getFxToDest(), getFxDepDate());
                List<Flight> retFlights = flightController.searchFlights(getFxToDest(),
                        getFxFromDest(), getFxRetDate());

                System.out.println("fromFlights found: " + fromFlights.size());
                System.out.println("destFlights found: " + retFlights.size());

                boolean fromFlightWithSeats = false;
                boolean retFlightWithSeats = false;
                if (fromFlights.size() != 0 && retFlights.size() != 0) {
                    for (Flight flight : fromFlights) { // Athuga hvort að næg sæti séu í boði frá brottfararstað
                        if (flight.getNumSeatsAvailable() >= getFxNumPassengers()) {
                            fromFlightWithSeats = true;
                            break;
                        }
                    }
                    for (Flight flight : retFlights) { // Athuga hvort að næg sæti séu í boði frá áfangastað
                        if (flight.getNumSeatsAvailable() >= getFxNumPassengers()) {
                            retFlightWithSeats = true;
                            break;
                        }
                    }
                }
                if (fromFlightWithSeats && retFlightWithSeats) { // Ef næg sæti og flug á báðum listum
                    BookingApplication bAppInstance = BookingApplication.getApplicationInstance();
                    bAppInstance.setStoredBAppController(this); // Þessi controller geymdur
                    bAppInstance.setUseStoredTrue(); // Til að vita hvort eigi að endurnýta valinn gildi ef hætt er við
                    bAppInstance.changeScene("/fxml/selectBothWays_View.fxml");
                    actionEvent.consume();
                } else { // Ef annar hvor listinn var ekki með nægum sætum
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Misheppnuð leit");
                    alert.setHeaderText("Engar niðurstöður");
                    alert.setContentText("Engin flug fundust sem passa við valda möguleika.");
                    alert.showAndWait();
                    actionEvent.consume();
                }
            }
        } else {
            if (fxFromDest.getValue() == null || fxToDest.getValue() == null
                    || fxDepDate.getValue() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Villa við leit");
                alert.setHeaderText("Óútfylltir reitir");
                alert.setContentText("Vinsamlegast kláraðu val í alla viðeigandi reiti.");
                alert.showAndWait();
                actionEvent.consume();
            } else { // ef reitir eru útfylltir
                // Leitað af flugum eftir útfylltum reitum og athugað hvort að flug finnist með nægum sætum.
                flightController = new FlightController("db/test.db");
                List<Flight> fromFlights = flightController.searchFlights(getFxFromDest(),
                        getFxToDest(), getFxDepDate());

                System.out.println("fromFlights found: " + fromFlights.size());

                boolean fromFlightWithSeats = false;

                if (fromFlights.size() != 0) {
                    for (Flight flight : fromFlights) { // Athuga hvort að næg sæti séu í boði frá brottfararstað
                        if (flight.getNumSeatsAvailable() >= getFxNumPassengers()) {
                            fromFlightWithSeats = true;
                            break;
                        }
                    }
                }
                if (fromFlightWithSeats) { // Ef næg sæti og flug á báðum listum
                    BookingApplication bAppInstance = BookingApplication.getApplicationInstance();
                    bAppInstance.setStoredBAppController(this); // Þessi controller geymdur
                    bAppInstance.setUseStoredTrue(); // Til að vita hvort eigi að endurnýta valinn gildi ef hætt er við
                    bAppInstance.changeScene("/fxml/selectOneWay_View.fxml");
                    actionEvent.consume();
                } else { // Ef listinn var ekki með nægum sætum
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Misheppnuð leit");
                    alert.setHeaderText("Engar niðurstöður");
                    alert.setContentText("Engin flug fundust sem passa við valda möguleika.");
                    alert.showAndWait();
                    actionEvent.consume();
                }
            }
        }
    }

    @FXML // Það sem gerist þegar ýtt er á "Finna bókun" takkann
    public void fxSearchForBooking(ActionEvent actionEvent) {
        System.out.println("Þetta verður mögulega virkjað(?)");
        actionEvent.consume();
    }
    // Getterar fyrir valmöguleikana
    public int getFxNumPassengers() {
        return Integer.parseInt(fxNumPassengers.getSelectionModel().getSelectedItem());
    }

    public boolean getFxBothWays() {
        return fxBothWays.isSelected();
    }

    public String getFxFromDest() {
        return fxFromDest.getSelectionModel().getSelectedItem();
    }

    public String getFxToDest() {
        return fxToDest.getSelectionModel().getSelectedItem();
    }

    public LocalDate getFxDepDate() {
        return fxDepDate.getValue();
    }

    public LocalDate getFxRetDate() {
        return fxRetDate.getValue();
    }

    public FlightController getFlightController() {
        return flightController;
    }
}
