package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.agendamenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;

/**
 * This class provides the user interface for viewing tasks between two dates.
 * It implements the Runnable interface, allowing it to be used in a separate thread.
 */
public class ViewTasksTwoDatesUI implements Runnable{
    /**
     * The controller that handles the task viewing process.
     */
    private AgendaController agendaController;

    /**
     * Constructs a new ViewTasksTwoDatesUI.
     * It initializes the controller.
     */
    public ViewTasksTwoDatesUI() {
        this.agendaController = new AgendaController();
    }

    /**
     * Starts the task viewing process.
     * It first requests the begin and end dates from the user.
     * It then retrieves the tasks between the entered dates from the agenda controller.
     * If there are no tasks between the entered dates, it prints a message indicating this.
     * If there are tasks between the entered dates, it prints the tasks.
     */
    public void run() {
        System.out.println("--View Tasks between two dates:--");
        Date dateBegin = Utils.readDateFromConsole("Enter the begin date (dd-mm-yyy):");
        Date dateEnd = Utils.readDateFromConsole("Enter the end date (dd-mm-yyy):");

        List<Entry> entries = agendaController.getEntriesBetweenDates(dateBegin, dateEnd);
        if(entries.isEmpty()){
            System.out.println("No entries found between the dates.");
            return;
        }
        for (Entry entry : entries) {
            System.out.println(entry.toString());
        }
        System.out.println("---------------\n");
    }
}
