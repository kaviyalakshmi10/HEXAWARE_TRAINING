package entity;

import java.time.LocalDateTime;

public class Incident {
    private int incidentId;
    private String incidentType;
    private LocalDateTime incidentDate;
    private double latitude;
    private double longitude;
    private String description;
    private String status;
    private int victimId;
    private int suspectId;

    public Incident() {
    }

    public Incident(int incidentId, String incidentType, LocalDateTime incidentDate, double latitude, double longitude,
                    String description, String status, int victimId, int suspectId) {
        this.incidentId = incidentId;
        this.incidentType = incidentType;
        this.incidentDate = incidentDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.status = status;
        this.victimId = victimId;
        this.suspectId = suspectId;
    }

    public int getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(int incidentId) {
        this.incidentId = incidentId;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public LocalDateTime getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(LocalDateTime incidentDate) {
        this.incidentDate = incidentDate;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVictimId() {
        return victimId;
    }

    public void setVictimId(int victimId) {
        this.victimId = victimId;
    }

    public int getSuspectId() {
        return suspectId;
    }

    public void setSuspectId(int suspectId) {
        this.suspectId = suspectId;
    }

    public String toString() {
        return String.format("Incident [ID=%d, Type=%s, Date=%s, Lat=%.6f, Lng=%.6f, Status=%s]",
                incidentId, incidentType, incidentDate, latitude, longitude, status);
    }
}
