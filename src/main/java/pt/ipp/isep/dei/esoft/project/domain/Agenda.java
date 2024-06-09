package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * The Agenda class represents an agenda of entries.
 * It includes a list of entries and methods for manipulating these entries.
 */
public class Agenda {
    /**
     * The list of entries in the agenda.
     */
    private final List<Entry> entries;

    /**
     * Constructs an empty Agenda.
     */
    public Agenda() {
        this.entries = new ArrayList<>();
    }

    /**
     * Constructs an Agenda by copying the entries of another Agenda.
     *
     * @param agenda The Agenda to copy.
     */
    public Agenda(Agenda agenda) {
        this.entries = new ArrayList<>(agenda.entries);
    }

    /**
     * Constructs an Agenda with the given list of entries.
     *
     * @param entries The list of entries.
     */
    public Agenda(List<Entry> entries) {
        this.entries = entries;
    }

    /**
     * Retrieves the list of entries in the agenda.
     *
     * @return The list of entries.
     */
    public List<Entry> getEntries() {
        return this.entries;
    }

    /**
     * Checks if an entry exists in the agenda.
     *
     * @param entry The entry to check.
     * @return True if the entry does not exist, false otherwise.
     */
    public boolean checkEntryExists(Entry entry) {
        return !this.entries.contains(entry);
    }

    /**
     * Adds an entry to the agenda if it does not already exist.
     *
     * @param entry The entry to add.
     * @return True if the entry was added, false otherwise.
     */
    public boolean addEntry(Entry entry) {
        if (checkEntryExists(entry)) {
            return this.entries.add(entry);
        }
        return false;
    }

    /**
     * Removes an entry from the agenda if it exists.
     *
     * @param entry The entry to remove.
     * @return True if the entry was removed, false otherwise.
     */
    public boolean removeEntry(Entry entry) {
        if (checkEntryExists(entry)) {
            return false;
        }
        return this.entries.remove(entry);
    }

    /**
     * Retrieves an entry from the agenda by its title.
     *
     * @param title The title of the entry.
     * @return The entry with the given title, or null if no such entry exists.
     */
    public Entry getEntryByTitle(String title) {
        if (title == null) {
            return null;
        }
        for (Entry entry : entries) {
            if (title.equals(entry.getTitle())) {
                return entry;
            }
        }
        return null;
    }

    /**
     * Sets the entries in the agenda to the given list of entries.
     *
     * @param entries The new list of entries.
     */
    public void setEntries(List<Entry> entries) {
        this.entries.clear();
        this.entries.addAll(entries);
    }

    /**
     * Cancels an entry in the agenda by its title.
     *
     * @param title The title of the entry to cancel.
     * @return True if the entry was cancelled, false otherwise.
     */
    public boolean cancelEntry(String title) {
        Entry entryToRemove = getEntryByTitle(title);
        if (entryToRemove != null) {
            return removeEntry(entryToRemove);
        }
        return false;
    }

}