# US020 - Register a green space.

## 4. Tests 

**Test 1:** Check that it is not possible to list the vehicles when VehicleRepository is empty. 

    @Test
    void ensureVehicleRepositoryIsNotEmpty() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        vehicleRepository.add("AA-00-00", "Brand", "Model", "Type", 1.0, 2.0, 1000, new Date(1, 1, 2001), new Date(1, 1, 2001), 12);
        assertFalse(vehicleRepository.isEmpty());
    }


## 5. Construction (Implementation)

### Class CreateTaskController 

```java
public Vehicle createVehicle(String plateId, String brand, String model, String type, double tare, double weight, int mileage,
                             Date register_date, Date acquisition_date, int maintenance_frequency) {
    
    
	newVehicle = organization.createVehicle(plateId, brand, model, type, tare, weight,
                                            mileage, register_date, acquisition_date, maintenance_frequency);
    
	return newVehicle;
}
```

### Class Organization

```java

public Optional<Vehicle> createVehicle(String plateId, String brand, String model, String type, double tare, double weight, int mileage,
                                       Date register_date, Date acquisition_date, int maintenance_frequency) {

    Vehicle vehicle = new Vehicle(plateId, brand, model, type, tare, weight, mileage, register_date, acquisition_date, maintenance_frequency);

    addVehicle(vehicle);

    return vehicle;
}
```


## 6. Integration and Demo 

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a