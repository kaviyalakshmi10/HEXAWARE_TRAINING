package entity;

import java.time.LocalDate;

public class Report {
    private int reportId;
    private int incidentId;
    private int reportingOfficerId;
    private LocalDate reportDate;
    private String reportDetails;
    private String status; 

    public Report() {
    }

    public Report(int reportId, int incidentId, int reportingOfficerId,
                  LocalDate reportDate, String reportDetails, String status) {
        this.reportId = reportId;
        this.incidentId = incidentId;
        this.reportingOfficerId = reportingOfficerId;
        this.reportDate = reportDate;
        this.reportDetails = reportDetails;
        this.status = status;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public int getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(int incidentId) {
        this.incidentId = incidentId;
    }

    public int getReportingOfficerId() {
        return reportingOfficerId;
    }

    public void setReportingOfficerId(int reportingOfficerId) {
        this.reportingOfficerId = reportingOfficerId;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportDetails() {
        return reportDetails;
    }

    public void setReportDetails(String reportDetails) {
        this.reportDetails = reportDetails;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return String.format("Report [ID=%d, IncidentID=%d, OfficerID=%d, Date=%s, Status=%s]",
                reportId, incidentId, reportingOfficerId, reportDate, status);
    }
}
