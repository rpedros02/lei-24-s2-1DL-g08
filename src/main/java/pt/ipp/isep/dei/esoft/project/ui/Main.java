package pt.ipp.isep.dei.esoft.project.ui;

import javafx.scene.control.Alert;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;

/**
 * This class serves as the entry point for the application.
 */
public class Main {

    /**
     * The main method of the application.
     * It creates a new MainMenuUI and runs it.
     * If an exception occurs, it displays an error alert with the exception's message.
     */
    public static void main() {
        try {
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }

    /**
     * Displays an error alert with the specified message.
     * @param message the message to display in the alert
     */
    public static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
