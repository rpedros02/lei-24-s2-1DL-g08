package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Enums.EStatus;

import java.util.ArrayList;
import java.util.List;

public class ToDoList {
    private final List<Entry> entries;

    public ToDoList() {
        this.entries = new ArrayList<>();
    }

    public boolean addEntry(Entry entry) {
        if(!hasEntry(entry)) {
            return entries.add(entry);
        }
        return false;
    }

    public boolean removeEntry(Entry entry) {
        if(!hasEntry(entry)) {
            return false;
        }
        return entries.remove(entry);
    }

    public boolean hasEntry(Entry entry) {
        return entries.contains(entry);
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

    public void setEntries(List<Entry> entries) {
        this.entries.clear();
        this.entries.addAll(entries);
    }

    public boolean updateStatus(Entry selectedEntry, EStatus eStatus) {
        if (selectedEntry == null) {
            return false;
        }
        selectedEntry.setStatus(eStatus);
        return true;
    }
}
