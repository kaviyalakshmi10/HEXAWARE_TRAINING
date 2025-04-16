package test;
import dao.CrimeAnalysisServiceImpl;
import dao.ICrimeAnalysisService;
import entity.Incident;
import entity.Report;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.List;


public class CrimeAnalysisServiceTest {

    static ICrimeAnalysisService service;

    @BeforeAll
    static void setup() {
        service = new CrimeAnalysisServiceImpl();
    }

    @Test
    void testCreateIncident() {
        Incident inc = new Incident();
        inc.setIncidentType("Test Theft");
        inc.setIncidentDate(LocalDateTime.now());
        inc.setLatitude(12.34);
        inc.setLongitude(56.78);
        inc.setDescription("JUnit Test");
        inc.setStatus("Open");
        inc.setVictimId(1);
        inc.setSuspectId(2);

        boolean result = service.createIncident(inc);
        assertTrue(result, "Incident should be created successfully");
    }

    @Test
    void testUpdateIncidentStatus() {
        boolean updated = service.updateIncidentStatus(10, "Closed");
        assertTrue(updated, "Incident status should be updated to Closed");
    }

    @Test
    void testGenerateIncidentReport() {
        Report r = service.generateIncidentReport(
                new Incident(10, "Test", LocalDateTime.now(), 0, 0, "", "Open", 1, 2),
                1,
                "JUnit generated report",
                "draft"
        );
        assertNotNull(r, "Report should not be null");
        assertEquals("draft", r.getStatus());
    }

    @Test
    void testGetReportsByIncidentId() {
        List<Report> reports = service.getReportsByIncidentId(10);
        assertFalse(reports.isEmpty(), "Should return at least one report");
    }
}
