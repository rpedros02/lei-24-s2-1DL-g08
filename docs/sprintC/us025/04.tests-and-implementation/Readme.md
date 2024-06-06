# US008 - List the vehicles in need off check-up.

## 4. Tests 

**Test 1:** Check that it is not possible to list the vehicles when VehicleRepository is empty. 

    @Test
    void ensureAgendaIsNotEmpty() {
        Agenda agenda = new Agenda();
        agenda.addEntry(new Entry("Title", "Description", DegreeOfUrgency.HIGH, new Date(), new Date(), EStatus.PENDING, new GreenSpace()));
        assertFalse(agenda.isEmpty());
    }
**Test 2:** Verify that the entry is canceled correctly.

    @Test
    void ensureEntryIsCancelled() {
        Agenda agenda = new Agenda();
        Entry entry = new Entry("Title", "Description", DegreeOfUrgency.HIGH, new Date(), new Date(), EStatus.PENDING, new GreenSpace());
        agenda.addEntry(entry);

        CancelAnEntryController controller = new CancelAnEntryController(agenda);
        controller.cancelEntry("Title");

    assertEquals(EStatus.CANCELED, entry.getStatus());
}

## 5. Construction (Implementation)

### Class CancelAnEntryController 

```java
public class CancelAnEntryController {
    private final Agenda agenda;

    public CancelAnEntryController(Agenda agenda) {
        this.agenda = agenda;
    }

    public String cancelEntry(String title) {
        Entry entry = agenda.getEntryByTitle(title);
        if (entry == null) {
            return "Entry not found.";
        }
        entry.setStatus(EStatus.CANCELED);
        return "Entry canceled successfully.";
    }
}
```

### Class CancelAnEntryUI

```java
public class CancelAnEntryUI implements Runnable {
    private final CancelAnEntryController cancelAnEntryController;

    public CancelAnEntryUI(Agenda agenda) {
        this.cancelAnEntryController = new CancelAnEntryController(agenda);
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the title of the entry to cancel: ");
        String title = scanner.nextLine();

        String result = cancelAnEntryController.cancelEntry(title);
        System.out.println(result);
    }
}
```
### Class EStatus

```java

public enum EStatus {
    PENDING(1),
    IN_PROGRESS(2),
    FINISHED(3),
    CANCELED(4);

    private final int status;

    EStatus(int status) {
        this.status = status;
    }

    public int status() {
        return status;
    }

    public static EStatus getStatus(int status) {
        for (EStatus s : EStatus.values()) {
            if (s.status == status) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return switch (this) {
            case PENDING -> "Pending";
            case IN_PROGRESS -> "In Progress";
            case FINISHED -> "Finished";
            case CANCELED -> "Canceled";
        };
    }
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
    options.add(new MenuItem("Cancel an entry in the Agenda", new CancelAnEntryUI(agenda)));
    //(...)
    
}
```
### Class Entry

```java
    public EStatus getStatus() {
    return status;
}
public void setStatus(EStatus status) {
    this.status = status;
}

```



## 6. Integration and Demo 

* For demo purposes some tasks are bootstrapped while system starts.


