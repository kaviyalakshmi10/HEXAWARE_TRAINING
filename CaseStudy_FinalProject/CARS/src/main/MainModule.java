package main;

import dao.CrimeAnalysisServiceImpl;
import dao.ICrimeAnalysisService;
import entity.*;
import exception.IncidentNumberNotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainModule {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ICrimeAnalysisService service = new CrimeAnalysisServiceImpl();

        int choice;
        do {
        	System.out.println("\n===WELCOME TO CARS===\n");
            System.out.println("1. Create New Incident");
            System.out.println("2. Update Incident Status");
            System.out.println("3. View Incidents by Date Range");
            System.out.println("4. Search Incidents by Type");
            System.out.println("5. Generate Incident Report");
            System.out.println("6. View Reports by Incident ID");
            System.out.println("7. Create a Case");
            System.out.println("8. Get Case Details");
            System.out.println("9. Update Case Details");
            System.out.println("10. Get All Cases");
            System.out.println("11. Create Victim");
            System.out.println("12. Create Suspect");
            System.out.println("13. Create Evidence");
            System.out.println("14. Show Incident Brief");
            System.out.println("15. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        Incident incident = new Incident();
                        System.out.print("Enter Incident ID: ");
                        incident.setIncidentId(scanner.nextInt());
                        scanner.nextLine();

                        System.out.print("Enter Type (e.g., Robbery): ");
                        incident.setIncidentType(scanner.nextLine());
                        incident.setIncidentDate(LocalDateTime.now());

                        System.out.print("Enter Latitude: ");
                        incident.setLatitude(scanner.nextDouble());

                        System.out.print("Enter Longitude: ");
                        incident.setLongitude(scanner.nextDouble());
                        scanner.nextLine();

                        System.out.print("Enter Description: ");
                        incident.setDescription(scanner.nextLine());

                        incident.setStatus("Open");

                        System.out.print("Enter Victim ID: ");
                        incident.setVictimId(scanner.nextInt());

                        System.out.print("Enter Suspect ID: ");
                        incident.setSuspectId(scanner.nextInt());

                        boolean created = service.createIncident(incident);
                        System.out.println(created ? " Incident created successfully." : " Failed to create incident.");
                        break;

                    case 2:
                        System.out.print("Enter Incident ID to update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter new status: ");
                        String status = scanner.nextLine();

                        boolean updated = service.updateIncidentStatus(id, status);
                        if (!updated) throw new IncidentNumberNotFoundException("Incident ID " + id + " not found.");
                        System.out.println(" Incident status updated.");
                        break;

                    case 3:
                        System.out.print("Enter start date (yyyy-mm-dd): ");
                        LocalDate start = LocalDate.parse(scanner.nextLine());
                        System.out.print("Enter end date (yyyy-mm-dd): ");
                        LocalDate end = LocalDate.parse(scanner.nextLine());

                        List<Incident> rangeList = service.getIncidentsInDateRange(start, end);
                        printIncidentTable(rangeList);
                        break;

                    case 4:
                        System.out.print("Enter Incident Type to search: ");
                        String type = scanner.nextLine();
                        List<Incident> list = service.searchIncidents(type);
                        printIncidentTable(list);
                        break;

                    case 5:
                        System.out.print("Enter Incident ID to generate report: ");
                        int incidentId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter Officer ID to assign the report: ");
                        int officerId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter report details/description: ");
                        String reportDetails = scanner.nextLine();

                        System.out.print("Enter report status (draft/finalized): ");
                        String reportStatus = scanner.nextLine().toLowerCase();

                        Incident inc = new Incident();
                        inc.setIncidentId(incidentId);

                        Report report = service.generateIncidentReport(inc, officerId, reportDetails, reportStatus);
                        if (report != null && report.getReportId() > 0) {
                            System.out.println(" Report generated successfully: " + report);
                        } else {
                            System.out.println(" Failed to generate report.");
                        }
                        break;

                    case 6:
                        System.out.print("Enter Incident ID to view reports: ");
                        int searchIncidentId = scanner.nextInt();
                        List<Report> reports = service.getReportsByIncidentId(searchIncidentId);
                        if (reports.isEmpty()) {
                            System.out.println(" No reports found for Incident ID " + searchIncidentId);
                        } else {
                            printReportTable(reports);
                        }
                        break;

                    case 7:
                        System.out.print("Enter case description: ");
                        String caseDesc = scanner.nextLine();

                        System.out.print("Enter number of incidents to add: ");
                        int count = scanner.nextInt();
                        List<Incident> incidentList = new ArrayList<>();

                        for (int i = 0; i < count; i++) {
                            System.out.print("Enter incident ID #" + (i + 1) + ": ");
                            int incId = scanner.nextInt();
                            Incident temp = new Incident();
                            temp.setIncidentId(incId);
                            incidentList.add(temp);
                        }

                        scanner.nextLine();
                        Case newCase = service.createCase(caseDesc, incidentList);
                        if (newCase != null && newCase.getCaseId() > 0) {
                            System.out.println(" Case created: ID = " + newCase.getCaseId());
                        } else {
                            System.out.println(" Failed to create case.");
                        }
                        break;

                    case 8:
                        System.out.print("Enter Case ID: ");
                        int getCaseId = scanner.nextInt();
                        scanner.nextLine();

                        Case c = service.getCaseDetails(getCaseId);
                        if (c != null && c.getCaseId() > 0) {
                            System.out.println("Case ID: " + c.getCaseId());
                            System.out.println("Description: " + c.getCaseDescription());
                            System.out.println("Incident IDs: " + c.getIncidentIds());
                        } else {
                            System.out.println(" Case not found.");
                        }
                        break;

                   case 9:
                        System.out.print("Enter Case ID to update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Enter new case description: ");
                        String updatedDesc = scanner.nextLine();

                        System.out.print("Do you want to add incidents to this case? (yes/no): ");
                        String addMore = scanner.nextLine().trim().toLowerCase();

                        List<Integer> incidentIdsToAdd = new ArrayList<>();

                        if (addMore.equals("yes")) {
                            System.out.print("Enter number of incidents to add: ");
                            int newCount = scanner.nextInt();

                            for (int i = 0; i < newCount; i++) {
                                System.out.print("Enter new incident ID #" + (i + 1) + ": ");
                                int incId = scanner.nextInt();
                                incidentIdsToAdd.add(incId);
                            }
                            scanner.nextLine(); // Consume newline
                        }

                        Case updateCase = new Case();
                        updateCase.setCaseId(updateId);
                        updateCase.setCaseDescription(updatedDesc);
                        updateCase.setIncidentIds(incidentIdsToAdd);

                        boolean updatedCaseResult = service.updateCaseDetails(updateCase);
                        System.out.println(updatedCaseResult ? " Case updated successfully." : " Failed to update case.");
                        break;

                    case 10:
                        List<Case> allCases = service.getAllCases();
                        if (allCases.isEmpty()) {
                            System.out.println("No cases found.");
                        } else {
                            System.out.printf("%-10s %-30s\n", "Case ID", "Description");
                            System.out.println("----------------------------------------------");
                            for (Case ac : allCases) {
                                System.out.printf("%-10d %-30s\n", ac.getCaseId(), ac.getCaseDescription());
                            }
                        }
                        break;

                    case 11:
                        Victim victim = new Victim();
                        System.out.print("Enter First Name: ");
                        victim.setFirstName(scanner.nextLine());
                        System.out.print("Enter Last Name: ");
                        victim.setLastName(scanner.nextLine());
                        System.out.print("Enter DOB (yyyy-mm-dd): ");
                        victim.setDateOfBirth(scanner.nextLine());
                        System.out.print("Enter Gender: ");
                        victim.setGender(scanner.nextLine());
                        System.out.print("Enter Address: ");
                        victim.setAddress(scanner.nextLine());
                        System.out.print("Enter Phone Number: ");
                        victim.setPhoneNumber(scanner.nextLine());

                        boolean victimCreated = service.createVictim(victim);
                        System.out.println(victimCreated ? " Victim created.": " Failed to create victim.");
                        break;

                    case 12:
                        Suspect suspect = new Suspect();
                        System.out.print("Enter First Name: ");
                        suspect.setFirstName(scanner.nextLine());
                        System.out.print("Enter Last Name: ");
                        suspect.setLastName(scanner.nextLine());
                        System.out.print("Enter DOB (yyyy-mm-dd): ");
                        suspect.setDateOfBirth(scanner.nextLine());

                        System.out.print("Enter Gender: ");
                        suspect.setGender(scanner.nextLine());
                        System.out.print("Enter Address: ");
                        suspect.setAddress(scanner.nextLine());
                        System.out.print("Enter Phone Number: ");
                        suspect.setPhoneNumber(scanner.nextLine());

                        boolean suspectCreated = service.createSuspect(suspect);
                        System.out.println(suspectCreated ? " Suspect created." : " Failed to create suspect.");
                        break;

                    case 13:
                        Evidence evidence = new Evidence();
                        System.out.print("Enter Description: ");
                        evidence.setDescription(scanner.nextLine());
                        System.out.print("Enter Location Found: ");
                        evidence.setLocationFound(scanner.nextLine());
                        System.out.print("Enter Incident ID: ");
                        evidence.setIncidentId(scanner.nextInt());
                        scanner.nextLine();

                        boolean evidenceCreated = service.createEvidence(evidence);
                        System.out.println(evidenceCreated ? " Evidence created." : " Failed to create evidence.");
                        break;

                    case 14:
                        System.out.print("Enter Incident ID to show brief: ");
                        int briefId = scanner.nextInt();
                        scanner.nextLine();
                        service.getIncidentBrief(briefId);
                        break;

                    case 15:
                        System.out.println(" Exiting CARS. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (IncidentNumberNotFoundException e) {
                System.out.println(" Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }

        } while (choice != 15);
        scanner.close();
    }

    private static void printReportTable(List<Report> reports) {
        System.out.printf("%-10s %-12s %-12s %-12s %-12s %-50s\n",
                "ReportID", "IncidentID", "OfficerID", "Date", "Status", "Description");
        System.out.println("----------------------------------------------------------------------------------------------");

        for (Report r : reports) {
            System.out.printf("%-10d %-12d %-12d %-12s %-12s %-50s\n",
                    r.getReportId(),
                    r.getIncidentId(),
                    r.getReportingOfficerId(),
                    r.getReportDate(),
                    r.getStatus(),
                    r.getReportDetails());
        }
    }

    private static void printIncidentTable(List<Incident> list) {
        if (list.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        System.out.printf("%-10s %-15s %-20s %-12s %-12s %-15s\n",
                "ID", "Type", "Date", "Latitude", "Longitude", "Status");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (Incident inc : list) {
            System.out.printf("%-10d %-15s %-20s %-12.6f %-12.6f %-15s\n",
                    inc.getIncidentId(),
                    inc.getIncidentType(),
                    inc.getIncidentDate(),
                    inc.getLatitude(),
                    inc.getLongitude(),
                    inc.getStatus());
        }
    }
}
