package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private List<Entry> entries;

    public ToDoList() {
        this.entries = new ArrayList<>();
    }

    public boolean addEntry(Entry entry) {
        return entries.add(entry);
    }

    public boolean removeEntry(Entry entry) {
        return entries.remove(entry);
    }

    public List<Entry> getEntries() {
        return new ArrayList<>(entries);
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
