package is.hi.flight_booking.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BookingApplication extends Application {

  private static BookingApplication thisApplication;
  private Stage mainStage;
  private Scene mainScene;
  @Override
    public void start(Stage stage) throws IOException {
    mainStage = stage;
    FXMLLoader main = new FXMLLoader(getClass().getResource("/fxml/bookingApplication_View.fxml"));
    thisApplication = this;
    Parent rootMain = main.load();
    mySetScene(rootMain);

    stage.setTitle("Flugbókunarkerfi (3-F)");
    stage.setScene(mainScene);
    stage.show();
    thisApplication = this;
  }

  public static BookingApplication getApplicationInstance() { return thisApplication; }
  public void changeScene(String FXMLurl) throws IOException {
      thisApplication = this;
      FXMLLoader newLoader = new FXMLLoader(getClass().getResource(FXMLurl));
      Parent newRoot = newLoader.load();
      mySetScene(newRoot);
      this.mainStage.setScene(this.mainScene);
      thisApplication = this;
  }
  private void mySetScene(Parent newRoot) {
    mainScene = new Scene(newRoot, 1280, 720);
  }
  public static void main(String[] args) {
    launch();
  }
}
