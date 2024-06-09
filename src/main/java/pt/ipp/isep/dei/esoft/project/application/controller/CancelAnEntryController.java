package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.ui.gui.UtilsGUI;

/**
 * The CancelAnEntryController class is responsible for handling operations related to canceling an entry in the agenda.
 * It interacts with the Agenda and Entry to perform operations such as retrieving an entry by its title and changing its status to CANCELED.
 */
public class CancelAnEntryController {
    /**
     * The Agenda instance.
     * This instance is used to interact with the agenda,
     * allowing the controller to perform operations related to the agenda.
     */
    private final ToDoList toDoList = Repositories.getInstance().getOrganizationRepository().getOrganizationByEmployeeEmail(UtilsGUI.getLoggedInUserEmail()).getTodoList();


    public CancelAnEntryController() {

    }

    /**
     * Cancels an entry in the agenda.
     * It retrieves an entry by its title from the agenda and changes its status to CANCELED.
     * If the entry is not found, it returns a message indicating that the entry was not found.
     * If the entry is found and successfully canceled, it returns a message indicating that the entry was canceled successfully.
     *
     * @param title The title of the entry.
     * @return A string indicating the result of the operation.
     */
    public boolean cancelEntry(String title) {
        Entry entry = toDoList.getEntryByTitle(title);
        if (entry == null) {
            return false;
        }
        entry.setStatus(EStatus.CANCELED);
        return true;
    }
}