package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class represents an OrganizationRepository in the system.
 * It manages a list of Organization objects and provides methods to manipulate and retrieve them.
 */
public class OrganizationRepository {
    /**
     * The singleton instance of the OrganizationRepository.
     */
    private static OrganizationRepository instance;

    /**
     * The list of Organization objects.
     */
    private final List<Organization> organizations;

    /**
     * Constructs an OrganizationRepository object.
     * It initializes the organizations list as an empty ArrayList.
     */
    public OrganizationRepository() {
        organizations = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the OrganizationRepository.
     * If the instance is null, it initializes it.
     *
     * @return the singleton instance of the OrganizationRepository
     */
    public static OrganizationRepository getInstance() {
        if (instance == null) {
            synchronized (OrganizationRepository.class) {
                instance = new OrganizationRepository();
            }
        }
        return instance;
    }

    /**
     * Returns an Organization object that has a Collaborator with the specified email.
     * If no such Organization exists, it returns an empty Optional.
     *
     * @param collaborator the Collaborator to search for
     * @return an Optional containing the Organization object if found, or an empty Optional if not
     */
    public Optional<Organization> getOrganizationByCollaborator(Collaborator collaborator) {
        for (Organization organization : organizations) {
            if (organization.anyCollaboratorHasEmail(collaborator.getEmail())) {
                return Optional.of(organization);
            }
        }
        return Optional.empty();
    }

    /**
     * Adds an Organization to the repository if it is valid.
     * If the Organization is added successfully, it returns an Optional containing the Organization.
     * If the Organization is not valid, it returns an empty Optional.
     *
     * @param organization the Organization to add
     * @return an Optional containing the added Organization if successful, or an empty Optional if not
     */
    public Optional<Organization> add(Organization organization) {
        if (validateOrganization(organization)) {
            organizations.add(organization);
            return Optional.of(organization);
        }
        return Optional.empty();
    }

    /**
     * Returns a Team object with the specified name from any Organization.
     * If no such Team exists, it returns an empty Optional.
     *
     * @param teamName the name of the Team to search for
     * @return an Optional containing the Team object if found, or an empty Optional if not
     */
    public Optional<Team> getTeamByName(String teamName) {
        for (Organization organization : organizations) {
            for (Team team : organization.getTeams()) {
                if (team.getName().equals(teamName)) {
                    return Optional.of(team);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Returns an Organization object that has a Collaborator with the specified email.
     * If no such Organization exists, it returns null.
     *
     * @param email the email of the Collaborator to search for
     * @return the Organization object if found, or null if not
     */
    public Organization getOrganizationByEmployeeEmail(String email) {

        Organization returnOrganization = null;

        for (Organization organization : organizations) {
            if (organization.anyCollaboratorHasEmail(email)) {
                returnOrganization = organization;
            }
        }

        return returnOrganization;
    }

    /**
     * Validates an Organization.
     * It checks if the Organization already exists in the list.
     *
     * @param organization the Organization to validate
     * @return true if the Organization is valid, false otherwise
     */
    private boolean validateOrganization(Organization organization) {
        return !organizations.contains(organization);
    }

    /**
     * Returns an Organization object with the specified VAT number.
     * If no such Organization exists, it returns an empty Optional.
     *
     * @param vatNumber the VAT number of the Organization to search for
     * @return an Optional containing the Organization object if found, or an empty Optional if not
     */
    public Optional<Organization> getOrganizationByVatNumber(String vatNumber) {
        for (Organization organization : organizations) {
            if (organization.getVatNumber().equals(vatNumber)) {
                return Optional.of(organization);
            }
        }
        return Optional.empty();
    }
}
