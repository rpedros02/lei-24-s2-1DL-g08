package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Date;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

/**
 * The PostponeAnEntryController class is responsible for handling operations related to postponing an entry in an agenda.
 * It interacts with the Agenda and Entry objects to perform operations such as retrieving an entry by its title and postponing an entry.
 */
public class PostponeAnEntryController {


    public PostponeAnEntryController() {
    }

    /**
     * Postpones an entry.
     * It retrieves an Entry object by its title from the agenda. If the entry is found, it postpones the entry to the new date and returns true.
     * If the entry is not found, it returns false.
     *
     * @param newDate The new date of the entry.
     * @return A boolean indicating the success of the operation.
     */
    public boolean postponeEntry(Entry entry, Date newDate) {
        if (entry == null) {
            return false;
        }
        entry.postponeEntry(newDate);
        return true;
    }

}
