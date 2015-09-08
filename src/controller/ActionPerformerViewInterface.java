package controller;

/**
 * This is interface which should be implemented by GUI view which is
 * responsible for displaying orginal and encrypted text string representation.
 * 
 * @author kowalik
 *
 */
public interface ActionPerformerViewInterface {

	/**
	 * Function resposible for displaying orginal file representation.
	 * 
	 * @param file
	 *            String to display.
	 */
	public void showOrginalFile(String file);

	/**
	 * Function responsible for dispalying encrypted file representation.
	 * 
	 * @param file
	 *            String to display.
	 */
	public void showEncryptedFile(String file);

}
