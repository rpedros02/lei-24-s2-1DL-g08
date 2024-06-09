package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a Team in the system.
 * It has several fields including minMembers, maxMembers, teamSkills, teamMembers, and name.
 */
public class Team {
    /**
     * The minimum number of members in the Team.
     */
    private int minMembers;

    /**
     * The maximum number of members in the Team.
     */
    private int maxMembers;

    /**
     * The skills required for the Team.
     */
    private List<Skill> teamSkills;

    /**
     * The members of the Team.
     */
    private List<Collaborator> teamMembers;

    /**
     * The name of the Team.
     */
    private String name;

    /**
     * Constructs a Team object with the specified minMembers, maxMembers, and teamSkills.
     * It initializes the teamMembers as an empty ArrayList.
     *
     * @param minMembers the minimum number of members in the Team
     * @param maxMembers the maximum number of members in the Team
     * @param teamSkills the skills required for the Team
     */
    public Team(int minMembers, int maxMembers, List<Skill> teamSkills) {
        this.minMembers = minMembers;
        this.maxMembers = maxMembers;
        this.teamSkills = teamSkills;
        this.teamMembers = new ArrayList<>();
        this.name = name;
    }

    /**
     * Returns a string representation of this Team.
     * If the Team has no members, it returns "No team members".
     * Otherwise, it returns a string containing the names of the team members and the number of team members.
     *
     * @return a string representation of this Team
     */
    @Override
    public String toString() {
        if (getNumberOfTeamMembers() == 0) {
            return "No team members";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Team Members:\n");
        for (Collaborator collaborator : teamMembers) {
            sb.append("Name: ").append(collaborator.getName()).append("\n");
            sb.delete(sb.length() - 2, sb.length()); // Remove the last comma and space
            sb.append("\n\n");
        }
        sb.append("Number of Team Members: ").append(getNumberOfTeamMembers());
        return sb.toString();
    }

    /**
     * Returns the minimum number of members in this Team.
     *
     * @return the minimum number of members in this Team
     */
    public int getMinMembers() {
        return minMembers;
    }

    /**
     * Returns the maximum number of members in this Team.
     *
     * @return the maximum number of members in this Team
     */
    public int getMaxMembers() {
        return maxMembers;
    }

    /**
     * Returns a new ArrayList containing the members of this Team.
     *
     * @return a new ArrayList containing the members of this Team
     */
    public List<Collaborator> getTeamMembers() {
        return new ArrayList<>(this.teamMembers);
    }

    /**
     * Returns a list of names of the members of this Team.
     *
     * @return a list of names of the members of this Team
     */
    public List<String> getMembers() {
        List<String> memberNames = new ArrayList<>();
        for (Collaborator collaborator : teamMembers) {
            memberNames.add(collaborator.getName());
        }
        return memberNames;
    }

    /**
     * Returns the number of members in this Team.
     *
     * @return the number of members in this Team
     */
    public int getNumberOfTeamMembers() {
        return this.teamMembers.size();
    }

    /**
     * Returns the skills required for this Team.
     *
     * @return the skills required for this Team
     */
    public List<Skill> getTeamSkills() {
        return teamSkills;
    }

    /**
     * Adds the specified Collaborator to the members of this Team.
     *
     * @param collaborator the Collaborator to add
     */
    public void addTeamMember(Collaborator collaborator) {
        teamMembers.add(collaborator);
    }

    /**
     * Returns the name of this Team.
     *
     * @return the name of this Team
     */
    public String getName() {
        return name;
    }
}