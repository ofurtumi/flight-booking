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

public class SelectSeatsBothWaysController implements Initializable {

  // ----------- FMXL breytur for Departure Flight -----------------
  // ---------------------------------------------------------------
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
  // ---------------------------------------------------------------

  // ----------- FMXL breytur for Return Flight --------------------
  // ---------------------------------------------------------------
  @FXML
  private Text fxRetHeaderFlightNum;
  @FXML
  private Text fxRetFromLocation, fxRetToLocation;
  @FXML
  private Text fxRetDate;
  @FXML
  private Text fxRetSeatsSelected, fxRetSeatsTotal;
  @FXML
  private VBox fxRetA1, fxRetA2, fxRetB1, fxRetB2, fxRetC1, fxRetC2;
  @FXML
  private Text fxRetMinorFlightNum;
  @FXML
  private Text fxRetTotalPrice;
  // ---------------------------------------------------------------

  @FXML
  private Text fxFinalPrice;
  @FXML
  private TextField fxUserID;
  @FXML
  private TextField fxUserName;

  // ----------- Controller breytur --------------------------------
  // ---------------------------------------------------------------
  private SelectFlightsBothWaysController storedSFBWC;
  private BookingController BC;
  private Flight departureFlight, returnFlight;

  private boolean fxDepA1Res, fxDepA2Res, fxDepB1Res,
      fxDepB2Res, fxDepC1Res, fxDepC2Res,
      fxRetA1Res, fxRetA2Res, fxRetB1Res,
      fxRetB2Res, fxRetC1Res, fxRetC2Res;

  private boolean fxDepA1Selected, fxDepA2Selected, fxDepB1Selected,
      fxDepB2Selected, fxDepC1Selected, fxDepC2Selected,
      fxRetA1Selected, fxRetA2Selected, fxRetB1Selected,
      fxRetB2Selected, fxRetC1Selected, fxRetC2Selected;

  private int depSeatsSelected;
  private int retSeatsSelected;
  private int totalSeatsSelected;
  // ---------------------------------------------------------------

  // ----------- Upplýsinga breytur --------------------------------
  // ---------------------------------------------------------------
  private String departureFlightId, returnFlightId;
  private String departureFlightFrom, departureFlightTo;
  private String returnFlightFrom, returnFlightTo;
  private LocalDate departureDate, returnDate;
  private List<Seat> departureFlightSeats, returnFlightSeats;
  private int numberOfPassengers;
  private int departureTotalPrice, returnTotalPrice;

  // ---------------------------------------------------------------
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // Controller breytur stilltar -----------------------------------
    BC = new BookingController("db/flightBooking.db");
    BookingApplication application = BookingApplication.getApplicationInstance();
    NumberFormat numberFormat = NumberFormat.getInstance(Locale.GERMANY);
    storedSFBWC = application.getStoredBothWaysController();
    departureFlight = storedSFBWC.getSelectedDepartureFlight().getStoredFlight();
    returnFlight = storedSFBWC.getSelectedReturnFlight().getStoredFlight();

    // Upplýsingabreytur stilltar -------------------------------------
    departureFlightId = departureFlight.getFlightId();
    returnFlightId = returnFlight.getFlightId();
    departureFlightFrom = departureFlight.getDepartureAddress();
    departureFlightTo = departureFlight.getArrivalAddress();
    returnFlightFrom = returnFlight.getDepartureAddress();
    returnFlightTo = returnFlight.getArrivalAddress();
    departureDate = departureFlight.getArrivalTime();
    returnDate = returnFlight.getArrivalTime();
    departureFlightSeats = departureFlight.getSeats();
    returnFlightSeats = returnFlight.getSeats();
    numberOfPassengers = application.getStoredBAppController().getFxNumPassengers();
    departureTotalPrice = departureFlight.getPrice() * numberOfPassengers;
    returnTotalPrice = returnFlight.getPrice() * numberOfPassengers;

    // FXML upplýsingabreytur stilltar --------------------------------
    // ###### Departure flugið ######
    fxDepHeaderFlightNum.setText(departureFlightId);
    fxDepFromLocation.setText(departureFlightFrom);
    fxDepToLocation.setText(departureFlightTo);
    fxDepDate.setText(departureDate.toString());
    fxDepSeatsSelected.setText("0");
    fxDepSeatsTotal.setText(Integer.toString(numberOfPassengers));
    fxDepMinorFlightNum.setText(departureFlightId);
    fxDepTotalPrice.setText(numberFormat.format(departureTotalPrice));

    // ###### Return flugið ######
    fxRetHeaderFlightNum.setText(returnFlightId);
    fxRetFromLocation.setText(returnFlightFrom);
    fxRetToLocation.setText(returnFlightTo);
    fxRetDate.setText(returnDate.toString());
    fxRetSeatsSelected.setText("0");
    fxRetSeatsTotal.setText(Integer.toString(numberOfPassengers));
    fxRetMinorFlightNum.setText(returnFlightId);
    fxRetTotalPrice.setText(numberFormat.format(returnTotalPrice));

    // ###### Annað FXML Stillt ######
    fxFinalPrice.setText(numberFormat.format(departureTotalPrice + returnTotalPrice));
    setStartSeats(departureFlightSeats, returnFlightSeats);
  }

  // Aðferðir til þess að lita sætinn.
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

  // Aðferð til þess að stilla upphafsástand sæta í fxml
  private void setStartSeats(List<Seat> depSeats, List<Seat> retSeats) {
    for (Seat seat : depSeats) {
      String seatId = seat.getId();
      boolean reserved = seat.isReserved();
      System.out.println("Departure seatId: " + seatId + "| is reserved: " + reserved);

      switch (seatId) {
        case "A1":
          if (reserved) {
            fxDepA1Res = true;
            Platform.runLater(() -> setSeatReservedBG(fxDepA1));
          } else {
            Platform.runLater(() -> setSeatUnselectedBG(fxDepA1));
          }
          break;
        case "A2":
          System.out.println("In case 'A2' for dep.");
          if (reserved) {
            fxDepA2Res = true;
            Platform.runLater(() -> setSeatReservedBG(fxDepA2));
          } else {
            Platform.runLater(() -> setSeatUnselectedBG(fxDepA2));
          }
          break;
        case "B1":
          System.out.println("In case 'B1' for dep.");
          if (reserved) {
            fxDepB1Res = true;
            Platform.runLater(() -> setSeatReservedBG(fxDepB1));
          } else {
            Platform.runLater(() -> setSeatUnselectedBG(fxDepB1));
          }
          break;
        case "B2":
          System.out.println("In case 'B2' for dep.");
          if (reserved) {
            fxDepB2Res = true;
            Platform.runLater(() -> setSeatReservedBG(fxDepB2));
          } else {
            Platform.runLater(() -> setSeatUnselectedBG(fxDepB2));
          }
          break;
        case "C1":
          System.out.println("In case 'C1' for dep.");
          if (reserved) {
            fxDepC1Res = true;
            Platform.runLater(() -> setSeatReservedBG(fxDepC1));
          } else {
            Platform.runLater(() -> setSeatUnselectedBG(fxDepC1));
          }
          break;
        case "C2":
          System.out.println("In case 'C2' for dep.");
          if (reserved) {
            fxDepC2Res = true;
            Platform.runLater(() -> setSeatReservedBG(fxDepC2));
          } else {
            Platform.runLater(() -> setSeatUnselectedBG(fxDepC2));
          }
          break;
        default:
          System.out.println("Eitthvað fór úrskeiðis í sætalitun Dep");
      }
    }
    for (Seat seat : retSeats) {
      String seatId = seat.getId();
      boolean reserved = seat.isReserved();
      System.out.println("Return seatId: " + seatId + "| is reserved: " + reserved);

      switch (seatId) {
        case "A1":
          if (reserved) {
            fxRetA1Res = true;
            Platform.runLater(() -> setSeatReservedBG(fxRetA1));
          } else {
            Platform.runLater(() -> setSeatUnselectedBG(fxRetA1));
          }
          break;
        case "A2":
          System.out.println("In case 'A2' for Ret.");
          if (reserved) {
            fxRetA2Res = true;
            Platform.runLater(() -> setSeatReservedBG(fxRetA2));
          } else {
            Platform.runLater(() -> setSeatUnselectedBG(fxRetA2));
          }
          break;
        case "B1":
          System.out.println("In case 'B1' for Ret.");
          if (reserved) {
            fxRetB1Res = true;
            Platform.runLater(() -> setSeatReservedBG(fxRetB1));
          } else {
            Platform.runLater(() -> setSeatUnselectedBG(fxRetB1));
          }
          break;
        case "B2":
          System.out.println("In case 'B2' for Ret.");
          if (reserved) {
            fxRetB2Res = true;
            Platform.runLater(() -> setSeatReservedBG(fxRetB2));
          } else {
            Platform.runLater(() -> setSeatUnselectedBG(fxRetB2));
          }
          break;
        case "C1":
          System.out.println("In case 'C1' for Ret.");
          if (reserved) {
            fxRetC1Res = true;
            Platform.runLater(() -> setSeatReservedBG(fxRetC1));
          } else {
            Platform.runLater(() -> setSeatUnselectedBG(fxRetC1));
          }
          break;
        case "C2":
          System.out.println("In case 'C2' for Ret.");
          if (reserved) {
            fxRetC2Res = true;
            Platform.runLater(() -> setSeatReservedBG(fxRetC2));
          } else {
            Platform.runLater(() -> setSeatUnselectedBG(fxRetC2));
          }
          break;
        default:
          System.out.println("Eitthvað fór úrskeiðis í sætalitun Ret");
      }
    }
  }

  public void fxBackHandler(ActionEvent actionEvent) throws IOException {
    BookingApplication application = BookingApplication.getApplicationInstance();
    application.setStoredBothWaysController(null);
    application.changeScene("/flight_fxml/selectBothWays_view.fxml");
    actionEvent.consume();
  }

  // Hér fer bókunarferlið fram
  public void fxMakeBookingHandler(ActionEvent actionEvent) throws IOException {
    if (totalSeatsSelected != numberOfPassengers * 2) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Óklárað sætaval");
      alert.setHeaderText("Ekki búið að velja öll sæti!");
      alert.setContentText("Vertu viss um að þú hafir valið öll umbeðin sæti í báðum flugum.");
      alert.showAndWait();
      actionEvent.consume();
    } else if (fxUserName.getText() == null || fxUserName.getText().length() == 0
        || fxUserName.getText().length() > 30) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Nafn í ólagi");
      alert.setHeaderText("Nafn ekki í gildu formi!");
      alert.setContentText("Nafn verður að vera a.m.k. 1 bókstafur og í mesta lagi 30 stafir");
      alert.showAndWait();
      actionEvent.consume();
    } else if (fxUserID.getText() == null || fxUserID.getText().length() != 10) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Kennitala í ólagi");
      alert.setHeaderText("Kennitala ekki í gildu formi!");
      alert.setContentText("Sláðu inn nákvæmlega 10 stafa kennitölu án bila eða merkja.");
      alert.showAndWait();
      actionEvent.consume();
    } else {
      User newUser = new User(fxUserID.getText(), fxUserName.getText());
      List<Seat> selectedDepSeats = new ArrayList<>();
      List<Seat> selectedRetSeats = new ArrayList<>();

      if (fxDepA1Selected) {
        for (Seat seat : departureFlightSeats) {
          if ("A1".equals(seat.getId())) {
            selectedDepSeats.add(seat);
            break;
          }
        }
      }
      if (fxDepA2Selected) {
        for (Seat seat : departureFlightSeats) {
          if ("A2".equals(seat.getId())) {
            selectedDepSeats.add(seat);
            break;
          }
        }
      }
      if (fxDepB1Selected) {
        for (Seat seat : departureFlightSeats) {
          if ("B1".equals(seat.getId())) {
            selectedDepSeats.add(seat);
            break;
          }
        }
      }
      if (fxDepB2Selected) {
        for (Seat seat : departureFlightSeats) {
          if ("B2".equals(seat.getId())) {
            selectedDepSeats.add(seat);
            break;
          }
        }
      }
      if (fxDepC1Selected) {
        for (Seat seat : departureFlightSeats) {
          if ("C1".equals(seat.getId())) {
            selectedDepSeats.add(seat);
            break;
          }
        }
      }
      if (fxDepC2Selected) {
        for (Seat seat : departureFlightSeats) {
          if ("C2".equals(seat.getId())) {
            selectedDepSeats.add(seat);
            break;
          }
        }
      }

      if (fxRetA1Selected) {
        for (Seat seat : returnFlightSeats) {
          if ("A1".equals(seat.getId())) {
            selectedRetSeats.add(seat);
            break;
          }
        }
      }
      if (fxRetA2Selected) {
        for (Seat seat : returnFlightSeats) {
          if ("A2".equals(seat.getId())) {
            selectedRetSeats.add(seat);
            break;
          }
        }
      }
      if (fxRetB1Selected) {
        for (Seat seat : returnFlightSeats) {
          if ("B1".equals(seat.getId())) {
            selectedRetSeats.add(seat);
            break;
          }
        }
      }
      if (fxRetB2Selected) {
        for (Seat seat : returnFlightSeats) {
          if ("B2".equals(seat.getId())) {
            selectedRetSeats.add(seat);
            break;
          }
        }
      }
      if (fxRetC1Selected) {
        for (Seat seat : returnFlightSeats) {
          if ("C1".equals(seat.getId())) {
            selectedRetSeats.add(seat);
            break;
          }
        }
      }
      if (fxRetC2Selected) {
        for (Seat seat : returnFlightSeats) {
          if ("C2".equals(seat.getId())) {
            selectedRetSeats.add(seat);
            break;
          }
        }
      }

      Booking depBooking = BC.createBooking(departureFlight, newUser, selectedDepSeats);
      Booking retBooking = BC.createBooking(returnFlight, newUser, selectedRetSeats);

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("Bókun staðfest");
      alert.setHeaderText("Bókun tókst!");
      alert.setContentText("Bókunarnúmer:\nBrottför: " + depBooking.getBookingID()
          + "\nHeimkoma: " + retBooking.getBookingID());
      alert.showAndWait();

      BookingApplication application = BookingApplication.getApplicationInstance();
      application.setStoredBAppController(null);
      application.setStoredBothWaysController(null);
      application.setUseStoredFalse();
      application.changeScene("/flight_fxml/bookingApplication_view.fxml");
      actionEvent.consume();

    }
  }

  // ------===== Departure Flight Seat Mouse Events ======-----------
  // ----------------------------------------------------------------
  public void fxDepA1Click(MouseEvent mouseEvent) {
    if (fxDepA1Res) {
      System.out.println("Sæti A1 er frátekið");
      mouseEvent.consume();
    } else {
      if (fxDepA1Selected) {
        fxDepA1Selected = false;
        depSeatsSelected--;
        totalSeatsSelected--;
        fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
        Platform.runLater(() -> setSeatUnselectedBG(fxDepA1));
        mouseEvent.consume();
      } else {
        if (numberOfPassengers == depSeatsSelected) {
          System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
          mouseEvent.consume();
        } else {
          fxDepA1Selected = true;
          depSeatsSelected++;
          totalSeatsSelected++;
          fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
          Platform.runLater(() -> setSeatSelectedBG(fxDepA1));
          mouseEvent.consume();
        }
      }
    }
  }

  public void fxDepA2Click(MouseEvent mouseEvent) {
    if (fxDepA2Res) {
      System.out.println("Sæti A2 er frátekið");
      mouseEvent.consume();
    } else {
      if (fxDepA2Selected) {
        fxDepA2Selected = false;
        depSeatsSelected--;
        totalSeatsSelected--;
        fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
        Platform.runLater(() -> setSeatUnselectedBG(fxDepA2));
        mouseEvent.consume();
      } else {
        if (numberOfPassengers == depSeatsSelected) {
          System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
          mouseEvent.consume();
        } else {
          fxDepA2Selected = true;
          depSeatsSelected++;
          totalSeatsSelected++;
          fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
          Platform.runLater(() -> setSeatSelectedBG(fxDepA2));
          mouseEvent.consume();
        }
      }
    }
  }

  public void fxDepB1Click(MouseEvent mouseEvent) {
    if (fxDepB1Res) {
      System.out.println("Sæti B1 er frátekið");
      mouseEvent.consume();
    } else {
      if (fxDepB1Selected) {
        fxDepB1Selected = false;
        depSeatsSelected--;
        totalSeatsSelected--;
        fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
        Platform.runLater(() -> setSeatUnselectedBG(fxDepB1));
        mouseEvent.consume();
      } else {
        if (numberOfPassengers == depSeatsSelected) {
          System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
          mouseEvent.consume();
        } else {
          fxDepB1Selected = true;
          depSeatsSelected++;
          totalSeatsSelected++;
          fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
          Platform.runLater(() -> setSeatSelectedBG(fxDepB1));
          mouseEvent.consume();
        }
      }
    }
  }

  public void fxDepB2Click(MouseEvent mouseEvent) {
    if (fxDepB2Res) {
      System.out.println("Sæti B2 er frátekið");
      mouseEvent.consume();
    } else {
      if (fxDepB2Selected) {
        fxDepB2Selected = false;
        depSeatsSelected--;
        totalSeatsSelected--;
        fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
        Platform.runLater(() -> setSeatUnselectedBG(fxDepB2));
        mouseEvent.consume();
      } else {
        if (numberOfPassengers == depSeatsSelected) {
          System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
          mouseEvent.consume();
        } else {
          fxDepB2Selected = true;
          depSeatsSelected++;
          totalSeatsSelected++;
          fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
          Platform.runLater(() -> setSeatSelectedBG(fxDepB2));
          mouseEvent.consume();
        }
      }
    }
  }

  public void fxDepC1Click(MouseEvent mouseEvent) {
    if (fxDepC1Res) {
      System.out.println("Sæti C1 er frátekið");
      mouseEvent.consume();
    } else {
      if (fxDepC1Selected) {
        fxDepC1Selected = false;
        depSeatsSelected--;
        totalSeatsSelected--;
        fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
        Platform.runLater(() -> setSeatUnselectedBG(fxDepC1));
        mouseEvent.consume();
      } else {
        if (numberOfPassengers == depSeatsSelected) {
          System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
          mouseEvent.consume();
        } else {
          fxDepC1Selected = true;
          depSeatsSelected++;
          totalSeatsSelected++;
          fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
          Platform.runLater(() -> setSeatSelectedBG(fxDepC1));
          mouseEvent.consume();
        }
      }
    }
  }

  public void fxDepC2Click(MouseEvent mouseEvent) {
    if (fxDepC2Res) {
      System.out.println("Sæti C2 er frátekið");
      mouseEvent.consume();
    } else {
      if (fxDepC2Selected) {
        fxDepC2Selected = false;
        depSeatsSelected--;
        totalSeatsSelected--;
        fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
        Platform.runLater(() -> setSeatUnselectedBG(fxDepC2));
        mouseEvent.consume();
      } else {
        if (numberOfPassengers == depSeatsSelected) {
          System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
          mouseEvent.consume();
        } else {
          fxDepC2Selected = true;
          depSeatsSelected++;
          totalSeatsSelected++;
          fxDepSeatsSelected.setText(Integer.toString(depSeatsSelected));
          Platform.runLater(() -> setSeatSelectedBG(fxDepC2));
          mouseEvent.consume();
        }
      }
    }
  }
  // ----------------------------------------------------------------

  // -------====== Return Flight Seats Mouse Events =======----------
  // ----------------------------------------------------------------

  public void fxRetA1Click(MouseEvent mouseEvent) {
    if (fxRetA1Res) {
      System.out.println("Sæti A1 er frátekið");
      mouseEvent.consume();
    } else {
      if (fxRetA1Selected) {
        fxRetA1Selected = false;
        retSeatsSelected--;
        totalSeatsSelected--;
        fxRetSeatsSelected.setText(Integer.toString(retSeatsSelected));
        Platform.runLater(() -> setSeatUnselectedBG(fxRetA1));
        mouseEvent.consume();
      } else {
        if (numberOfPassengers == retSeatsSelected) {
          System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
          mouseEvent.consume();
        } else {
          fxRetA1Selected = true;
          retSeatsSelected++;
          totalSeatsSelected++;
          fxRetSeatsSelected.setText(Integer.toString(retSeatsSelected));
          Platform.runLater(() -> setSeatSelectedBG(fxRetA1));
          mouseEvent.consume();
        }
      }
    }
  }

  public void fxRetA2Click(MouseEvent mouseEvent) {
    if (fxRetA2Res) {
      System.out.println("Sæti A2 er frátekið");
      mouseEvent.consume();
    } else {
      if (fxRetA2Selected) {
        fxRetA2Selected = false;
        retSeatsSelected--;
        totalSeatsSelected--;
        fxRetSeatsSelected.setText(Integer.toString(retSeatsSelected));
        Platform.runLater(() -> setSeatUnselectedBG(fxRetA2));
        mouseEvent.consume();
      } else {
        if (numberOfPassengers == retSeatsSelected) {
          System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
          mouseEvent.consume();
        } else {
          fxRetA2Selected = true;
          retSeatsSelected++;
          totalSeatsSelected++;
          fxRetSeatsSelected.setText(Integer.toString(retSeatsSelected));
          Platform.runLater(() -> setSeatSelectedBG(fxRetA2));
          mouseEvent.consume();
        }
      }
    }
  }

  public void fxRetB1Click(MouseEvent mouseEvent) {
    if (fxRetB1Res) {
      System.out.println("Sæti B1 er frátekið");
      mouseEvent.consume();
    } else {
      if (fxRetB1Selected) {
        fxRetB1Selected = false;
        retSeatsSelected--;
        totalSeatsSelected--;
        fxRetSeatsSelected.setText(Integer.toString(retSeatsSelected));
        Platform.runLater(() -> setSeatUnselectedBG(fxRetB1));
        mouseEvent.consume();
      } else {
        if (numberOfPassengers == retSeatsSelected) {
          System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
          mouseEvent.consume();
        } else {
          fxRetB1Selected = true;
          retSeatsSelected++;
          totalSeatsSelected++;
          fxRetSeatsSelected.setText(Integer.toString(retSeatsSelected));
          Platform.runLater(() -> setSeatSelectedBG(fxRetB1));
          mouseEvent.consume();
        }
      }
    }
  }

  public void fxRetB2Click(MouseEvent mouseEvent) {
    if (fxRetB2Res) {
      System.out.println("Sæti B2 er frátekið");
      mouseEvent.consume();
    } else {
      if (fxRetB2Selected) {
        fxRetB2Selected = false;
        retSeatsSelected--;
        totalSeatsSelected--;
        fxRetSeatsSelected.setText(Integer.toString(retSeatsSelected));
        Platform.runLater(() -> setSeatUnselectedBG(fxRetB2));
        mouseEvent.consume();
      } else {
        if (numberOfPassengers == retSeatsSelected) {
          System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
          mouseEvent.consume();
        } else {
          fxRetB2Selected = true;
          retSeatsSelected++;
          totalSeatsSelected++;
          fxRetSeatsSelected.setText(Integer.toString(retSeatsSelected));
          Platform.runLater(() -> setSeatSelectedBG(fxRetB2));
          mouseEvent.consume();
        }
      }
    }
  }

  public void fxRetC1Click(MouseEvent mouseEvent) {
    if (fxRetC1Res) {
      System.out.println("Sæti C1 er frátekið");
      mouseEvent.consume();
    } else {
      if (fxRetC1Selected) {
        fxRetC1Selected = false;
        retSeatsSelected--;
        totalSeatsSelected--;
        fxRetSeatsSelected.setText(Integer.toString(retSeatsSelected));
        Platform.runLater(() -> setSeatUnselectedBG(fxRetC1));
        mouseEvent.consume();
      } else {
        if (numberOfPassengers == retSeatsSelected) {
          System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
          mouseEvent.consume();
        } else {
          fxRetC1Selected = true;
          retSeatsSelected++;
          totalSeatsSelected++;
          fxRetSeatsSelected.setText(Integer.toString(retSeatsSelected));
          Platform.runLater(() -> setSeatSelectedBG(fxRetC1));
          mouseEvent.consume();
        }
      }
    }
  }

  public void fxRetC2Click(MouseEvent mouseEvent) {
    if (fxRetC2Res) {
      System.out.println("Sæti C2 er frátekið");
      mouseEvent.consume();
    } else {
      if (fxRetC2Selected) {
        fxRetC2Selected = false;
        retSeatsSelected--;
        totalSeatsSelected--;
        fxRetSeatsSelected.setText(Integer.toString(retSeatsSelected));
        Platform.runLater(() -> setSeatUnselectedBG(fxRetC2));
        mouseEvent.consume();
      } else {
        if (numberOfPassengers == retSeatsSelected) {
          System.out.println("Sæti fyrir alla farþega hafa þegar verið valin fyrir brottfararflug.");
          mouseEvent.consume();
        } else {
          fxRetC2Selected = true;
          retSeatsSelected++;
          totalSeatsSelected++;
          fxRetSeatsSelected.setText(Integer.toString(retSeatsSelected));
          Platform.runLater(() -> setSeatSelectedBG(fxRetC2));
          mouseEvent.consume();
        }
      }
    }
  }
  // ----------------------------------------------------------------
}
