package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {
    private ToDoList toDoList;
    private Entry entry1;
    private Entry entry2;

    @BeforeEach
    void setUp() {
        toDoList = new ToDoList();
        entry1 = new Entry("Entry 1");
        entry2 = new Entry("Entry 2");
    }

    @Test
    void addEntryShouldReturnTrueWhenEntryDoesNotExist() {
        boolean result = toDoList.addEntry(entry1);
        assertTrue(result);
    }

    @Test
    void addEntryShouldReturnFalseWhenEntryExists() {
        toDoList.addEntry(entry1);
        boolean result = toDoList.addEntry(entry1);
        assertFalse(result);
    }

    @Test
    void removeEntryShouldReturnTrueWhenEntryExists() {
        toDoList.addEntry(entry1);
        boolean result = toDoList.removeEntry(entry1);
        assertTrue(result);
    }

    @Test
    void removeEntryShouldReturnFalseWhenEntryDoesNotExist() {
        boolean result = toDoList.removeEntry(entry1);
        assertFalse(result);
    }

    @Test
    void hasEntryShouldReturnTrueWhenEntryExists() {
        toDoList.addEntry(entry1);
        boolean result = toDoList.hasEntry(entry1);
        assertTrue(result);
    }

    @Test
    void hasEntryShouldReturnFalseWhenEntryDoesNotExist() {
        boolean result = toDoList.hasEntry(entry1);
        assertFalse(result);
    }

    @Test
    void getEntryByTitleShouldReturnEntryWhenTitleExists() {
        toDoList.addEntry(entry1);
        Entry result = toDoList.getEntryByTitle("Entry 1");
        assertEquals(entry1, result);
    }

    @Test
    void getEntryByTitleShouldReturnNullWhenTitleDoesNotExist() {
        Entry result = toDoList.getEntryByTitle("Nonexistent Entry");
        assertNull(result);
    }

    @Test
    void setEntriesShouldReplaceExistingEntries() {
        List<Entry> entries = new ArrayList<>();
        entries.add(entry1);
        entries.add(entry2);
        toDoList.setEntries(entries);
        assertEquals(entries, toDoList.getEntries());
    }
}
