package encryptor;

/**
 * Assistant class for returning two String - to display and to save in file.
 * 
 * @author kowalik
 *
 */
public class EncryptorReturn {

	private String toDisplay;
	private String toSaveInFile;

	/**
	 * Constructor of the class which saves given objects in private fields.
	 * 
	 * @param toDisplay
	 *            String to display.
	 * @param toSaveInFile
	 *            String to save in file.
	 */
	public EncryptorReturn(String toDisplay, String toSaveInFile) {
		this.toDisplay = toDisplay;
		this.toSaveInFile = toSaveInFile;
	}

	/**
	 * Getter for string to display.
	 * 
	 * @return string to display.
	 */
	public String getToDisplay() {
		return toDisplay;
	}

	/**
	 * Getter for string to save in file.
	 * 
	 * @return string to save in file.
	 */
	public String getToSaveInFIle() {
		return toSaveInFile;
	}

}
