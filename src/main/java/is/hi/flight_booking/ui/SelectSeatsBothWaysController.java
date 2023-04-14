package is.hi.flight_booking.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SelectSeatsBothWaysController implements Initializable {

    // ----------- FMXL breytur for Departure Flight -----------------
    // ---------------------------------------------------------------
    @FXML
    private Text fxDepHeaderFlightNum;
    @FXML
    private Text fxDepFromLocation;
    @FXML
    private Text fxDepToLocation;
    @FXML
    private LocalDate fxDepDate;
    @FXML
    private Text fxDepSeatsSelected;
    @FXML
    private Text fxDepSeatsTotal;
    @FXML
    private VBox fxDepA1;
    @FXML
    private VBox fxDepA2;
    @FXML
    private VBox fxDepB1;
    @FXML
    private VBox fxDepB2;
    @FXML
    private VBox fxDepC1;
    @FXML
    private VBox fxDepC2;
    @FXML
    private TextField fxDepUserID;
    @FXML
    private Text fxDepMinorFlightNum;
    @FXML
    private Text fxDepTotalPrice;
    // ---------------------------------------------------------------

    // ----------- FMXL breytur for Return Flight --------------------
    // ---------------------------------------------------------------
    @FXML
    private Text fxRetHeaderFlightNum;
    @FXML
    private Text fxRetFromLocation;
    @FXML
    private Text fxRetToLocation;
    @FXML
    private LocalDate fxRetDate;
    @FXML
    private Text fxRetSeatsSelected;
    @FXML
    private Text fxRetSeatsTotal;
    @FXML
    private VBox fxRetA1;
    @FXML
    private VBox fxRetA2;
    @FXML
    private VBox fxRetB1;
    @FXML
    private VBox fxRetB2;
    @FXML
    private VBox fxRetC1;
    @FXML
    private VBox fxRetC2;
    @FXML
    private TextField fxRetUserID;
    @FXML
    private Text fxRetMinorFlightNum;
    @FXML
    private Text fxRetTotalPrice;
    // ---------------------------------------------------------------

    @FXML
    private Text fxFinalPrice;

    // ----------- Controller breytur --------------------------------
    // ---------------------------------------------------------------

    // ---------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    // ------===== Departure Flight Seat Mouse Events ======-----------
    // ----------------------------------------------------------------
    public void fxDepA1Click(MouseEvent mouseEvent) {
    }

    public void fxDepA2Click(MouseEvent mouseEvent) {
    }

    public void fxDepB1Click(MouseEvent mouseEvent) {
    }

    public void fxDepB2Click(MouseEvent mouseEvent) {
    }

    public void fxDepC1Click(MouseEvent mouseEvent) {
    }

    public void fxDepC2Click(MouseEvent mouseEvent) {
    }
    // ----------------------------------------------------------------

    // -------====== Return Flight Seats Mouse Events =======----------
    // ----------------------------------------------------------------

    public void fxRetA1Click(MouseEvent mouseEvent) {
    }

    public void fxRetA2Click(MouseEvent mouseEvent) {
    }

    public void fxRetB1Click(MouseEvent mouseEvent) {
    }

    public void fxRetB2Click(MouseEvent mouseEvent) {
    }

    public void fxRetC1Click(MouseEvent mouseEvent) {
    }

    public void fxRetC2Click(MouseEvent mouseEvent) {
    }
    // ----------------------------------------------------------------
}
