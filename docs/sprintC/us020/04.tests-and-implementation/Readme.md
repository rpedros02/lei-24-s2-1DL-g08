# US020 - Register a green space.

## 4. Tests 

**Test 1:** Check that it is not possible to create a null green space.

```java
@Test
public void ensureNullGreenSpaceIsNotAllowed() {
    GreenSpace greenSpace = null;
    assertThrows(IllegalArgumentException.class, () -> greenSpaceController.registerGreenSpace(greenSpace));
}
```

**Test 2:** Check that it is not possible to add a green space with the same name.

```java
@Test
public void ensureGreenSpaceWithSameNameCannotBeAdded() {
    GreenSpace greenSpace1 = new GreenSpace("Park", "Medium Sized", 100.0);
    GreenSpace greenSpace2 = new GreenSpace("Park", "Large Sized", 200.0);
    greenSpaceController.registerGreenSpace(greenSpace1);
    assertThrows(IllegalArgumentException.class, () -> greenSpaceController.registerGreenSpace(greenSpace2));
}
```


## 5. Construction (Implementation)

### Class GreenSpaceController 

```java
public class GreenSpaceController {
    private GreenSpaceRepository greenSpaceRepository;
    public GreenSpaceController() {
        if (greenSpaceRepository == null) {
            Repositories repositories = Repositories.getInstance();
            greenSpaceRepository = repositories.getGreenSpaceRepository();
        }
    }
    public boolean registerGreenSpace(String name, GreenSpaceTypeRepository type, double area) {
        GreenSpace greenSpace = new GreenSpace(name, type, area);
        return greenSpaceRepository.addGreenSpace(greenSpace);
    }
    public List<GreenSpace> getAllGreenSpaces() {
        return greenSpaceRepository.getGreenSpaces();
    }
    public GreenSpace getGreenSpaceByName(String name) {
        return greenSpaceRepository.getGreenSpaceByName(name);
    }
    public boolean removeGreenSpace(GreenSpace greenSpace) {
        return greenSpaceRepository.removeGreenSpace(greenSpace);
    }
    public boolean updateGreenSpace(GreenSpace greenSpace, String name, GreenSpaceTypeRepository type, double area) {
        return greenSpaceRepository.updateGreenSpace(greenSpace, name, type, area);
    }
    public void setGreenSpaceRepository(GreenSpaceRepository greenSpaceRepository) {
        this.greenSpaceRepository = greenSpaceRepository;
    }
    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }
    public void setGreenSpaceRepository() {
        Repositories repositories = Repositories.getInstance();
        greenSpaceRepository = repositories.getGreenSpaceRepository();
    }
}
```

### Class GreenSpace

```java
public class GreenSpace {
    private String name;
    private GreenSpaceTypeRepository type;
    private double area;

    public GreenSpace(String name, GreenSpaceTypeRepository type, double area) {
        this.name = name;
        this.type = type;
        this.area = area;
    }
    public String getName() {
        return name;
    }
    public GreenSpaceTypeRepository getType() {
        return type;
    }
    public double getArea() {
        return area;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setType(GreenSpaceTypeRepository type) {
        this.type = type;
    }
    public void setArea(double area) {
        this.area = area;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        GreenSpace greenSpace = (GreenSpace) o;
        return this.name.equals(greenSpace.name) && this.type.equals(greenSpace.type) && this.area == greenSpace.area;
    }

    public int hashCode() {
        return Objects.hash(name, type, area);
    }

    public String toString() {
        return "GreenSpace{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", area=" + area +
                "hectares}";
    }

    public boolean setData(String name, GreenSpaceTypeRepository type, double area) {
        if (name == null || type == null || area < 0) {
            return false;
        }
        this.name = name;
        this.type = type;
        this.area = area;
        return true;
    }

    public boolean isDataValid() {
        return this.name != null && this.type != null && this.area >= 0;
    }

    public boolean isNameValid(String name) {
        return name != null;
    }

    public boolean isTypeValid(String type) {
        return type != null;
    }

    public boolean isAreaValid(double area) {
        return area >= 0;
    }

}
```

### Class GreenSpaceRepository

```java
public class GreenSpaceRepository {
    private List<GreenSpace> greenSpaces = new ArrayList<>();
    private GreenSpaceTypeRepository greenSpaceTypeRepository;


    public boolean addGreenSpace(GreenSpace greenSpace) {
        for (GreenSpace existingGreenSpace : greenSpaces) {
            if (existingGreenSpace.getName().equals(greenSpace.getName())) {
                return false;
            }
        }
        greenSpaces.add(greenSpace);
        return true;
    }

    public List<GreenSpace> getGreenSpaces() {
        return greenSpaces;
    }

    public GreenSpace getGreenSpaceByName(String name) {
        for (GreenSpace greenSpace : greenSpaces) {
            if (greenSpace.getName().equals(name)) {
                return greenSpace;
            }
        }
        return null;
    }

    public boolean removeGreenSpace(GreenSpace greenSpace) {
        return greenSpaces.remove(greenSpace);
    }

    public boolean updateGreenSpace(GreenSpace greenSpace, String name, GreenSpaceTypeRepository type, double area) {
        if (greenSpaces.contains(greenSpace)) {
            greenSpace.setName(name);
            greenSpace.setType(type);
            greenSpace.setArea(area);
            return true;
        }
        return false;
    }

    public boolean isManagedByGSM(GreenSpace greenSpace) {
        return greenSpaces.contains(greenSpace);
    }

}
```

## 6. Integration and Demo 

* A new option on the Green Space menu options was added to register a green space.


## 7. Observations

n/a