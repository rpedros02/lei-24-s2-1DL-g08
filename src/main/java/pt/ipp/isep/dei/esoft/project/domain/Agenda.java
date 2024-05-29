package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Entry> entries;

    public Agenda() {
        this.entries = new ArrayList<>();
    }

    public Agenda(List<Entry> entries){
        this.entries = entries;
    }

    public List<Entry> getEntries(){
        return this.entries;
    }

    public boolean hasEntry(Entry entry) {
        return this.entries.contains(entry);
    }

}
