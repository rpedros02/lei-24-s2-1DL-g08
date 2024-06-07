package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.domain.Enums.IdDocType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CollaboratorRepository {


    private List<Collaborator> collaborator;

    /**
     * Constructor for CollaboratorRepository.
     */
    public CollaboratorRepository() {
        this.collaborator = new ArrayList<>();
    }

    /**
     * Adds a collaborator to the repository.
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
     * Gets all collaborator in the repository.
     *
     * @return A list of all collaborator.
     */
    public List<Collaborator> getAll() {
        return new ArrayList<>(this.collaborator);
    }

    /**
     * Gets a collaborator by their number.
     *
     * @param number The number of the collaborator.
     * @return An Optional containing the found collaborator if successful, or an empty Optional if not.
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
     * Removes a collaborator from the repository.
     *
     * @param collaborator The collaborator to remove.
     * @return True if the collaborator was removed successfully, false otherwise.
     */
    public boolean hasCollaborator(Collaborator collaborator) {
        return this.collaborator.contains(collaborator);
    }

    /**
     * Gets a collaborator by their email.
     *
     * @param mail The email of the collaborator.
     * @return The found collaborator if successful, or null if not.
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
     * Gets all collaborator in the repository.
     *
     * @return A list of all collaborator.
     */
    public List<Collaborator> getCollaborator() {
        return this.collaborator;
    }

    /**
     * Gets all collaborator with a specific skill.
     *
     * @param skill The skill to search for.
     * @return A list of all collaborator with the specified skill.
     */
    public List<Collaborator> getAllCollaboratorsBySkill(String skill) {
        return this.collaborator.stream()
                .filter(collaborator -> collaborator.hasSkill(skill))
                .collect(Collectors.toList());
    }

    /**
     * Gets all collaborator with a specific job.
     *
     * @param job The job to search for.
     * @return A list of all collaborator with the specified job.
     */
    public Collaborator Collaborator(String name, Date birthDate, Date admissionDate, int mobileNumber, String email, int taxPayerNumber, IdDocType idDocType, String idNumber, Address address, Job job, Task task) {
        Collaborator collaborator = new Collaborator(name, birthDate, admissionDate, mobileNumber, email, taxPayerNumber, idDocType,idNumber, address, job, task);
        addCollaborator(collaborator);
        return collaborator;
    }
    public void assignTaskToTeam(TreeSet<Collaborator> team, Task task) {
        for (Collaborator collaborator : team) {
            collaborator.assignTask(task);

        }
    }

    public void assignSkills(Collaborator collaborator, List<Skill> skills) {
        collaborator.assignSkills(skills);
    }
}