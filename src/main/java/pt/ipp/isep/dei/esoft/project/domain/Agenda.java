package pt.ipp.isep.dei.esoft.project.domain;

import java.util.List;

public class Agenda {
    private Entry entry;
    private Team team;
    private List<Vehicle> vehicles;

    public Agenda(Entry entry, Team team, List<Vehicle> vehicles) {
        this.entry = entry;
        this.team = team;
        this.vehicles = vehicles;
    }

    public Entry getEntry() {
        return entry;
    }

    public Team getTeam() {
        return team;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
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

    public boolean hasEntry(Entry entry) {
        return this.entry.equals(entry);
    }

    public boolean hasVehicles(List<Vehicle> vehicles) {
        return this.vehicles.equals(vehicles);
    }
}
