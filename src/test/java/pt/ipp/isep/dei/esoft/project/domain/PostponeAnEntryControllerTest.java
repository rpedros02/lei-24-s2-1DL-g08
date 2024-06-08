package pt.ipp.isep.dei.esoft.project.domain;


import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.application.controller.PostponeAnEntryController;
import pt.ipp.isep.dei.esoft.project.domain.Enums.DegreeOfUrgency;
import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class PostponeAnEntryControllerTest {
    private Agenda agenda;
    private PostponeAnEntryController controller;

    public void setUp() {
        agenda = new Agenda(new ArrayList<>());
    controller = new PostponeAnEntryController(agenda);

    Entry entry = new Entry(
            "Test Entry", "Test Description", DegreeOfUrgency.LOW,
            new Date(1, 6, 2024), new Date(1, 7, 2024), EStatus.PLANNED,
            new GreenSpace("Test Green Space")
    );
        agenda.addEntry(entry);
}

    @Test
    public void testPostponeEntry() {
        String title = "Test Entry";
        Date newDateEnd = new Date(1, 8, 2024);

        boolean result = controller.postponeEntry(title, newDateEnd);
        assertTrue(result);

        Entry postponedEntry = agenda.getEntryByTitle(title);
        assertNotNull(postponedEntry);
        assertEquals(newDateEnd, postponedEntry.getDateEnd());
    }

    @Test
    public void testPostponeEntryNotFound() {
        String title = "Nonexistent Entry";
        Date newDateEnd = new Date(1, 8, 2024);

        boolean result = controller.postponeEntry(title, newDateEnd);
        assertFalse(result);
    }
}
