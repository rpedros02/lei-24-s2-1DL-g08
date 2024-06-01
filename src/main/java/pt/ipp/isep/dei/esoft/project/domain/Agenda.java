package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private final List<Entry> entries;

    public Agenda() {
        this.entries = new ArrayList<>();
    }

    public Agenda(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getEntries() {
        return this.entries;
    }

    public boolean hasEntry(Entry entry) {
        return this.entries.contains(entry);
    }

    public boolean addEntry(Entry entry) {
        if (!hasEntry(entry)) {
            return this.entries.add(entry);
        }
        return false;
    }

    public boolean removeEntry(Entry entry) {
        if (!hasEntry(entry)) {
            return false;
        }
        return this.entries.remove(entry);
    }

    public Entry getEntryByTitle(String title) {
        for (Entry entry : entries) {
            if (entry.getTitle().equals(title)) {
                return entry;
            }
        }
        return null;
    }

}
