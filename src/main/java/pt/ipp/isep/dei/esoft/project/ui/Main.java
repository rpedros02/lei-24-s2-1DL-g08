package pt.ipp.isep.dei.esoft.project.ui;

import javafx.scene.control.Alert;
import pt.ipp.isep.dei.esoft.project.ui.console.menu.MainMenuUI;

public class Main {

    public static void main() {
        try {
            MainMenuUI menu = new MainMenuUI();
            menu.run();
        } catch (Exception e) {
            showAlert(e.getMessage());
        }
    }
    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
