package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;
import pt.ipp.isep.dei.esoft.project.domain.Date;

public class CancelAnEntryController {
    private final Agenda agenda;

    public CancelAnEntryController(Agenda agenda) {
        this.agenda = agenda;
    }

    public String cancelEntry(String title) {
        Entry entry = agenda.getEntryByTitle(title);
        if (entry == null) {
            return "Entry not found.";
        }
        entry.setStatus(EStatus.CANCELED);
        return "Entry canceled successfully.";
    }
}