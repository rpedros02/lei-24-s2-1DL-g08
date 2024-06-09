# US027 - List all green spaces managed by the GSM

## 4. Tests 

**Test 1:** Check that it is not possible to list green spaces when the GreenSpaceRepository is empty. 

    @Test
    void ensureGreenSpaceRepositoryIsEmpty() {
        GreenSpaceRepository greenSpaceRepository = new GreenSpaceRepository();
        assertTrue(greenSpaceRepository.getGreenSpaces().isEmpty(), "Green space repository should be empty initially");
    }

**Test 2:** Verify that green spaces are sorted by size in descending order.

     @Test
    void ensureGreenSpacesAreSortedBySizeDescending() {
        GreenSpaceRepository greenSpaceRepository = new GreenSpaceRepository();
        greenSpaceRepository.addGreenSpace(new GreenSpace("Small Park", 1.0));
        greenSpaceRepository.addGreenSpace(new GreenSpace("Medium Park", 5.0));
        greenSpaceRepository.addGreenSpace(new GreenSpace("Large Park", 10.0));

        SortStrategy sortStrategy = new SortBySizeDescending();
        ListGreenSpacesController controller = new ListGreenSpacesController(sortStrategy);

        List<GreenSpace> greenSpaces = controller.getAllGreenSpaces();
        assertEquals(3, greenSpaces.size());
        assertEquals("Large Park", greenSpaces.get(0).getName());
        assertEquals("Medium Park", greenSpaces.get(1).getName());
        assertEquals("Small Park", greenSpaces.get(2).getName());
    }



## 5. Construction (Implementation)

### Class ListGreenSpacesController 

```java
public class ListGreenSpacesController {
    private GreenSpaceRepository greenSpaceRepository;

    public ListGreenSpacesController() {
        this.greenSpaceRepository = Repositories.getInstance().getGreenSpaceRepository();
    }
    public List<GreenSpace> getAllGreenSpaces(){
        return this.greenSpaceRepository.getGreenSpaces();
    }

}
```

### ListGreenSpacesUI

```java

public class ListGreenSpacesUI implements Runnable {
    private final ListGreenSpacesController controller;

    public ListGreenSpacesUI() {
        this.controller = new ListGreenSpacesController();
    }

    @Override
    public void run() {
        System.out.println("Green Spaces:");
        List<GreenSpace> greenSpaces = controller.getAllGreenSpaces();
        displayGreenSpaces(greenSpaces);
        System.out.println("--------------------");
    }

    private void displayGreenSpaces(List<GreenSpace> greenSpaces) {
        if (greenSpaces.isEmpty()) {
            System.out.println("No green spaces found for this GSM.");
        } else {
            for (GreenSpace greenSpace : greenSpaces) {
                System.out.println(greenSpace);
            }
        }
    }
}
```


## 6. Integration and Demo 

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a