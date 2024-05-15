# US005- Generate Team Proposal

## 4. Tests 

**Test 1:** Ensure that the minimum size and max size are different than 0. 

	    @Test
        void ensureMinAndMaxDiffZero () {
            assertThrows(IllegalArgumentException.class, () -> new Team(0,0,null));
        }


## 5. Construction (Implementation)

### Class GenerateTeamController 

```java
public Team generateTeam(int minMembers, int maxMembers, String[] teamSkills) {

    List<Skill> skills = new ArrayList<>();
    for (String skill : teamSkills) {
        skills.add(new Skill(skill));
    }

    Team team = new Team(minMembers, maxMembers, skills);
    collaborators = new ArrayList<>(getCollaboratorRepository().getCollaboratorList());

    int skillIndex = 0;
    while(collaborators.size() >= minMembers) {
        if (team.getNumberOfTeamMembers() < maxMembers) {
            Optional<Collaborator> OptCollaborator = getCollaborator(teamSkills[skillIndex]);
            if (OptCollaborator.isPresent()) {
                collaborators.remove(OptCollaborator);
                team.addTeamMember(OptCollaborator.get());
            } else {
                Collaborator collaborator = getRandomCollaborator();
                collaborators.remove(collaborator);
                team.addTeamMember(collaborator);
            }
        }
    }

    return team;
}
```

## 6. Integration and Demo 

* For demo purposes some tasks are bootstrapped while system starts.


## 7. Observations

n/a