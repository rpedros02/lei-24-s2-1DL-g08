package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Agenda;
import pt.ipp.isep.dei.esoft.project.domain.Entry;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AgendaTest {
    private Agenda agenda;
    private Entry entry1;
    private Entry entry2;

    @BeforeEach
    void setUp() {
        agenda = new Agenda();
        entry1 = new Entry("Entry 1");
        entry2 = new Entry("Entry 2");
    }

    @Test
    void addEntryShouldReturnTrueWhenEntryDoesNotExist() {
        boolean result = agenda.addEntry(entry1);
        assertTrue(result);
    }

    @Test
    void addEntryShouldReturnFalseWhenEntryExists() {
        agenda.addEntry(entry1);
        boolean result = agenda.addEntry(entry1);
        assertFalse(result);
    }

    @Test
    void removeEntryShouldReturnTrueWhenEntryExists() {
        agenda.addEntry(entry1);
        boolean result = agenda.removeEntry(entry1);
        assertTrue(result);
    }

    @Test
    void removeEntryShouldReturnFalseWhenEntryDoesNotExist() {
        boolean result = agenda.removeEntry(entry1);
        assertFalse(result);
    }

    @Test
    void getEntryByTitleShouldReturnEntryWhenTitleExists() {
        agenda.addEntry(entry1);
        Entry result = agenda.getEntryByTitle("Entry 1");
        assertEquals(entry1, result);
    }

    @Test
    void getEntryByTitleShouldReturnNullWhenTitleDoesNotExist() {
        Entry result = agenda.getEntryByTitle("Nonexistent Entry");
        assertNull(result);
    }

    @Test
    void cancelEntryShouldReturnTrueWhenEntryExists() {
        agenda.addEntry(entry1);
        boolean result = agenda.cancelEntry("Entry 1");
        assertTrue(result);
    }

    @Test
    void cancelEntryShouldReturnFalseWhenEntryDoesNotExist() {
        boolean result = agenda.cancelEntry("Nonexistent Entry");
        assertFalse(result);
    }

    @Test
    void setEntriesShouldReplaceExistingEntries() {
        List<Entry> entries = new ArrayList<>();
        entries.add(entry1);
        entries.add(entry2);
        agenda.setEntries(entries);
        assertEquals(entries, agenda.getEntries());
    }
}
