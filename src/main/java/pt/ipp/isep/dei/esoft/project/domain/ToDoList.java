package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a ToDoList in the system.
 * It has one field: entries, which is a list of Entry objects.
 */
public class ToDoList {
    /**
     * The entries of the ToDoList.
     */
    private final List<Entry> entries;

    /**
     * Constructs a ToDoList object.
     * It initializes the entries as an empty ArrayList.
     */
    public ToDoList() {
        this.entries = new ArrayList<>();
    }

    /**
     * Adds the specified Entry to the entries of this ToDoList.
     * If the Entry is already in the entries, it returns false.
     *
     * @param entry the Entry to add
     * @return true if the Entry was added successfully, false otherwise
     */
    public boolean addEntry(Entry entry) {
        if(!hasEntry(entry)) {
            return entries.add(entry);
        }
        return false;
    }

    /**
     * Removes the specified Entry from the entries of this ToDoList.
     * If the Entry is not in the entries, it returns false.
     *
     * @param entry the Entry to remove
     * @return true if the Entry was removed successfully, false otherwise
     */
    public boolean removeEntry(Entry entry) {
        if(!hasEntry(entry)) {
            return false;
        }
        return entries.remove(entry);
    }

    /**
     * Checks if the specified Entry is in the entries of this ToDoList.
     *
     * @param entry the Entry to check
     * @return true if the Entry is in the entries, false otherwise
     */
    public boolean hasEntry(Entry entry) {
        return entries.contains(entry);
    }

    /**
     * Returns a new ArrayList containing the entries of this ToDoList.
     *
     * @return a new ArrayList containing the entries of this ToDoList
     */
    public List<Entry> getEntries() {
        return new ArrayList<>(entries);
    }

    /**
     * Returns the Entry with the specified title from the entries of this ToDoList.
     * If no Entry has the specified title, it returns null.
     *
     * @param title the title to check
     * @return the Entry with the specified title
     */
    public Entry getEntryByTitle(String title) {
        for (Entry entry : entries) {
            if (entry.getTitle().equals(title)) {
                return entry;
            }
        }
        return null;
    }

    /**
     * Sets the entries of this ToDoList to the specified entries.
     *
     * @param entries the entries to set
     */
    public void setEntries(List<Entry> entries) {
        this.entries.clear();
        this.entries.addAll(entries);
    }
}