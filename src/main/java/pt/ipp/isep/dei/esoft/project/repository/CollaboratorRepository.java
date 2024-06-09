package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * This class represents a CollaboratorRepository in the system.
 * It manages a list of Collaborator objects.
 */
public class CollaboratorRepository {

    /**
     * The list of Collaborator objects.
     */
    private List<Collaborator> collaborator;

    /**
     * Constructs a CollaboratorRepository object.
     * It initializes the collaborator list as an empty ArrayList.
     */
    public CollaboratorRepository() {
        this.collaborator = new ArrayList<>();
    }

    /**
     * Adds a collaborator to the repository.
     * It validates the collaborator before adding.
     * If the collaborator is valid, it clones the collaborator and adds it to the list.
     *
     * @param collaborator The collaborator to add.
     * @return An Optional containing the added collaborator if successful, or an empty Optional if not.
     */
    public Optional<Collaborator> add(Collaborator collaborator) {
        if (validateCollaborator(collaborator)) {
            Collaborator newCollaborator = collaborator.clone();
            this.collaborator.add(newCollaborator);
            return Optional.of(newCollaborator);
        }
        return Optional.empty();
    }

    /**
     * Finds a collaborator by their email.
     * It iterates over the list of collaborators and returns the first collaborator with the matching email.
     *
     * @param email The email of the collaborator.
     * @return An Optional containing the found collaborator if successful, or an empty Optional if not.
     */
    public Optional<Collaborator> findByEmail(String email) {
        for (Collaborator collaborator : this.collaborator) {
            if (collaborator.getEmail().equals(email)) {
                return Optional.of(collaborator);
            }
        }
        return Optional.empty();
    }

    /**
     * Gets all collaborators in the repository.
     *
     * @return A list of all collaborators.
     */
    public List<Collaborator> getAll() {
        return new ArrayList<>(this.collaborator);
    }

    /**
     * Gets a collaborator by their number.
     * It iterates over the list of collaborators and returns the first collaborator with the matching number.
     *
     * @param number The number of the collaborator.
     * @return The collaborator if found, or null if not.
     */
    public Collaborator getCollaboratorByNumber(String number) {
        for (Collaborator collaborator : this.collaborator) {
            if (collaborator.getIdNumber().equals(number)) {
                return collaborator;
            }
        }
        return null;
    }

    /**
     * Validates a collaborator.
     * It checks if a collaborator with the same email already exists in the list.
     *
     * @param collaborator The collaborator to validate.
     * @return True if the collaborator is valid, false otherwise.
     */
    private boolean validateCollaborator(Collaborator collaborator) {
        for (Collaborator existingCollaborator : this.collaborator) {
            if (existingCollaborator.getEmail().equals(collaborator.getEmail())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the first collaborator with a specific skill.
     * It uses a stream to filter the collaborators by the specified skill and returns the first match.
     *
     * @param skill The skill to search for.
     * @return The first collaborator with the specified skill if successful, or null if not.
     */
    public Collaborator getFirstCollaboratorBySkill(String skill) {
        return collaborator.stream()
                .filter(collaborator -> collaborator.getAssignedSkills().contains(skill))
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds a collaborator to the repository.
     * It checks if the collaborator already exists in the list before adding.
     *
     * @param collaborator The collaborator to add.
     * @return True if the collaborator was added successfully, false otherwise.
     */
    public boolean addCollaborator(Collaborator collaborator) {
        if (this.collaborator.contains(collaborator)) {
            return false;
        }
        this.collaborator.add(collaborator);
        return true;
    }

    /**
     * Checks if a collaborator exists in the repository.
     *
     * @param collaborator The collaborator to check.
     * @return True if the collaborator exists, false otherwise.
     */
    public boolean hasCollaborator(Collaborator collaborator) {
        return this.collaborator.contains(collaborator);
    }

    /**
     * Gets a collaborator by their email.
     * It iterates over the list of collaborators and returns the first collaborator with the matching email.
     *
     * @param mail The email of the collaborator.
     * @return The collaborator if found, or null if not.
     */
    public Collaborator getCollaboratorByEmail(String mail) {
        for (Collaborator collaborator : this.collaborator) {
            if (collaborator.getEmail().equals(mail)) {
                return collaborator;
            }
        }
        return null;
    }

    /**
     * Removes a collaborator from the repository.
     *
     * @param collaborator The collaborator to remove.
     */
    public void removeCollaborator(Collaborator collaborator) {
        this.collaborator.remove(collaborator);
    }

    /**
     * Gets all collaborators in the repository.
     *
     * @return A list of all collaborators.
     */
    public List<Collaborator> getCollaborator() {
        return this.collaborator;
    }

    /**
     * Gets all collaborators with a specific skill.
     * It uses a stream to filter the collaborators by the specified skill and collects the results into a list.
     *
     * @param skill The skill to search for.
     * @return A list of all collaborators with the specified skill.
     */
    public List<Collaborator> getAllCollaboratorsBySkill(String skill) {
        return this.collaborator.stream()
                .filter(collaborator -> collaborator.hasSkill(skill))
                .collect(Collectors.toList());
    }

    /**
     * Creates a new Collaborator with the specified parameters and adds it to the repository.
     *
     * @param name The name of the collaborator.
     * @param birthDate The birth date of the collaborator.
     * @param admissionDate The admission date of the collaborator.
     * @param mobileNumber The mobile number of the collaborator.
     * @param email The email of the collaborator.
     * @param taxPayerNumber The tax payer number of the collaborator.
     * @param idDocType The ID document type of the collaborator.
     * @param idNumber The ID number of the collaborator.
     * @param address The address of the collaborator.
     * @param job The job of the collaborator.
     * @param task The task of the collaborator.
     * @return The created collaborator.
     */
    public Collaborator Collaborator(String name, Date birthDate, Date admissionDate, int mobileNumber, String email, int taxPayerNumber, IdDocType idDocType, String idNumber, Address address, Job job, Task task) {
        Collaborator collaborator = new Collaborator(name, birthDate, admissionDate, mobileNumber, email, taxPayerNumber, idDocType,idNumber, address, job, task);
        addCollaborator(collaborator);
        return collaborator;
    }

    /**
     * Assigns a task to a team of collaborators.
     *
     * @param team The team of collaborators.
     * @param task The task to assign.
     */
    public void assignTaskToTeam(TreeSet<Collaborator> team, Task task) {
        for (Collaborator collaborator : team) {
            collaborator.assignTask(task);
        }
    }

    /**
     * Assigns a list of skills to a collaborator.
     *
     * @param collaborator The collaborator to assign the skills to.
     * @param skills The list of skills to assign.
     */
    public void assignSkills(Collaborator collaborator, List<Skill> skills) {
        collaborator.assignSkills(skills);
    }
}