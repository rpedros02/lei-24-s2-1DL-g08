# US007 - Register a vehicle’s check-up

## 4. Tests 

**Test 1:** verify that the registerCheckUp method in the VehicleController class correctly registers a check-up for a vehicle in the VehicleRepository when given a valid vehicle ID.

	 @Test
    void testRegisterCheckUp() {
        VehicleRepository mockRepo = mock(VehicleRepository.class);
        VehicleController controller = new VehicleController(mockRepo);
        when(mockRepo.getVehicleByPlateId(anyString())).thenReturn(Optional.of(new Vehicle()));

        controller.registerCheckUp("ABC123", LocalDate.now(), 1000);

        verify(mockRepo, times(1)).addCheckUp(any(CheckUp.class));
    }


**Test 2:** verify that the vehicleExists method in the VehicleController class correctly identifies whether a vehicle exists in the VehicleRepository based on its plate ID.

    @Test
    void testVehicleExists() {
        VehicleRepository mockRepo = mock(VehicleRepository.class);
        VehicleController controller = new VehicleController(mockRepo);
        when(mockRepo.getVehicleByPlateId("ABC123")).thenReturn(Optional.of(new Vehicle()));

        assertTrue(controller.vehicleExists("ABC123"));
        assertFalse(controller.vehicleExists("XYZ789"));
    }

**Test 3:** verify that the getVehicleByPlateId method correctly retrieves a vehicle by its plate ID from the VehicleRepository.

	@Test
    void testGetVehicleByPlateId() {
        VehicleRepository repo = new VehicleRepository();
        Vehicle vehicle = new Vehicle();
        vehicle.setPlateId("ABC123");
        repo.addVehicle(vehicle);

        Optional<Vehicle> result = repo.getVehicleByPlateId("ABC123");

        assertTrue(result.isPresent());
        assertEquals("ABC123", result.get().getPlateId());
    }


_It is also recommended to organize this content by subsections._ 


## 5. Construction (Implementation)

### VehicleCheckupController 

```java
public class VehicleCheckupController {
    private final VehicleCheckupRepository checkUpRepository;
    private final VehicleRepository vehicleRepository;

    public VehicleCheckupController(VehicleCheckupRepository checkUpRepository, VehicleRepository vehicleRepository) {
        this.checkUpRepository = checkUpRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public boolean registerCheckUp(String plate, Date date, int lastCheckUpKm) {
        Optional<Vehicle> optVehicle = vehicleRepository.getVehicleByPlate(plate);
        if (optVehicle.isPresent()) {
            Vehicle vehicle = optVehicle.get();
            vehicle.updateLastCheckUpKm(lastCheckUpKm);
            boolean checkUpRegistered = checkUpRepository.registerCheckUp(plate, date, lastCheckUpKm).isPresent();
            if (checkUpRegistered) {
                vehicleRepository.updateVehicle(vehicle);
                return true;
            }
        }
        return false;
    }
}
```

### Class VehicleCheckup

```java
public class VehicleCheckup {
    private String plate;
    private Date date;
    private int lastCheckUpKm;

    public VehicleCheckup(String plate, Date date, int lastCheckUpKm) {
        this.plate = plate;
        this.date = date;
        this.lastCheckUpKm = lastCheckUpKm;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getLastCheckUpKm() {
        return lastCheckUpKm;
    }

    public void setLastCheckUpKm(int lastCheckUpKm) {
        this.lastCheckUpKm = lastCheckUpKm;
    }
}
```

### Class VehicleCheckupRepository

```java
public class VehicleCheckupRepository {
    private final List<VehicleCheckup> vehicleCheckups;

    public VehicleCheckupRepository() {
        vehicleCheckups = new ArrayList<>();
    }

    public Optional<VehicleCheckup> registerVehicleCheckup(String plate, Date date, int lastVehicleCheckupKm) {
        VehicleCheckup newVehicleCheckup = new VehicleCheckup(plate, date, lastVehicleCheckupKm);
        for (VehicleCheckup existingVehicleCheckup : vehicleCheckups) {
            if (existingVehicleCheckup.getPlate().equals(plate) && existingVehicleCheckup.getDate().isEqual(date)) {
                return Optional.empty();
            }
        }
        vehicleCheckups.add(newVehicleCheckup);
        return Optional.of(newVehicleCheckup);
    }
    public void addVehicleCheckup(VehicleCheckup vehicleCheckup) {
        vehicleCheckups.add(vehicleCheckup);
    }

}
```

## 6. Integration and Demo 

* A new method registerCheckUp() was added to the VehicleController class. This method receives the vehicleId, date and currentKms as parameters.
* The class CheckUp was created with the attributes vehicleId, date and currentKms.
* The method vehicleExists() was added to the VehicleController class. This method receives the vehicleId as a parameter and returns a boolean value.
* The method getVehicleByPlateId() was added to the CheckUpRepository class. This method receives the plateId as a parameter and returns an Optional<Vehicle> value.
* The method run() was added to the CheckUpUI class. This method is responsible for running the Check Up System and registering a checkup.
* The method showConfirmation() was added to the CheckUpUI class. This method is responsible for showing a confirmation message when a checkup is successfully registered.

## 7. Observations

n/a