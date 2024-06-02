package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;



public class Team {
    private int minMembers;
    private int maxMembers;
    private List<Skill> teamSkills;
    private List<Collaborator> teamMembers;

    public Team(int minMembers, int maxMembers, List<Skill> teamSkills) {
        this.minMembers = minMembers;
        this.maxMembers = maxMembers;
        this.teamSkills = teamSkills;
        this.teamMembers = new ArrayList<>();
    }

    public String toString() {
        if(getNumberOfTeamMembers() == 0) {
            return "No team members";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Team Members:\n");
        for (Collaborator collaborator : getTeamMembers()) {
            sb.append("Name: ").append(collaborator.getName()).append("\n");
            sb.delete(sb.length() - 2, sb.length()); // Remove the last comma and space
            sb.append("\n\n");
        }
        sb.append("Number of Team Members: ").append(getNumberOfTeamMembers());
        return sb.toString();
    }

    public int getMinMembers() {
        return minMembers;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public List<Collaborator> getTeamMembers() {
        return new ArrayList<>(this.teamMembers);
    }

    public int getNumberOfTeamMembers() {
        return this.teamMembers.size();
    }

    public List<Skill> getTeamSkills() {
        return teamSkills;
    }

    public void addTeamMember(Collaborator collaborator) {
        teamMembers.add(collaborator);
    }
}