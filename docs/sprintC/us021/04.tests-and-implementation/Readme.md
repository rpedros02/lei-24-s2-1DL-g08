# US021 - Adding Entries for GSM.

## 4. Tests 

**Test 1:** Check that it is possible to add a task to the ToDoList in ToDoListRepository.
    
    @Test
    void testAddTaskToToDoList() {
        ToDoListRepository toDoListRepository = new ToDoListRepository();
        Address address = new Address("Street", "City", "Country");
        GreenSpace greenSpace = new GreenSpace("Park", GreenSpaceTypeRepository.GARDEN, 100.0, "GSM", address);
        Task task = new Task("Test Task", "This is a test task", DegreeOfUrgencyRepository.HIGH, 2.0, "In Progress", greenSpace);
        assertTrue(toDoListRepository.addTaskToToDoList(task));
    }

**Test 2:** Check that it is possible to remove a task from the ToDoList in ToDoListRepository.

    @Test
    void testRemoveTaskFromToDoList() {
        ToDoListRepository toDoListRepository = new ToDoListRepository();
        Address address = new Address("Street", "City", "Country");
        GreenSpace greenSpace = new GreenSpace("Park", GreenSpaceTypeRepository.GARDEN, 100.0, "GSM", address);
        Task task = new Task("Test Task", "This is a test task", DegreeOfUrgencyRepository.HIGH, 2.0, "In Progress", greenSpace);
        toDoListRepository.addTaskToToDoList(task);
        assertTrue(toDoListRepository.removeTaskFromToDoList(task));
    }

**Test 3:** Check that the list of tasks from ToDoListRepository is updated correctly after adding a task.

    @Test
    void testGetTasks() {
        ToDoListRepository toDoListRepository = new ToDoListRepository();
        Address address = new Address("Street", "City", "Country");
        GreenSpace greenSpace = new GreenSpace("Park", GreenSpaceTypeRepository.GARDEN, 100.0, "GSM", address);
        Task task = new Task("Test Task", "This is a test task", DegreeOfUrgencyRepository.HIGH, 2.0, "In Progress", greenSpace);
        toDoListRepository.addTaskToToDoList(task);
        assertEquals(1, toDoListRepository.getToDoListEntries().size());
    }

**Test 4:** Check that the ToDoList can be retrieved from ToDoListRepository.

    @Test
    void testGetToDoList() {
        ToDoListRepository toDoListRepository = new ToDoListRepository();
        assertNotNull(toDoListRepository.getToDoList());
    }   

**Test 5:** Check that the VehicleRepository is not empty after adding a vehicle.

    @Test
    void ensureVehicleRepositoryIsNotEmpty() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.add("AA-00-00", "Brand", "Model", "Type", 1.0, 2.0, 1000, new Date(1, 1, 2001), new Date(1, 1, 2001), 12);
        assertFalse(vehicleRepository.isEmpty());
    }


## 5. Construction (Implementation)

### Class ToDoListController 

```java
public class ToDoListController {

    private final OrganizationRepository organizationRepository;
    private final AuthenticationRepository authenticationRepository;

    public ToDoListController() {
        organizationRepository = Repositories.getInstance().getOrganizationRepository();
        authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
    }

    public Optional<Entry> createEntry(String title, String description, DegreeOfUrgency degreeOfUrgency, Date dateBegin, Date dateEnd, EStatus status, GreenSpace greenSpace, Team team, List<Vehicle> vehicles, Task task) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);
        Entry entry = new Entry(title, description, degreeOfUrgency, dateBegin, dateEnd, status, greenSpace, team, vehicles, task);
        Optional<Entry> newEntryToDoList = Optional.empty();


        if (organization.isPresent()) {
            newEntryToDoList = organization.get()
                    .addEntryToToDoList(entry);

        }
        return newEntryToDoList;
    }

    public boolean exists(String entryTitle) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);
        return organization.get().getEntriesFromToDoList().contains(getEntry(entryTitle));
    }

    private Entry getEntry(String entryTitle) {
        Collaborator collaborator = getEmployeeFromSession();
        Optional<Organization> organization = organizationRepository.getOrganizationByCollaborator(collaborator);
        return organization.get().getEntryFromToDoList(entryTitle);
    }

    public Entry getToDoListEntry(String entryTitle) {
        return getEntry(entryTitle);
    }

    public boolean addEntry(Entry entry) {
        return !createEntry(entry.getTitle(), entry.getDescription(), entry.getDegreeOfUrgency(), entry.getDateBegin(), entry.getDateEnd(), entry.getStatus(), entry.getGreenSpace(), entry.getTeam(), entry.getVehicles(), entry.getTask()).equals(Optional.empty());
    }

    private Collaborator getEmployeeFromSession() {
        Email email = authenticationRepository.getCurrentUserSession().getUserId();
        return new Collaborator(email);
    }

}
```

### AddEntryToDoListUI

```java

public class AddEntryToToDoListUI implements Runnable {
    private final GreenSpaceController greenSpaceController;
    private final ToDoListController toDoListController;
    private final AgendaController agendaController;

    public AddEntryToToDoListUI() {
        this.greenSpaceController = new GreenSpaceController();
        this.toDoListController = new ToDoListController();
        this.agendaController = new AgendaController();
    }

    /**
     * Runs the Add Entry to To-Do List UI.
     */
    @Override
    public void run() {
        System.out.println("-- Add Entry to To-Do List: --");
        GreenSpace greenSpace = getGreenSpace();
        String entryTitle = getEntryName();
        String entryDescription = getEntryDescription();
        List<String> types = DegreeOfUrgency.getDegreesOfUrgency();
        String degreeOfUrgencyString = (String) Utils.showAndSelectOne(types, "Select a degree of urgency:");
        Date dateBegin = Utils.readDateFromConsole("Enter the entry begin date (dd-mm-yyy):");
        Date dateEnd = Utils.readDateFromConsole("Enter the entry end date (dd-mm-yyy):");

        EStatus entryStatus = EStatus.PENDING; // Assuming the entry status is "Pending" when it's created

        DegreeOfUrgency degreeOfUrgency = DegreeOfUrgency.valueOf(degreeOfUrgencyString.toUpperCase());
        Entry entry = new Entry(entryTitle, entryDescription, degreeOfUrgency, dateBegin,dateEnd, entryStatus, greenSpace);

        boolean flag = Utils.getBooleanAnswer("Do you want to link a Task to this Entry?");

        if (flag) {
            AddTaskToEntryUI taskUI = new AddTaskToEntryUI(entry);
            taskUI.run();
        }

        if (toDoListController.addEntry(entry) && agendaController.addEntry(entry)){
            System.out.println("Entry successfully added to the To-Do List and registered in the Agenda.");
        } else {
            System.out.println("Failed to create Entry.");
        }
    }

    private GreenSpace getGreenSpace(){
        List<GreenSpace> greenSpaces = greenSpaceController.getAllGreenSpaces();
        for (int i = 0; i < greenSpaces.size(); i++) {
            System.out.println((i + 1) + ". " + greenSpaces.get(i).getName());
        }
        int greenSpaceIndex = Utils.readIntegerFromConsole("Select a green space from the list above:") - 1;
        return greenSpaces.get(greenSpaceIndex);
    }

    private String getEntryName(){
        return Utils.readLineFromConsole("Enter the entry title:");
    }

    private String getEntryDescription(){
        return Utils.readLineFromConsole("Enter the entry description:");
    }
}

```

### ToDoList

```java

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
}
```

### ToDoListRepository

```java

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

```

## 6. Integration and Demo 

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a