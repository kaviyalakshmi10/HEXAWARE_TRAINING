package exception;

public class GeneralApplicationException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GeneralApplicationException() {
        super("An unexpected application error occurred.");
    }

    public GeneralApplicationException(String message) {
        super(message);
    }
}
