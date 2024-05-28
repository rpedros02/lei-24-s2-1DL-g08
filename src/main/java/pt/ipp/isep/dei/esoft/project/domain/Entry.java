package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.DegreeOfUrgencyRepository;

import java.util.Objects;

public class Entry {
    private String title;
    private String description;
    private DegreeOfUrgencyRepository degreeOfUrgency;
    private Date dateBegin;
    private Date dateEnd;
    private String status;
    private GreenSpace greenSpace;

    public Entry(String title) {
        this.title = title;
    }

    public Entry(String title, String description, DegreeOfUrgencyRepository degreeOfUrgency, Date dateBegin, Date dateEnd, String status, GreenSpace greenSpace) {
        this.title = title;
        this.description = description;
        this.degreeOfUrgency = degreeOfUrgency;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.status = status;
        this.greenSpace = greenSpace;
    }

    public GreenSpace getGreenSpace() {
        return greenSpace;
    }

    public void setGreenSpace(GreenSpace greenSpace) {
        this.greenSpace = greenSpace;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public DegreeOfUrgencyRepository getDegreeOfUrgency() {
        return degreeOfUrgency;
    }

    public double getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setDegreeOfUrgency(DegreeOfUrgencyRepository degreeOfUrgency) {
        this.degreeOfUrgency = degreeOfUrgency;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean validateEntry() {
        return !title.isEmpty() && !description.isEmpty() && duration > 0;
    }

    public boolean addEntry() {
        return validateEntry();
    }

    @Override
    public Entry clone() {
        return new Entry(title, description, degreeOfUrgency, duration, status, greenSpace);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entry)) return false;
        Entry entry = (Entry) o;
        return Double.compare(entry.duration, duration) == 0 &&
                Objects.equals(title, entry.title) &&
                Objects.equals(description, entry.description) &&
                Objects.equals(degreeOfUrgency, entry.degreeOfUrgency) &&
                Objects.equals(status, entry.status) &&
                Objects.equals(greenSpace, entry.greenSpace);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, degreeOfUrgency, duration, status, greenSpace);
    }

    @Override
    public String toString() {
        return "Entry{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", degreeOfUrgency='" + degreeOfUrgency + '\'' +
                ", dateBegin=" + dateBegin.toString() +
                ", dateEnd=" + dateEnd.toString() +
                ", status='" + status + '\'' +
                ", greenSpace='" + greenSpace + '\'' +
                '}';
    }

    public Date getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
