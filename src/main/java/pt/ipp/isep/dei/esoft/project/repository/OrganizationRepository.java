package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Organization;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrganizationRepository {
    private static OrganizationRepository instance;
    private final List<Organization> organizations;

    public OrganizationRepository() {
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

        Optional<Organization> returnOrganization = Optional.empty();

        for (Organization organization : organizations) {
            if (organization.anyCollaboratorHasEmail(collaborator.getEmail())) {
                returnOrganization = Optional.of(organization);
            }
        }

        return returnOrganization;
    }

    public Organization getOrganizationByEmployeeEmail(String email) {

        Organization returnOrganization = null;

        for (Organization organization : organizations) {
            if (organization.anyCollaboratorHasEmail(email)) {
                returnOrganization = organization;
            }
        }

        return returnOrganization;
    }

    public Optional<Organization> add(Organization organization) {

        Optional<Organization> newOrganization = Optional.empty();
        boolean operationSuccess = false;

        if (validateOrganization(organization)) {
            newOrganization = Optional.of(organization.clone());
            operationSuccess = organizations.add(newOrganization.get());
        }

        if (!operationSuccess) {
            newOrganization = Optional.empty();
        }

        return newOrganization;

    }

    private boolean validateOrganization(Organization organization) {
        boolean isValid = !organizations.contains(organization);

        return isValid;
    }
}