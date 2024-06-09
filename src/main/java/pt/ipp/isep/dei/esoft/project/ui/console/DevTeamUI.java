package pt.ipp.isep.dei.esoft.project.ui.console;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
/**
 * This class provides the user interface for displaying the development team.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class DevTeamUI implements Runnable {

    /**
     * Constructs a new instance of DevTeamUI.
     */
    public DevTeamUI() {

    }

    /**
     * Starts the user interface for displaying the development team.
     * It prints the names and email addresses of the development team members.
     */
    public void run() {
        System.out.println("\n");
        System.out.println("--- DEVELOPMENT TEAM -------------------");
        System.out.println("  Rui Silva - 1231501@isep.ipp.pt");
        System.out.println("  Catarina Lima - 1231131@isep.ipp.pt");
        System.out.println("  Rita Tavares - 1231261@isep.ipp.pt");
        System.out.println("  Marisa Adelino - 1231287@isep.ipp.pt");
        System.out.println("  Diogo Borges - 1211050@isep.ipp.pt");
        System.out.println("\n");
    }
}