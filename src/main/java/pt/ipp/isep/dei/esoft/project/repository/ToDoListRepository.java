package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a ToDoListRepository in the system.
 * It manages a ToDoList object and a list of Entry objects, and provides methods to manipulate and retrieve them.
 */
public class ToDoListRepository {
    /**
     * The ToDoList object.
     */
    private ToDoList toDoList;

    /**
     * The list of Entry objects.
     */
    private List<Entry> entries;

    /**
     * Constructs a ToDoListRepository object.
     * It initializes the toDoList as a new ToDoList object and the entries list as an empty ArrayList.
     */
    public ToDoListRepository() {
        this.toDoList = new ToDoList();
        this.entries = new ArrayList<>();
    }

    /**
     * Returns the list of Entry objects.
     *
     * @return the list of Entry objects
     */
    public List<Entry> getEntries() {
        return entries;
    }

    /**
     * Adds an Entry to the ToDoList and the entries list.
     * If the Entry is added successfully, it returns true.
     * Otherwise, it returns false.
     *
     * @param entry the Entry to add
     * @return true if the Entry was added successfully, false otherwise
     */
    public boolean addEntryToToDoList(Entry entry) {
        this.entries.add(entry);
        return this.toDoList.addEntry(entry);
    }

    /**
     * Removes an Entry from the ToDoList and the entries list.
     * If the Entry is removed successfully, it returns true.
     * Otherwise, it returns false.
     *
     * @param entry the Entry to remove
     * @return true if the Entry was removed successfully, false otherwise
     */
    public boolean removeEntryFromToDoList(Entry entry) {
        return toDoList.removeEntry(entry) && entries.remove(entry);
    }

    /**
     * Returns the ToDoList object.
     *
     * @return the ToDoList object
     */
    public ToDoList getToDoList() {
        return toDoList;
    }
}
