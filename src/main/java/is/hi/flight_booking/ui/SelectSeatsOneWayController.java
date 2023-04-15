package is.hi.flight_booking.ui;

import is.hi.flight_booking.application.Booking;
import is.hi.flight_booking.application.Flight;
import is.hi.flight_booking.application.Seat;
import is.hi.flight_booking.application.User;
import is.hi.flight_booking.controller.BookingController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class SelectSeatsOneWayController implements Initializable {

    @FXML
    private Text fxDepHeaderFlightNum;
    @FXML
    private Text fxDepFromLocation, fxDepToLocation;
    @FXML
    private Text fxDepDate;
    @FXML
    private Text fxDepSeatsSelected, fxDepSeatsTotal;
    @FXML
    private VBox fxDepA1, fxDepA2, fxDepB1, fxDepB2, fxDepC1, fxDepC2;
    @FXML
    private Text fxDepMinorFlightNum;
    @FXML
    private Text fxDepTotalPrice;

    @FXML
    private Text fxFinalPrice;
    @FXML
    private TextField fxUserID;
    @FXML
    private TextField fxUserName;

    private SelectFlightsOneWayController storedSFOWC;
    private BookingController BC;
    private Flight departureFlight;

    private boolean fxDepA1Res = false, fxDepA2Res = false, fxDepB1Res = false,
            fxDepB2Res = false, fxDepC1Res = false, fxDepC2Res = false;
    private boolean fxDepA1Selected = false, fxDepA2Selected = false, fxDepB1Selected = false,
            fxDepB2Selected = false, fxDepC1Selected = false, fxDepC2Selected = false;

    private int depSeatsSelected = 0;

    private String departureFlightId;
    private String departureFlightFrom, departureFlightTo;
    private LocalDate departureDate;
    private List<Seat> departureFlightSeats;
    private int numberOfPassengers;
    private int departureTotalPrice;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BC = new BookingController("db/flightBooking.db");
        BookingApplication application = BookingApplication.getApplicationInstance();
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
        storedSFOWC = application.getStoredOneWayController();
        departureFlight = storedSFOWC.getSelectedFlight().getStoredFlight();


        //Upplýsingabreytur stilltar -------------------------------------
        departureFlightId = departureFlight.getFlightId();
        departureFlightFrom = departureFlight.getDepartureAddress();
        departureFlightTo = departureFlight.getArrivalAddress();
        departureDate = departureFlight.getArrivalTime();
        departureFlightSeats = departureFlight.getSeats();
        numberOfPassengers = application.getStoredBAppController().getFxNumPassengers();
        departureTotalPrice = departureFlight.getPrice() * numberOfPassengers;

        fxDepHeaderFlightNum.setText(departureFlightId);
        fxDepFromLocation.setText(departureFlightFrom);
        fxDepToLocation.setText(departureFlightTo);
        fxDepDate.setText(departureDate.toString());
        fxDepSeatsSelected.setText("0");
        fxDepSeatsTotal.setText(Integer.toString(numberOfPassengers));
        fxDepMinorFlightNum.setText(departureFlightId);
        fxDepTotalPrice.setText(numberFormat.format(departureTotalPrice));
        fxFinalPrice.setText(numberFormat.format(departureTotalPrice));
        setStartSeats(departureFlightSeats);
    }

    private void setSeatReservedBG(VBox seatIcon) {
        seatIcon.setBackground(new Background(
                new BackgroundFill(Color.RED, new CornerRadii(10.0), null)));
    }

    private void setSeatUnselectedBG(VBox seatIcon) {
        seatIcon.setBackground(new Background(
                new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(10.0), null)));
    }

    private void setSeatSelectedBG(VBox seatIcon) {
        seatIcon.setBackground(new Background(
                new BackgroundFill(Color.LIMEGREEN, new CornerRadii(10.0), null)));
    }

    private void setStartSeats(List<Seat> depSeats) {
        for(Seat seat : depSeats) {
            String seatId = seat.getId();
            boolean reserved = seat.isReserved();
            System.out.println("Departure seatId: " + seatId + "| is reserved: " + reserved);

            switch (seatId) {
                case "A1":
                    if(reserved){ fxDepA1Res = true; Platform.runLater(() -> setSeatReservedBG(fxDepA1));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxDepA1)); }
                    break;
                case "A2":
                    System.out.println("In case 'A2' for dep.");
                    if(reserved){ fxDepA2Res = true; Platform.runLater(() -> setSeatReservedBG(fxDepA2));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxDepA2)); }
                    break;
                case "B1":
                    System.out.println("In case 'B1' for dep.");
                    if(reserved){ fxDepB1Res = true; Platform.runLater(() -> setSeatReservedBG(fxDepB1));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxDepB1)); }
                    break;
                case "B2":
                    System.out.println("In case 'B2' for dep.");
                    if(reserved){ fxDepB2Res = true; Platform.runLater(() -> setSeatReservedBG(fxDepB2));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxDepB2)); }
                    break;
                case "C1":
                    System.out.println("In case 'C1' for dep.");
                    if(reserved){ fxDepC1Res = true; Platform.runLater(() -> setSeatReservedBG(fxDepC1));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxDepC1)); }
                    break;
                case "C2":
                    System.out.println("In case 'C2' for dep.");
                    if(reserved){ fxDepC2Res = true; Platform.runLater(() -> setSeatReservedBG(fxDepC2));
                    } else { Platform.runLater(() -> setSeatUnselectedBG(fxDepC2)); }
                    break;
                default:
                    System.out.println("Eitthvað fór úrskeiðis í sætalitun Dep");
            }
        }
    }

    public void fxBackHandlerSF(ActionEvent actionEvent) throws IOException {
        BookingApplication application = BookingApplication.getApplicationInstance();
        application.setStoredOneWayController(null);
        application.changeScene("/fxml/selectOneWay_View.fxml");
        actionEvent.consume();
    }

    public void fxMakeBookingHandlerSF(ActionEvent actionEvent) throws IOException {
        if(depSeatsSelected != numberOfPassengers) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Óklárað sætaval");
            alert.setHeaderText("Ekki búið að velja öll sæti!");
            alert.setContentText("Vertu viss um að þú hafir valið öll umbeðin sæti í fluginu.");
            alert.showAndWait();
            actionEvent.consume();
        } else if(fxUserName.getText() == null || fxUserName.getText().length() == 0
                || fxUserName.getText().length() > 30) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nafn í ólagi");
            alert.setHeaderText("Nafn ekki í gildu formi!");
            alert.setContentText("Nafn verður að vera a.m.k. 1 bókstafur og í mesta lagi 30 stafir");
            alert.showAndWait();
            actionEvent.consume();
        } else if(fxUserID.getText() == null || fxUserID.getText().length() != 10) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Kennitala í ólagi");
            alert.setHeaderText("Kennitala ekki í gildu formi!");
            alert.setContentText("Sláðu inn nákvæmlega 10 stafa kennitölu án bila eða merkja.");
            alert.showAndWait();
            actionEvent.consume();
        } else {
            User newUser = new User(fxUserID.getText(), fxUserName.getText());
            List<Seat> selectedDepSeats = new ArrayList<>();

            if (fxDepA1Selected) {
                for (Seat seat : departureFlightSeats) {
                    if (seat.getId().equals("A1")) {
                        selectedDepSeats.add(seat);
                        break;
                    }
                }
            }
            if (fxDepA2Selected) {
                for (Seat seat : departureFlightSeats) {
                    if (seat.getId().equals("A2")) {
                        selectedDepSeats.add(seat);
                        break;
                    }
                }
            }
            if (fxDepB1Selected) {
                for (Seat seat : departureFlightSeats) {
                    if (seat.getId().equals("B1")) {
                        selectedDepSeats.add(seat);
                        break;
                    }
                }
            }
            if (fxDepB2Selected) {
                for (Seat seat : departureFlightSeats) {
                    if (seat.getId().equals("B2")) {
                        selectedDepSeats.add(seat);
                        break;
                    }
                }
            }
            if (fxDepC1Selected) {
                for (Seat seat : departureFlightSeats) {
                    if (seat.getId().equals("C1")) {
                        selectedDepSeats.add(seat);
                        break;
                    }
                }
            }
            if (fxDepC2Selected) {
                for (Seat seat : departureFlightSeats) {
                    if (seat.getId().equals("C2")) {
                        selectedDepSeats.add(seat);
                        break;
                    }
                }
            }

            Booking depBooking = BC.createBooking(departureFlight, newUser, selectedDepSeats);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Bókun staðfest");
            alert.setHeaderText("Bókun tókst!");
            alert.setContentText("Bókunarnúmer:\nBrottför: " + depBooking.getBookingID());
            alert.showAndWait();

            BookingApplication application = BookingApplication.getApplicationInstance();
            application.setStoredBAppController(null);
            application.setStoredOneWayController(null);
            application.setUseStoredFalse();
            application.changeScene("/fxml/bookingApplication_View.fxml");
            actionEvent.consume();

        }
    }
    public void fxDepA1Click(MouseEvent mouseEvent) {
        if(fxDepA1Res) {
            System.out.println("Sæti A1 er frátekið");
            mouseEvent.consume();
        } else {
            if(fxDepA1Selected) {
                fxDepA1Selected = false;
                depSeatsSelected--;
                fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
                Platform.runLater(() -> setSeatUnselectedBG(fxDepA1));
                mouseEvent.consume();
            } else {
                if(numberOfPassengers == depSeatsSelected) {
                    System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
                    mouseEvent.consume();
                } else {
                    fxDepA1Selected = true;
                    depSeatsSelected++;
                    fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
                    Platform.runLater(() -> setSeatSelectedBG(fxDepA1));
                    mouseEvent.consume();
                }
            }
        }
    }

    public void fxDepA2Click(MouseEvent mouseEvent) {
        if(fxDepA2Res) {
            System.out.println("Sæti A2 er frátekið");
            mouseEvent.consume();
        } else {
            if(fxDepA2Selected) {
                fxDepA2Selected = false;
                depSeatsSelected--;
                fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
                Platform.runLater(() -> setSeatUnselectedBG(fxDepA2));
                mouseEvent.consume();
            } else {
                if(numberOfPassengers == depSeatsSelected) {
                    System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
                    mouseEvent.consume();
                } else {
                    fxDepA2Selected = true;
                    depSeatsSelected++;
                    fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
                    Platform.runLater(() -> setSeatSelectedBG(fxDepA2));
                    mouseEvent.consume();
                }
            }
        }
    }

    public void fxDepB1Click(MouseEvent mouseEvent) {
        if(fxDepB1Res) {
            System.out.println("Sæti B1 er frátekið");
            mouseEvent.consume();
        } else {
            if(fxDepB1Selected) {
                fxDepB1Selected = false;
                depSeatsSelected--;
                fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
                Platform.runLater(() -> setSeatUnselectedBG(fxDepB1));
                mouseEvent.consume();
            } else {
                if(numberOfPassengers == depSeatsSelected) {
                    System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
                    mouseEvent.consume();
                } else {
                    fxDepB1Selected = true;
                    depSeatsSelected++;
                    fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
                    Platform.runLater(() -> setSeatSelectedBG(fxDepB1));
                    mouseEvent.consume();
                }
            }
        }
    }

    public void fxDepB2Click(MouseEvent mouseEvent) {
        if(fxDepB2Res) {
            System.out.println("Sæti B2 er frátekið");
            mouseEvent.consume();
        } else {
            if(fxDepB2Selected) {
                fxDepB2Selected = false;
                depSeatsSelected--;
                fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
                Platform.runLater(() -> setSeatUnselectedBG(fxDepB2));
                mouseEvent.consume();
            } else {
                if(numberOfPassengers == depSeatsSelected) {
                    System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
                    mouseEvent.consume();
                } else {
                    fxDepB2Selected = true;
                    depSeatsSelected++;
                    fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
                    Platform.runLater(() -> setSeatSelectedBG(fxDepB2));
                    mouseEvent.consume();
                }
            }
        }
    }

    public void fxDepC1Click(MouseEvent mouseEvent) {
        if(fxDepC1Res) {
            System.out.println("Sæti C1 er frátekið");
            mouseEvent.consume();
        } else {
            if(fxDepC1Selected) {
                fxDepC1Selected = false;
                depSeatsSelected--;
                fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
                Platform.runLater(() -> setSeatUnselectedBG(fxDepC1));
                mouseEvent.consume();
            } else {
                if(numberOfPassengers == depSeatsSelected) {
                    System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
                    mouseEvent.consume();
                } else {
                    fxDepC1Selected = true;
                    depSeatsSelected++;
                    fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
                    Platform.runLater(() -> setSeatSelectedBG(fxDepC1));
                    mouseEvent.consume();
                }
            }
        }
    }

    public void fxDepC2Click(MouseEvent mouseEvent) {
        if(fxDepC2Res) {
            System.out.println("Sæti C2 er frátekið");
            mouseEvent.consume();
        } else {
            if(fxDepC2Selected) {
                fxDepC2Selected = false;
                depSeatsSelected--;
                fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
                Platform.runLater(() -> setSeatUnselectedBG(fxDepC2));
                mouseEvent.consume();
            } else {
                if(numberOfPassengers == depSeatsSelected) {
                    System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
                    mouseEvent.consume();
                } else {
                    fxDepC2Selected = true;
                    depSeatsSelected++;
                    fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
                    Platform.runLater(() -> setSeatSelectedBG(fxDepC2));
                    mouseEvent.consume();
                }
            }
        }
    }
}
