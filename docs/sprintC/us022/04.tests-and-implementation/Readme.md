# US022 - List the vehicles in need off check-up.

## 4. Tests 

**Test 1:** Checks that a entry is correctly retrieved from the To Do List.

    @Test
    void getEntryByTitleShouldReturnEntryWhenTitleExists() {
        toDoList.addEntry(entry1);
        Entry result = toDoList.getEntryByTitle("Entry 1");
        assertEquals(entry1, result);
    }

**Test 2:** Checks that its not possible to enter repeated entries in the agenda.

    @Test
    void addEntryShouldReturnFalseWhenEntryExists() {
        agenda.addEntry(entry1);
        boolean result = agenda.addEntry(entry1);
        assertFalse(result);
    }


## 5. Construction (Implementation)

### Class ToDoList

```java
package pt.ipp.isep.dei.esoft.project.domain;

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
}

```

### Class Agenda

```java

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

```


## 6. Integration and Demo 

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a