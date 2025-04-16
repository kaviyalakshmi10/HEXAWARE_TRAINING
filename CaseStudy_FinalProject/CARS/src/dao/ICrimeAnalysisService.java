package dao;

import entity.*;

import java.time.LocalDate;
import java.util.List;

public interface ICrimeAnalysisService {

    boolean createIncident(Incident incident);

    boolean updateIncidentStatus(int incidentId, String newStatus);

    List<Incident> getIncidentsInDateRange(LocalDate startDate, LocalDate endDate);

    List<Incident> searchIncidents(String incidentType);

    Report generateIncidentReport(Incident incident, int officerId, String reportDetails, String reportStatus);

    List<Report> getReportsByIncidentId(int incidentId);

    Case createCase(String caseDescription, List<Incident> incidentList);

    Case getCaseDetails(int caseId);

    boolean updateCaseDetails(Case updatedCase);

    List<Case> getAllCases();

    boolean createVictim(Victim victim);

    boolean createSuspect(Suspect suspect);

    boolean createEvidence(Evidence evidence);

    void getIncidentBrief(int incidentId);
}
