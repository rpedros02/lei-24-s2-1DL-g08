package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Organization;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationRepositoryTest {

    @Test
    void testAddOrganization() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");

        Optional<Organization> returnOrganization = organizationRepository.add(organization);

        assertEquals(organization, returnOrganization.get());
    }

    @Test
    void ensureGetOrganizationByEmployeeWorks() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        Collaborator collaborator = new Collaborator(new Email("john.doe@this.company.com"));
        organization.addCollaborator(collaborator);
        organizationRepository.add(organization);

        Optional<Organization> result = organizationRepository.getOrganizationByCollaborator(collaborator);

        assertEquals(organization, result.get());
    }

    @Test
    void ensureGetOrganizationByEmployeeFails() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        Collaborator collaborator = new Collaborator(new Email("john.doe@this.company.com"));
        organization.addCollaborator(collaborator);
        organizationRepository.add(organization);

        Collaborator collaborator2 = new Collaborator(new Email("jane.doe@this.company.com"));
        Optional<Organization> result = organizationRepository.getOrganizationByCollaborator(collaborator2);

        assertTrue(result.isEmpty());
    }

    @Test
    void ensureGetOrganizationByEmailWorks() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        Collaborator collaborator = new Collaborator(new Email("john.doe@this.company.com"));
        organization.addCollaborator(collaborator);
        organizationRepository.add(organization);

        Optional<Organization> result =
                Optional.of(organizationRepository.getOrganizationByEmployeeEmail("john.doe@this.company.com"));

        assertEquals(organization, result.get());
    }

    @Test
    void ensureAddOrganizationWorks() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        Collaborator collaborator = new Collaborator(new Email("john.doe@this.company.com"));
        organization.addCollaborator(collaborator);

        organizationRepository.add(organization);

        Optional<Organization> returnOrganization =
                Optional.of(organizationRepository.getOrganizationByEmployeeEmail("john.doe@this.company.com"));

        //Assert
        //Make sure both represents the same object
        assertEquals(organization, returnOrganization.get());
        //Make sure it is a clone (different memory addresses)
        assertNotSame(organization, returnOrganization.get());
    }

    @Test
    void ensureAddOrganizationDuplicateFails() {
        OrganizationRepository organizationRepository = new OrganizationRepository();
        Organization organization = new Organization("123456789");
        Collaborator collaborator = new Collaborator(new Email("john.doe@this.company.com"));
        organization.addCollaborator(collaborator);
        organizationRepository.add(organization);

        Optional<Organization> result = organizationRepository.add(organization);

        assertTrue(result.isEmpty());
    }
}