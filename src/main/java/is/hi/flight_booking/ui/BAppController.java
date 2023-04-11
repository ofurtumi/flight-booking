//Þetta er JavaFX controllerinn fyrir upphafssenuna

package is.hi.flight_booking.ui;

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

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // "Báðar leiðir:" stillt á sjálfvalið
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
    if (getFxBothWays()) {
      if (fxFromDest.getValue() == null || fxToDest.getValue() == null
          || fxDepDate.getValue() == null || fxRetDate.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Villa við leit");
        alert.setHeaderText("Óútfylltir reitir");
        alert.setContentText("Vinsamlegast kláraðu val í alla viðeigandi reiti.");
        alert.showAndWait();
      } else {
        // Hér á auðvitað að eftir að græja það sem gerist þegar leitarskilyrði eru
        // uppfyllt
        System.out.println("Allir reitir úfyllitir / Halda áfram");
        BookingApplication bAppInstance = BookingApplication.getApplicationInstance();
        bAppInstance.changeScene("/fxml/selectBothWays_View.fxml");
        actionEvent.consume();
      }
    } else {
      if (fxFromDest.getValue() == null || fxToDest.getValue() == null
          || fxDepDate.getValue() == null) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Villa við leit");
        alert.setHeaderText("Óútfylltir reitir");
        alert.setContentText("Vinsamlegast kláraðu val í alla viðeigandi reiti.");
        alert.showAndWait();
      } else {
        // Hér á auðvitað að eftir að græja það sem gerist þegar leitarskilyrði eru
        // uppfyllt
        BookingApplication bAppInstance = BookingApplication.getApplicationInstance();
        bAppInstance.changeScene("/fxml/selectBothWays_View.fxml");
        actionEvent.consume();
        System.out.println("Allir reitir úfyllitir / Halda áfram");
      }
    }
    actionEvent.consume();
  }

  @FXML // Það sem gerist þegar ýtt er á "Finna bókun" takkann
  public void fxSearchForBooking(ActionEvent actionEvent) {
    System.out.println("Þetta verður mögulega virkjað(?)");
    actionEvent.consume();
  }

  private void parametersSelectedOneWay(Button button) {
    button.disableProperty().bind(fxFromDest.valueProperty().isNull()
        .or(fxToDest.valueProperty().isNull().or(fxDepDate.valueProperty().isNull())));
  }

  private void parametersSelectedBothWays(Button button) {
    button.disableProperty().bind(fxFromDest.valueProperty().isNull()
        .or(fxToDest.valueProperty().isNull().or(fxDepDate.valueProperty().isNull()
            .or(fxRetDate.valueProperty().isNull()))));
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
}
