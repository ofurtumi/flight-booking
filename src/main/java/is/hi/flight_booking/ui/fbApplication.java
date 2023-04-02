package is.hi.flight_booking.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class fbApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(fbApplication.class.getResource("fbMain-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Flight Booking");
        stage.setScene(scene);
        stage.show();
        scene.setUserData(fxmlLoader.getController());
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}
