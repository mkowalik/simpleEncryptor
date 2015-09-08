package controller;

/**
 * Interface which should be implemented by GUI view and methods are called when
 * several warning or exception occurs.
 * 
 * @author kowalik
 *
 */
public interface ErrorDisplayInterface {

	/**
	 * Method to call when FileNotFoundException occurs.
	 * 
	 * @param message
	 *            Extra message to display.
	 */
	public void noSuchFileWarning(String message);

	/**
	 * Method to call when any IOException occurs.
	 * 
	 * @param message
	 *            Extra message to display.
	 */
	public void readingFileProblemWarning(String message);

	/**
	 * Method to call when CustomizeEncryptorException occurs.
	 * 
	 * @param message
	 *            Extra message to display.
	 */
	public void customizeEncryptorWarning(String message);

}
