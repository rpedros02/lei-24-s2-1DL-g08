package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Entry;
import pt.ipp.isep.dei.esoft.project.domain.ToDoList;

import java.util.ArrayList;
import java.util.List;

public class ToDoListRepository {
    private ToDoList toDoList;
    private List<Entry> entries;

    public ToDoListRepository() {
        this.toDoList = new ToDoList();
        this.entries = new ArrayList<>();
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public boolean addEntryToToDoList(Entry entry) {
        this.entries.add(entry);
        return this.toDoList.addEntry(entry);
    }

    public boolean removeEntryFromToDoList(Entry entry) {
        return toDoList.removeEntry(entry) && entries.remove(entry);
    }

    public ToDoList getToDoList() {
        return toDoList;
    }
}
