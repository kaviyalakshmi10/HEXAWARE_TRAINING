package exception;

/**
 * Thrown when an incident ID is not found in the database.
 */
public class IncidentNumberNotFoundException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IncidentNumberNotFoundException() {
        super("Incident number not found.");
    }

    public IncidentNumberNotFoundException(String message) {
        super(message);
    }
}
