package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public class Agenda {
    private Task task;
    private Team team;
    private List<Vehicle> vehicles;

    public Agenda(Task task, Team team, List<Vehicle> vehicles) {
        this.task = task;
        this.team = team;
        this.vehicles = vehicles;
    }

    public Task getTask() {
        return task;
    }

    public Team getTeam() {
        return team;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public boolean addVehicle(Vehicle vehicle) {
        return this.vehicles.add(vehicle);
    }

    public boolean removeVehicle(Vehicle vehicle) {
        return this.vehicles.remove(vehicle);
    }

    public boolean hasVehicle(Vehicle vehicle) {
        return this.vehicles.contains(vehicle);
    }

    public boolean hasTeam(Team team) {
        return this.team.equals(team);
    }

    public boolean hasTask(Task task) {
        return this.task.equals(task);
    }

    public boolean hasVehicles(List<Vehicle> vehicles) {
        return this.vehicles.equals(vehicles);
    }
}
