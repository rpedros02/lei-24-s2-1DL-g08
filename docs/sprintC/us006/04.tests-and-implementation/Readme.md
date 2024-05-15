# US006 - Create a Task 

## 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the Vehicle class with null values. 

	@Test(expected = IllegalArgumentException.class)
		public void ensureNullIsNotAllowed() {
		Vehicle instance = new Vehicle(null, null, null, null, null, null, null);
	}
	

**Test 2:** Check if a valid vehicle is created succesfully 

    @Test
	void ensureVehicleIsCreatedSuccessfully() {
        Vehicle vehicle = new Vehicle("AA-00-00", "Brand", "Model", "Type", 1.0, 2.0, 1000, new Date(1, 1, 2001), new Date(1, 1, 2001), 12);
        assertNotNull(vehicle);
    }



## 5. Construction (Implementation)

### Class VehicleController 

```java
public class VehicleController {
    private VehicleRepository vehicleRepository;

    public VehicleController() {
        getVehicleRepository();
    }

    public VehicleRepository getVehicleRepository() {
        if (vehicleRepository == null) {
            Repositories repositories = Repositories.getInstance();

            vehicleRepository = repositories.getVehicleRepository();
        }
        return vehicleRepository;
    }

    public Vehicle createVehicle(String plateId, String brand, String model,
                                 String type, double tare, double weight, int mileage,
                                 Date register_date, Date acquisition_date, int maintenance_frequency) {

        if (vehicleRepository.add(plateId, brand, model, type, tare, weight, mileage, register_date, acquisition_date, maintenance_frequency)) {
            return new Vehicle(plateId, brand, model, type, tare, weight, mileage, register_date, acquisition_date, maintenance_frequency);
        } else {
            return null;
        }
    }

}
```

### Class Vehicle

```java
public class Vehicle {
    String plateId;
    String brand;
    String model;
    String type;
    double tare;
    double weight;
    int mileage;
    Date register_date;
    Date acquisition_date;
    int maintenance_frequency;

    public Vehicle(String plateId, String brand, String model, String type, double tare, double weight, int mileage, Date register_date, Date acquisition_date, int maintenance_frequency) {
        if (!validateArgs()) {
            throw new IllegalArgumentException("Null arguments: \n");
        }
        this.plateId = plateId;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.tare = tare;
        this.weight = weight;
        this.mileage = mileage;
        this.register_date = register_date;
        this.acquisition_date = acquisition_date;
        this.maintenance_frequency = maintenance_frequency;
    }

    public boolean validateArgs() {
        return plateId != null && brand != null && model != null && type != null && tare != 0 && weight != 0 && mileage != 0 && register_date != null && acquisition_date != null && maintenance_frequency != 0;
    }

    public String getPlateId() {
        return plateId;
    }

    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getTare() {
        return tare;
    }

    public void setTare(double tare) {
        this.tare = tare;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public Date getRegisterDate() {
        return register_date;
    }

    public void setRegisterDate(Date register_date) {
        this.register_date = register_date;
    }

    public Date getAcquisitionDate() {
        return acquisition_date;
    }

    public void setAcquisitionDate(Date acquisition_date) {
        this.acquisition_date = acquisition_date;
    }

    public int getMaintenanceFrequency() {
        return maintenance_frequency;
    }

    public void setMaintenanceFrequency(int maintenance_frequency) {
        this.maintenance_frequency = maintenance_frequency;
    }

    public Vehicle clone() {
        return new Vehicle(this.plateId, this.brand, this.model, this.type, this.tare, this.weight, this.mileage, this.register_date.clone(), this.acquisition_date.clone(), this.maintenance_frequency);
    }
    @Override

    public String toString() {
        return "\n-----\nID: " + plateId + "\nBrand: " + brand + "\nModel: " + model + "\nType: " + type + "\nTare: " + tare + "\nWeight: " + weight + "\nMileage: " + mileage + "\nRegister Date: " + register_date.toString() + "\nAcquisition Date: " + acquisition_date.toString() + "\nMaintenance Frequency: " + maintenance_frequency + "\n-----\n";
    }

}
```