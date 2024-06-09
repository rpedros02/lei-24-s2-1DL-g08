# US026- Assign one or more vehicles to an entry in the Agenda.

## 4. Tests 

**Test 1:** Check that it is not possible to list vehicles when the VehicleRepository is empty

    @Test
    void ensureVehicleRepositoryIsEmpty() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        assertTrue(vehicleRepository.getVehicles().isEmpty(), "Vehicle repository should be empty initially");
    }

    @Test
    void ensureVehicleRepositoryIsNotEmptyAfterAddingVehicle() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        Vehicle vehicle = new Vehicle("AA-00-00", "Brand", "Model", "Type", 1.0, 2.0, 1000, new Date(1, 1, 2001), new Date(1, 1, 2001), 12);
        vehicleRepository.addVehicle(vehicle);
        assertFalse(vehicleRepository.getVehicles().isEmpty(), "Vehicle repository should not be empty after adding a vehicle");
    }


## 5. Construction (Implementation)

### Class AssignVehicleAgendaController 

```java
public class AssignVehicleAgendaController {

    private Agenda agenda;

    private VehicleRepository vehicleRepository;

    public AssignVehicleAgendaController(){
        getAgenda();
        getVehicleRepository();
    }


    public Agenda getAgenda(){
        if(agenda == null){
            Repositories repositories = Repositories.getInstance();

            agenda = repositories.getAgenda();
        }
        return agenda;
    }

    public VehicleRepository getVehicleRepository(){
        if(vehicleRepository == null){
            Repositories repositories = Repositories.getInstance();

            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    public void assignVehicle(Entry agenda, Vehicle vehicle) {
        agenda.addVehicle(vehicle);
    }

    public List<Vehicle> getVehicles() {
        return vehicleRepository.getVehicles();
    }


}
```

### AssignVehicleAgendaUI

```java

public class AssignVehicleAgendaUI implements Runnable {
    private final AssignVehicleAgendaController controller;
    private final VehicleRepository vehicleRepository = Repositories.getInstance().getVehicleRepository();
    private Scanner input = new Scanner(System.in);
    private Entry selectedEntry;

    public AssignVehicleAgendaUI() {
        this.controller = new AssignVehicleAgendaController();
    }

    @Override
    public void run() {
        System.out.println("Welcome to the assignment of a vehicle to an entry");
        selectedEntry = SelectEntry();
        boolean continueAssigning = true;
        while (continueAssigning) {
            Vehicle selectedVehicle = SelectVehicles();
            controller.assignVehicle(selectedEntry, selectedVehicle);
            displayEntry();
            continueAssigning = continueAssigning();
        }
        System.out.println("Assignment complete!");
    }

    private Vehicle SelectVehicles() {
        List<Vehicle> vehicles = vehicleRepository.getVehicles();

        int numberOfVehicle = vehicles.size();
        int answer = -1;

        while (answer < 1 || answer > numberOfVehicle) {
            displayVehicles();
            System.out.println("Select a vehicle: ");
            answer = input.nextInt();
        }

        Vehicle vehicle = vehicles.get(answer - 1);
        return vehicle;
    }

    private void displayVehicles() {
        List<Vehicle> vehicles = vehicleRepository.getVehicles();
        int i = 1;
        for (Vehicle vehicle : vehicles) {
            System.out.println("  " + i + " - " + vehicle.toString());
            i++;
        }
    }

    private Entry SelectEntry() {
        List<Entry> entries = controller.getAgenda().getEntries();

        int numberOfEntry = entries.size();
        int answer = -1;

        while (answer < 1 || answer > numberOfEntry) {
            displayEntry();
            System.out.println("Select entry: ");
            answer = input.nextInt();
        }

        Entry entry = entries.get(answer - 1);
        return entry;
    }

    private void displayEntry() {
        List<Entry> entries = controller.getAgenda().getEntries();
        int i = 1;
        for (Entry entry : entries) {
            System.out.println("  " + i + " - " + entry.toString());
            i++;
        }
    }

    private boolean continueAssigning() {
        System.out.println("Do you want to assign another vehicle? (yes/no): ");
        String response = input.next().trim().toLowerCase();
        return response.equals("yes");
    }
}
```


## 6. Integration and Demo 

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a