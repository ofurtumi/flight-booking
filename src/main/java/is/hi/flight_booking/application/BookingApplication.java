package is.hi.flight_booking.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BookingApplication.class.getResource("BookingApplication_View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Dagbok");
        stage.setScene(scene);
        stage.show();
        scene.setUserData(fxmlLoader.getController());
    }

    public static void main(String[] args) {
        launch();
    }
}
