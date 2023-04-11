package is.hi.flight_booking.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookingApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(BookingApplication.class.getResource("/fxml/bookingApplication_View.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
    stage.setTitle("Leita að flugi");
    stage.setScene(scene);
    stage.show();
    scene.setUserData(fxmlLoader.getController());
  }

  public static void main(String[] args) {
    launch();
  }
}
