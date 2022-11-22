package no.ntnu.cardsnap.fx;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main JavaFX Application class.
 */
public final class App extends Application {
  /**
   * Start the main JavaFX application.
   *
   * @param stage The stage to play
   * @throws IOException If FXML could not be loaded
   */
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(
        this.getClass().getResource("LandingPage.fxml")
    );
    Parent parent = fxmlLoader.load();

    stage.setScene(new Scene(parent));
    stage.setResizable(false);
    stage.setTitle("CardSnap");

    stage.show();
  }

  /**
   * Start the application.
   *
   * @param args Program arguments, unused
   */
  public static void main(String[] args) {
    launch();
  }
}
