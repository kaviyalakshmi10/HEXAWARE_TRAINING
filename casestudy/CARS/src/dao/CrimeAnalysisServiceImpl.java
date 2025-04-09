package dao;

import entity.*;
import util.DBConnUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CrimeAnalysisServiceImpl implements ICrimeAnalysisService {

    private static Connection connection;

    public CrimeAnalysisServiceImpl() {
        try {
            connection = DBConnUtil.getConnection();
        } catch (Exception e) {
            System.out.println("Error establishing database connection: " + e.getMessage());
        }
    }

    public boolean createIncident(Incident incident) {
        String sql = "INSERT INTO incidents (incident_id, incident_type, incident_date, latitude, longitude, description, status, victim_id, suspect_id) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, incident.getIncidentId());
            ps.setString(2, incident.getIncidentType());
            ps.setTimestamp(3, Timestamp.valueOf(incident.getIncidentDate()));
            ps.setDouble(4, incident.getLatitude());
            ps.setDouble(5, incident.getLongitude());
            ps.setString(6, incident.getDescription());
            ps.setString(7, incident.getStatus());
            ps.setInt(8, incident.getVictimId());
            ps.setInt(9, incident.getSuspectId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error creating incident: " + e.getMessage());
            return false;
        }
    }

    public boolean updateIncidentStatus(int incidentId, String newStatus) {
        String sql = "UPDATE incidents SET status = ? WHERE incident_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, incidentId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating incident status: " + e.getMessage());
            return false;
        }
    }
    public List<Incident> getIncidentsInDateRange(LocalDate startDate, LocalDate endDate) {
        List<Incident> incidents = new ArrayList<>();
        String sql = "SELECT * FROM incidents WHERE incident_date BETWEEN ? AND ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Incident incident = new Incident(
                    rs.getInt("incident_id"),
                    rs.getString("incident_type"),
                    rs.getTimestamp("incident_date").toLocalDateTime(),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude"),
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getInt("victim_id"),
                    rs.getInt("suspect_id")
                );
                incidents.add(incident);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving incidents in date range: " + e.getMessage());
        }

        return incidents;
    }

    public List<Incident> searchIncidents(String incidentType) {
        List<Incident> results = new ArrayList<>();
        String sql = "SELECT * FROM incidents WHERE incident_type = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, incidentType);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Incident incident = new Incident(
                    rs.getInt("incident_id"),
                    rs.getString("incident_type"),
                    rs.getTimestamp("incident_date").toLocalDateTime(),
                    rs.getDouble("latitude"),
                    rs.getDouble("longitude"),
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getInt("victim_id"),
                    rs.getInt("suspect_id")
                );
                results.add(incident);
            }

        } catch (SQLException e) {
            System.out.println("Error searching incidents: " + e.getMessage());
        }

        return results;
    }
    public Report generateIncidentReport(Incident incident, int officerId, String reportDetails, String reportStatus) {
        Report report = new Report();
        report.setIncidentId(incident.getIncidentId());
        report.setReportingOfficerId(officerId);
        report.setReportDate(LocalDate.now());
        report.setReportDetails(reportDetails);
        report.setStatus(reportStatus);

        String insertSql = "INSERT INTO reports (incident_id, reporting_officer, report_date, report_details, status) " +
                           "VALUES (?, ?, ?, ?, ?)";
        String updateIncidentSql = "UPDATE incidents SET status = ? WHERE incident_id = ?";

        try (
            PreparedStatement psInsert = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement psUpdate = connection.prepareStatement(updateIncidentSql)
        ) {
            
            psInsert.setInt(1, report.getIncidentId());
            psInsert.setInt(2, report.getReportingOfficerId());
            psInsert.setDate(3, Date.valueOf(report.getReportDate()));
            psInsert.setString(4, report.getReportDetails());
            psInsert.setString(5, report.getStatus());

            int rowsInserted = psInsert.executeUpdate();
            if (rowsInserted > 0) {
                ResultSet rs = psInsert.getGeneratedKeys();
                if (rs.next()) {
                    report.setReportId(rs.getInt(1));
                }

                psUpdate.setString(1, reportStatus.equalsIgnoreCase("finalized") ? "Closed" : "Under Investigation");
                psUpdate.setInt(2, report.getIncidentId());
                psUpdate.executeUpdate();

                return report;
            } else {
                throw new SQLException("Failed to insert report.");
            }

        } catch (SQLException e) {
            System.out.println("Error generating incident report: " + e.getMessage());
            return null;
        }
    }

    public List<Report> getReportsByIncidentId(int incidentId) {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT * FROM reports WHERE incident_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, incidentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Report report = new Report();
                report.setReportId(rs.getInt("report_id"));
                report.setIncidentId(rs.getInt("incident_id"));
                report.setReportingOfficerId(rs.getInt("reporting_officer"));
                report.setReportDate(rs.getDate("report_date").toLocalDate());
                report.setReportDetails(rs.getString("report_details"));
                report.setStatus(rs.getString("status"));
                reports.add(report);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching reports: " + e.getMessage());
        }

        return reports;
    }

    @Override
    public Case createCase(String caseDescription, List<Incident> incidentList) {
        Case newCase = new Case();
        String insertCaseSql = "INSERT INTO cases (case_description) VALUES (?)";
        String linkIncidentsSql = "INSERT INTO case_incidents (case_id, incident_id) VALUES (?, ?)";

        try (
            PreparedStatement caseStmt = connection.prepareStatement(insertCaseSql, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement linkStmt = connection.prepareStatement(linkIncidentsSql)
        ) {
            caseStmt.setString(1, caseDescription);
            caseStmt.executeUpdate();

            ResultSet rs = caseStmt.getGeneratedKeys();
            if (rs.next()) {
                int caseId = rs.getInt(1);
                newCase.setCaseId(caseId);
                newCase.setCaseDescription(caseDescription);

                for (Incident inc : incidentList) {
                    linkStmt.setInt(1, caseId);
                    linkStmt.setInt(2, inc.getIncidentId());
                    linkStmt.addBatch();
                }
                linkStmt.executeBatch();

                List<Integer> ids = new ArrayList<>();
                for (Incident i : incidentList) ids.add(i.getIncidentId());
                newCase.setIncidentIds(ids);
            }
        } catch (SQLException e) {
            System.out.println("Error creating case: " + e.getMessage());
        }

        return newCase;
    }
    @Override
    public Case getCaseDetails(int caseId) {
        Case result = new Case();
        List<Integer> incidentIds = new ArrayList<>();

        try (
            PreparedStatement psCase = connection.prepareStatement("SELECT * FROM cases WHERE case_id = ?");
            PreparedStatement psLink = connection.prepareStatement("SELECT incident_id FROM case_incidents WHERE case_id = ?")
        ) {
            psCase.setInt(1, caseId);
            ResultSet rs1 = psCase.executeQuery();
            if (rs1.next()) {
                result.setCaseId(rs1.getInt("case_id"));
                result.setCaseDescription(rs1.getString("case_description"));
            }

            psLink.setInt(1, caseId);
            ResultSet rs2 = psLink.executeQuery();
            while (rs2.next()) {
                incidentIds.add(rs2.getInt("incident_id"));
            }

            result.setIncidentIds(incidentIds);

        } catch (SQLException e) {
            System.out.println("Error retrieving case: " + e.getMessage());
        }

        return result;
    }

    public boolean updateCaseDetails(Case updatedCase) {
        String sql = "UPDATE cases SET case_description = ? WHERE case_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, updatedCase.getCaseDescription());
            ps.setInt(2, updatedCase.getCaseId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating case: " + e.getMessage());
            return false;
        }
    }


    public List<Case> getAllCases() {
        List<Case> cases = new ArrayList<>();
        String sql = "SELECT * FROM cases";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Case c = new Case();
                c.setCaseId(rs.getInt("case_id"));
                c.setCaseDescription(rs.getString("case_description"));
                cases.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching all cases: " + e.getMessage());
        }

        return cases;
    }
}
