package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private final List<Entry> entries;

    public Agenda() {
        this.entries = new ArrayList<>();
    }

    public Agenda(Agenda agenda) {
        this.entries = new ArrayList<>(agenda.entries);
    }

    public Agenda(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getEntries() {
        return this.entries;
    }

    public boolean checkEntryExists(Entry entry) {
        return !this.entries.contains(entry);
    }

    public boolean addEntry(Entry entry) {
        if (checkEntryExists(entry)) {
            return this.entries.add(entry);
        }
        return false;
    }

    public boolean removeEntry(Entry entry) {
        if (checkEntryExists(entry)) {
            return false;
        }
        return this.entries.remove(entry);
    }

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


    public void setEntries(List<Entry> entries) {
        this.entries.clear();
        this.entries.addAll(entries);
    }

    public boolean cancelEntry(String title) {
        Entry entryToRemove = getEntryByTitle(title);
        if (entryToRemove != null) {
            return removeEntry(entryToRemove);
        }
        return false;
    }

}
