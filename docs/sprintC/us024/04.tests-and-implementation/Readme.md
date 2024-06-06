# US024 - Postpone an Entry 

## 4. Tests 

**Test 1:** Verify that an entry is postponed correctly 

     @Test
    public void testPostponeEntry() {
        String title = "Test Entry";
        Date newDateEnd = new Date(1, 8, 2024);

        String result = controller.postponeEntry(title, newDateEnd);
        assertEquals("Entry postponed successfully.", result);

        Entry postponedEntry = agenda.getEntryByTitle(title);
        assertNotNull(postponedEntry);
        assertEquals(newDateEnd, postponedEntry.getDateEnd());
    }


## 5. Construction (Implementation)

### Class PostponeAnEntryController

```java
public class PostponeAnEntryController {
    private final Agenda agenda;

    public PostponeAnEntryController(Agenda agenda) {
        this.agenda = agenda;
    }

    public String postponeEntry(String title, Date newDate) {
        Entry entry = agenda.getEntryByTitle(title);
        if (entry == null) {
            return "Entry not found.";
        }
        entry.postponeEntry(newDate);
        return "Entry postponed successfully.";
    }
```

### Class PostPoneAnEntryUI

```java
public class PostPoneAnEntryUI implements Runnable {
    private final PostponeAnEntryController postponeAnEntryController;
    private final Agenda agenda;

    public PostPoneAnEntryUI(Agenda agenda) {
        this.agenda = agenda;
        this.postponeAnEntryController = new PostponeAnEntryController(agenda);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the entry to postpone: ");
        String title = scanner.nextLine();

        System.out.print("Enter the new date (dd-MM-YYYY): ");
        String dateStr = scanner.nextLine();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
        Date newDate = new Date(dateStr);
        String result = postponeAnEntryController.postponeEntry(title, newDate);
        System.out.println(result);
    }
}
```

### Class Entry

```java
public void postponeEntry(Date newDateEnd) {
    this.dateEnd = newDateEnd;
}
```
### Class GreenSpaceMenuUI

```java
 public GreenSpaceMenuUI(Agenda agenda) {
    this.agenda = agenda;
}

@Override
public void run() {
    //(...)
    options.add(new MenuItem("Postpone an entry in the Agenda", new PostPoneAnEntryUI(agenda)));
    //(...)
    
}
```


## 6. Integration and Demo 

* For demo purposes some tasks are bootstrapped while system starts.

