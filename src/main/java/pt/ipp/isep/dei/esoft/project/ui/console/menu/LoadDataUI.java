package pt.ipp.isep.dei.esoft.project.ui.console.menu;

import pt.ipp.isep.dei.esoft.project.ui.Bootstrap;

/**
 * This class provides the user interface for loading data.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class LoadDataUI implements Runnable {

    /**
     * Constructs a new instance of LoadDataUI.
     */
    public LoadDataUI() {
    }

    /**
     * Starts the user interface for loading data.
     * It prints a message to the console, initializes the Bootstrap class, and runs it.
     */
    @Override
    public void run() {
        System.out.println("Load Data");
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.run();
        return;
    }
}
