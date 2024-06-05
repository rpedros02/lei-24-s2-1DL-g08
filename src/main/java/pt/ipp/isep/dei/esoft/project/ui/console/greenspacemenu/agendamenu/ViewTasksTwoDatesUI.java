package pt.ipp.isep.dei.esoft.project.ui.console.greenspacemenu.agendamenu;

import pt.ipp.isep.dei.esoft.project.application.controller.AgendaController;
import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.List;

public class ViewTasksTwoDatesUI implements Runnable{
    private AgendaController agendaController;

    public ViewTasksTwoDatesUI() {
        this.agendaController = new AgendaController();
    }

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
