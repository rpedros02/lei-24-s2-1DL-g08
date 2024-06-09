package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Date;

/**
 * The PostponeAnEntryController class is responsible for handling operations related to postponing an entry in an agenda.
 * It interacts with the Agenda and Entry objects to perform operations such as retrieving an entry by its title and postponing an entry.
 */
public class PostponeAnEntryController {
    /**
     * The Agenda instance.
     * This instance is used to interact with the agenda,
     * allowing the controller to perform operations related to entries in the agenda.
     */
    private final Agenda agenda;

    /**
     * The constructor for the PostponeAnEntryController with an Agenda parameter.
     * It initializes the Agenda instance with the provided Agenda.
     *
     * @param agenda The Agenda object.
     */
    public PostponeAnEntryController(Agenda agenda) {
        this.agenda = agenda;
    }

    /**
     * Postpones an entry.
     * It retrieves an Entry object by its title from the agenda. If the entry is found, it postpones the entry to the new date and returns true.
     * If the entry is not found, it returns false.
     *
     * @param title The title of the entry.
     * @param newDate The new date of the entry.
     * @return A boolean indicating the success of the operation.
     */
    public boolean postponeEntry(String title, Date newDate) {
        Entry entry = agenda.getEntryByTitle(title);
        if (entry == null) {
            return false;
        }
        entry.postponeEntry(newDate);
        return true;
    }

}
