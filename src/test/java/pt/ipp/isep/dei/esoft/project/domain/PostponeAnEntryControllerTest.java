package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.Before;
import org.junit.Test;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;
import pt.ipp.isep.dei.esoft.project.domain.Enums.DegreeOfUrgency;
import pt.ipp.isep.dei.esoft.project.application.controller.PostponeAnEntryController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class PostponeAnEntryControllerTest {
    private Agenda agenda;
    private PostponeAnEntryController controller;

    @Before
    public void setUp() {
        agenda = new Agenda();
        controller = new PostponeAnEntryController(agenda);

        // Adiciona uma entrada de exemplo na agenda
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateBegin = dateFormat.parse("2023-01-01");
            Date dateEnd = dateFormat.parse("2023-01-10");
            Entry entry = new Entry("Sample Task", "Description", DegreeOfUrgency.MEDIUM, dateBegin, dateEnd, EStatus.PLANNED, null);
            agenda.addEntry(entry);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPostponeEntrySuccess() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date newDate = dateFormat.parse("2023-02-01");
            String result = controller.postponeEntry("Sample Task", newDate);

            assertEquals("Entry postponed successfully.", result);

            Entry entry = agenda.getEntryByTitle("Sample Task");
            assertNotNull(entry);
            assertEquals(newDate, entry.getDateEnd());
        } catch (ParseException e) {
            fail("Invalid date format in test.");
        }
    }

    @Test
    public void testPostponeEntryNotFound() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date newDate = dateFormat.parse("2023-02-01");
            String result = controller.postponeEntry("Nonexistent Task", newDate);

            assertEquals("Entry not found.", result);
        } catch (ParseException e) {
            fail("Invalid date format in test.");
        }
    }
}
