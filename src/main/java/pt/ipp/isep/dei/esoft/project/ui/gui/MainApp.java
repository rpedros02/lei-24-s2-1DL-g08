package pt.ipp.isep.dei.esoft.project.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

import java.util.Objects;

/**
 * This class is the main entry point for the application.
 * It extends the Application class from the JavaFX library.
 */
public class MainApp extends Application {

    /**
     * This method is called when the application is launched.
     * It sets up the main user interface and displays it.
     * @param primaryStage the primary stage for this application, onto which the application scene can be set
     * @throws Exception if an error occurs while loading the user interface
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main menu user interface from the MainMenuGUI.fxml file
        Parent root = new FXMLLoader(getClass().getResource("/MainMenuGUI.fxml")).load();

        // Set the title of the primary stage
        primaryStage.setTitle("Green Spaces Portal");

        // Set the scene of the primary stage
        primaryStage.setScene(new Scene(root, 600, 600));

        // Display the primary stage
        primaryStage.show();
    }

    /**
     * This is the main method of the application.
     * It creates a Bootstrap object, runs it, and then launches the application.
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // Create a Bootstrap object
        Bootstrap bootstrap = new Bootstrap();

        // Run the bootstrap
        bootstrap.run();

        // Launch the application
        launch(args);
    }
}