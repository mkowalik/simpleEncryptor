package encryptor;

/**
 * Exception thrown when necessery encryptor settings are not set.
 * 
 * @author kowalik
 *
 */
public class CustomizeEncryptorException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the class.
	 * 
	 * @param message
	 *            Extra message to show.
	 */
	public CustomizeEncryptorException(String message) {
		super(message);
	}

}
