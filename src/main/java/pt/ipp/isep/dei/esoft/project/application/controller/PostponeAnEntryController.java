package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Date;

public class PostponeAnEntryController {
    private final Agenda agenda;

    public PostponeAnEntryController(Agenda agenda) {
        this.agenda = agenda;
    }

    public String postponeEntry(String title, Date newDate) {
        Entry entry = agenda.getEntryByTitle(title);
        if (entry == null) {
            return "Entry not found.";
        }
        entry.postponeEntry(newDate);
        return "Entry postponed successfully.";
    }
}