package entity;

import java.util.List;

public class Case {
    private int caseId;
    private String caseDescription;
    private List<Integer> incidentIds;

    public Case() {
    }

    public Case(int caseId, String caseDescription, List<Integer> incidentIds) {
        this.caseId = caseId;
        this.caseDescription = caseDescription;
        this.incidentIds = incidentIds;
    }

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getCaseDescription() {
        return caseDescription;
    }

    public void setCaseDescription(String caseDescription) {
        this.caseDescription = caseDescription;
    }

    public List<Integer> getIncidentIds() {
        return incidentIds;
    }

    public void setIncidentIds(List<Integer> incidentIds) {
        this.incidentIds = incidentIds;
    }

    public String toString() {
        return String.format("Case [ID=%d, Description=%s, Incident Count=%d]",
                caseId, caseDescription, incidentIds != null ? incidentIds.size() : 0);
    }
}
