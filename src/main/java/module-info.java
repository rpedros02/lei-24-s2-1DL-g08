module pt.ipp.isep.dei.esoft.project.ui.gui {
    requires AuthLib;
    requires java.logging;
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires org.apache.commons.lang3;

    opens pt.ipp.isep.dei.esoft.project.ui.gui to javafx.fxml;
    exports pt.ipp.isep.dei.esoft.project.ui.gui to javafx.graphics;

}