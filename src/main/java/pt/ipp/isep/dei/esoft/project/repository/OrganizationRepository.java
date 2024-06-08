package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.ipp.isep.dei.esoft.project.domain.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrganizationRepository {
    private static OrganizationRepository instance;
    private final List<Organization> organizations;

    private OrganizationRepository() {
        organizations = new ArrayList<>();
    }

    public static OrganizationRepository getInstance() {
        if (instance == null) {
            synchronized (OrganizationRepository.class) {
                instance = new OrganizationRepository();
            }
        }
        return instance;
    }

    public Optional<Organization> getOrganizationByCollaborator(Collaborator collaborator) {
        for (Organization organization : organizations) {
            if (organization.anyCollaboratorHasEmail(collaborator.getEmail())) {
                return Optional.of(organization);
            }
        }
        return Optional.empty();
    }

    public Optional<Organization> add(Organization organization) {
        if (validateOrganization(organization)) {
            organizations.add(organization);
            return Optional.of(organization);
        }
        return Optional.empty();
    }

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

    private boolean validateOrganization(Organization organization) {
        return !organizations.contains(organization);
    }

    public Optional<Organization> getOrganizationByVatNumber(String vatNumber) {
        for (Organization organization : organizations) {
            if (organization.getVatNumber().equals(vatNumber)) {
                return Optional.of(organization);
            }
        }
        return Optional.empty();
    }
}
