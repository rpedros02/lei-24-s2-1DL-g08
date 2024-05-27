package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.*;
import pt.ipp.isep.dei.esoft.project.repository.AgendaRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import java.io.Serializable;
import java.util.List;

public class AgendaController implements Serializable {
    private AgendaRepository agendaRepository;

    /**
     * Instantiates a new Agenda entry controller.
     */
    public AgendaController() {
        if (agendaRepository == null) {
            Repositories repositories = Repositories.getInstance();
            agendaRepository = repositories.getAgendaEntryRepository();
        }
    }

    /**
     * Method to create an agenda entry
     *
     * @param task     Task
     * @param team     Team
     * @param vehicles List of vehicles
     * @return True if the agenda entry was created successfully, false otherwise
     */
    public boolean createAgendaEntry(Task task, Team team, List<Vehicle> vehicles) {
        Agenda newEntry = new Agenda(task, team, vehicles);
        return agendaRepository.addAgendaEntry(newEntry);
    }

    /**
     * Method to update an agenda entry
     *
     * @param agenda Agenda entry
     * @param entry        Entry
     * @param team        Team
     * @param vehicles    List of vehicles
     * @return True if the agenda entry was updated successfully, false otherwise
     */
    public boolean updateAgendaEntry(Agenda agenda, Entry entry, Team team, List<Vehicle> vehicles) {
        return agendaRepository.updateAgendaEntry(agenda, entry, team, vehicles);
    }

    /**
     * Method to remove an agenda entry
     *
     * @param agenda Agenda entry
     * @return True if the agenda entry was removed successfully, false otherwise
     */
    public boolean removeAgendaEntry(Agenda agenda) {
        return agendaRepository.removeAgendaEntry(agenda);
    }

    /**
     * Method to get all agenda entries
     *
     * @return List of all agenda entries
     */
    public List<Agenda> getAgendaEntries() {
        return agendaRepository.getAgendaEntries();
    }

    /**
     * Method to set the agenda entry repository
     *
     * @param agendaRepository Agenda entry repository
     */
    public void setAgendaEntryRepository(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    /**
     * Method to get the agenda entry repository
     *
     * @return Agenda entry repository
     */
    public AgendaRepository getAgendaEntryRepository() {
        return agendaRepository;
    }

    /**
     * Method to get all teams
     *
     * @return List of all teams
     */
    public List<Team> getTeams() {
        return agendaRepository.getTeams();
    }

    /**
     * Method to assign a team to an agenda entry
     *
     * @param entry Agenda entry
     * @param team  Team
     * @return True if the team was assigned successfully, false otherwise
     */
    public boolean assignTeamToAgendaEntry(Agenda entry, Team team) {
        return agendaRepository.assignTeamToAgendaEntry(entry, team);
    }

    /**
     * Method to check if a task is in the agenda
     *
     * @param task Task
     * @return True if the task is in the agenda, false otherwise
     */
    public boolean isTaskInAgenda(Task task) {
        return agendaRepository.isTaskInAgenda(task);
    }

    /**
     * Method to send an email to all team members
     *
     * @param team    Team
     * @param message Message to send
     * @return True if the email was sent successfully, false otherwise
     */
    public boolean sendEmailToTeamMembers(Team team, String message) {
        List<Collaborator> collaborators = team.getCollaborators();
        EmailService emailService = EmailServiceFactory.getEmailService();

        for (Collaborator collaborator : collaborators) {
            String email = collaborator.getEmail();
            if (!emailService.sendEmail(email, message)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Method to get the email service factory
     *
     * @return Email service factory
     */
    public interface EmailService {
        boolean sendEmail(String recipient, String message);
    }

    /**
     * Method to get the email service factory
     *
     * @return Email service factory
     */
    public interface EmailServiceFactory {
        static EmailService getEmailService() {
            // Implementation depends on your project's requirements
            // This method should read the configuration file and return an instance of the appropriate EmailService implementation
            return null;
        }
    }
