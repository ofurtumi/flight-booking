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
    private final String databaseURL = "db/flightBooking.db";
    private final String[] destinations = {"Akureyri", "Egilsstaðir", "Ísafjörður", "Keflavík", "Reykjavík",
            "Vestmannaeyjar"};
    private final String[] numberOfPassengers = {"1", "2", "3", "4", "5", "6"};

    private FlightController flightController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // "Báðar leiðir:" stillt á sjálfvalið
        BookingApplication theApplication = BookingApplication.getApplicationInstance();
        fxBothWays.setAllowIndeterminate(false);
        ObservableList<String> numberOfPassengersList = FXCollections.observableArrayList(numberOfPassengers);
        fxNumPassengers.setItems(numberOfPassengersList);
        ObservableList<String> destinationsList = FXCollections.observableArrayList(destinations);
        fxFromDest.setItems(destinationsList);
        fxToDest.setItems(destinationsList);

        if(!theApplication.getUseStored()) { // Fyrir fyrstu ræsingu og ef bókun tókst
            fxBothWays.setSelected(true);
            fxNumPassengers.getSelectionModel().selectFirst();

        } else {
            fxNumPassengers.getSelectionModel().select(
                    Integer.toString(theApplication.getStoredBAppController().getFxNumPassengers()));
            fxBothWays.setSelected(theApplication.getStoredBAppController().getFxBothWays());
            fxFromDest.getSelectionModel().select(theApplication.getStoredBAppController().getFxFromDest());
            fxToDest.getSelectionModel().select(theApplication.getStoredBAppController().getFxToDest());
            if(theApplication.getStoredBAppController().getFxDepDate() != null) {
                fxDepDate.setValue(theApplication.getStoredBAppController().getFxDepDate());
            }
            if(theApplication.getStoredBAppController().getFxRetDate() != null) {
                fxRetDate.setValue(theApplication.getStoredBAppController().getFxRetDate());
            }
            System.out.println("From location: " + getFxFromDest());
            System.out.println("Return location: " + getFxToDest());
            System.out.println("--------------");
        }
    }

    @FXML // Það sem gerist þegar ýtt er á "Báðar leiðir:" check-boxið
    public void fxBothWaysCheckbox(ActionEvent actionEvent) {
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
        if (getFxFromDest().equals(getFxToDest())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Villa við leit");
            alert.setHeaderText("Valdir áfangastaðir þeir sömu");
            alert.setContentText("Vinsamlegast veldu ólíkan brottfarar- og áfangastað.");
            alert.showAndWait();
            actionEvent.consume();
            return;
        }
        if (getFxBothWays()) { // Það sem gerist ef hakað er í "Báðar leiðir" þegar ýtt er á "LEITA" takkann
            if (getFxFromDest() == null || getFxToDest() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Villa við leit");
                alert.setHeaderText("Óútfylltir reitir");
                alert.setContentText("Vinsamlegast veldu bæði brottfarar- og áfangastað.");
                alert.showAndWait();
                actionEvent.consume();
            } else { // ef reitir eru útfylltir
                // Leitað af flugum eftir útfylltum reitum og athugað hvort að flug finnist með nægum sætum.
                flightController = new FlightController(databaseURL);
                List<Flight> fromFlights;
                List<Flight> retFlights;
                if(getFxDepDate() != null) {
                    fromFlights = flightController.searchFlights(getFxFromDest(),
                            getFxToDest(), getFxDepDate());
                } else {
                    System.out.println("For departure list:");
                    System.out.println("(DepDate = null) From location: " + getFxFromDest());
                    System.out.println("(RetDate = null) Return location: " + getFxToDest());
                    System.out.println("--------------");
                    fromFlights = flightController.searchFlights(getFxFromDest(), getFxToDest());
                }
                if(getFxRetDate() != null) {
                    retFlights = flightController.searchFlights(getFxToDest(),
                            getFxFromDest(), getFxRetDate());
                } else {
                    System.out.println("For return list:");
                    System.out.println("(DepDate = null) From location: " + getFxFromDest());
                    System.out.println("(RetDate = null) Return location: " + getFxToDest());
                    System.out.println("--------------");
                    retFlights = flightController.searchFlights(getFxToDest(), getFxFromDest());
                }


                System.out.println("fromFlights found: " + fromFlights.size());
                System.out.println("destFlights found: " + retFlights.size());

                boolean fromFlightWithSeats = false;
                boolean retFlightWithSeats = false;
                if (fromFlights.size() != 0 && retFlights.size() != 0) {
                    LocalDate fromFlightsMinDate = null;
                    for (Flight flight : fromFlights) { // Athuga hvort að næg sæti séu í boði frá brottfararstað
                        if(fromFlightsMinDate == null || flight.getArrivalTime().isBefore(fromFlightsMinDate)){
                            fromFlightsMinDate = flight.getArrivalTime();
                        }
                        if (flight.getNumSeatsAvailable() >= getFxNumPassengers()) {
                            fromFlightWithSeats = true;
                        }
                    }
                    for (Flight flight : retFlights) { // Athuga hvort að næg sæti séu í boði frá áfangastað
                        System.out.println("Departure min date: " + fromFlightsMinDate + "| Return min date: " + flight.getArrivalTime());
                        if (flight.getNumSeatsAvailable() >= getFxNumPassengers()
                                && flight.getArrivalTime().isAfter(fromFlightsMinDate)) {
                            retFlightWithSeats = true;
                            break;
                        }
                    }
                }

                System.out.println("fromFlights ok: " + fromFlightWithSeats);
                System.out.println("retFlights ok: " + retFlightWithSeats);

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
                    alert.setContentText("Engin flug fundust sem passa við valda möguleika eða þá að engin heimflug eru eftir brottför");
                    alert.showAndWait();
                    actionEvent.consume();
                }
            }
        } else { // Það sem gerist ef ekki er hakað í "Báðar leiðir:" checkbox-ið
            if (getFxFromDest() == null || getFxToDest() == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Villa við leit");
                alert.setHeaderText("Óútfylltir reitir");
                alert.setContentText("Vinsamlegast veldu bæði brottfarar- og áfangastað.");
                alert.showAndWait();
                actionEvent.consume();
            } else { // ef reitir eru útfylltir
                // Leitað af flugum eftir útfylltum reitum og athugað hvort að flug finnist með nægum sætum.
                flightController = new FlightController(databaseURL);
                List<Flight> fromFlights;
                if(getFxDepDate() != null) {
                    fromFlights = flightController.searchFlights(getFxFromDest(), getFxToDest(), getFxDepDate());
                } else {
                    fromFlights = flightController.searchFlights(getFxFromDest(), getFxToDest());
                }

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
                if (fromFlightWithSeats) { // Ef næg sæti í einhverju flugi í lista
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
