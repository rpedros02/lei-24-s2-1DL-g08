# US006 - Create a Task 

## 4. Tests 

**Test 1:** Check that it is not possible to create an instance of the Task class with null values. 

	@Test(expected = IllegalArgumentException.class) // test should return true if verification is working
    public void ensureNullIsNotAllowed() {
        Integer skillId = null;
        String name = null;

        Skill skill = new Skill(skillId, name);
    }
	

**Test 2 & 3:** Check that if a skill special characters or digits. -- AC1

	@Test(expected = IllegalArgumentException.class)
    public void testSkillCreationWithInvalidName() { // test should return true if verification is working
        Integer skillId = 1;
        String invalidName = "Skill#1";


        Skill skill = new Skill(skillId, invalidName);


    }

    @Test
    public void testSkillCreationWithValidName() { // test should return true because its a valid name

        Integer skillId = 1;
        String ValidName = "Skill la";


        Skill skill = new Skill(skillId, ValidName);


    }


## 5. Construction (Implementation)

### Class RegisterSkillController

```java
public class RegisterSkillController {

    private SkillsRepository skillsRepository;


    public RegisterSkillController() {
        this.skillsRepository = getSkillsRepository();

    }

    private SkillsRepository getSkillsRepository() {
        if (Objects.isNull(skillsRepository)) {
            Repositories repositories = Repositories.getInstance();
            skillsRepository = repositories.getSkillsRepository();
        }
        return skillsRepository;
    }

    public void registerSkill(int skillId, String name) {
        skillsRepository.add(skillId, name);
    }

    public boolean skillExists(int skillId) {
        return skillsRepository.exists(skillId);
    }

    public boolean nameExists(String name) {
        return skillsRepository.exists(name);
    }



}
}
```


### Class Skill

```java
package pt.ipp.isep.dei.esoft.project.domain;

public class Skill {

    int skillId;
    String name;


    public Skill(Integer skillId, String name) {
        if (name == null || !isValidName(name)) {
            throw new IllegalArgumentException("Name contains special character and/or numbers:\n");
        }
        this.skillId = skillId;
        this.name = name;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return name;
    }

    public void setSkillName(String name) {
        this.name = name;
    }

    public boolean isValidName(String name) {
        return name.matches("[a-zA-Z ]+");
    }
}


```


### Class SkillsRepository

```java
public class SkillsRepository {

    private final List<Skill> skills;

    public SkillsRepository() {
        this.skills = new ArrayList<>();
    }

    public boolean add(int skillId, String name) {
        if(skills.isEmpty()){
            skills.add(new Skill(skillId, name)); // if its the first entry in the list
            return true;
        }
        for (Skill skill : skills) {
            if (skill.getSkillId() == skillId) {
                return false; //skill id already exists
            }
        }
        return skills.add(new Skill(skillId, name));
    }


    public boolean update(int skillId, String name) {
        for (Skill skill : skills) {
            if (skill.getSkillId() == skillId) {
                skill.setSkillName(name);
                return true;
            }
        }
        return false;
    }

    public boolean remove(int skillId) {
        for (Skill skill : skills) {
            if (skill.getSkillId() == skillId) {
                skills.remove(skill);
                return true;
            }
        }
        return false;
    }

    public boolean exists(int skillId) {
        for (Skill skill : skills) {
            if (skill.getSkillId() == skillId) {
                return true;
            }
        }
        return false;
    }

    public boolean exists(String name) {
        for (Skill skill : skills) {
            if (skill.getSkillName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}

```


## 6. Integration and Demo 

* A new option on the Menu was added (Register Skill).

* For demo purposes some Skills are bootstrapped while system starts.
