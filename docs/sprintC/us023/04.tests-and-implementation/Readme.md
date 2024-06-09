# US023 - Assign a Team to an entry in the Agenda

## 4. Tests 

**Test 1:** Check that it is not possible to update a null entry with a team.

    @Test
    void updateEntryShouldReturnFalseWhenEntryIsNull() {
        Agenda agenda = new Agenda();
        Team team = new Team("Team 1");
        assertFalse(agenda.updateEntry(null, team));
    }

**Test 2:** Check that it is not possible to update anentry with a null team.

    @Test
    void updateEntryShouldReturnFalseWhenTeamIsNull() {
        Agenda agenda = new Agenda();
        Entry entry = new Entry("Entry 1");
        assertFalse(agenda.updateEntry(entry, null));
    }



## 5. Construction (Implementation)

### Class Agenda

```java
public class Agenda {
    // already existing coded omitted for brevity

    public boolean updateEntry(Entry entry, Team team) {
        if (entry == null || team == null) {
            return false;
        }
        int index = entries.indexOf(entry);
        if (index != -1) {
            Entry existingEntry = entries.get(index);
            existingEntry.setTeam(team);
            return true;
        }
        return false;
    }
}
```

### Class TeamController

```java

package pt.ipp.isep.dei.esoft.project.controller;

import pt.ipp.isep.dei.esoft.project.domain.Team;
import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.util.List;

public class TeamController {
    private final TeamRepository teamRepository;

    public TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.getAllTeams();
    }

    public List<String> getTeamMembersEmails(Team team) {
        return team.getMembers();
    }
}
```

## 7. Observations

n/a