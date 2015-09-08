package encryptor;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Interface which should be implemneted by class which is to encrypt given
 * file.
 * 
 * @author kowalik
 *
 */
public interface EncryptorInterface {

	/**
	 * Encrypts given string and returns assistant class EncryptorReturn object
	 * with encrypted strings - to display and to save in file.
	 * 
	 * @param in
	 *            String to encrypt
	 * @return EncryptorReturn object with encrypted strings - to display and to
	 *         save in file.
	 * @throws CustomizeEncryptorException
	 */
	public EncryptorReturn encrypt(String in) throws CustomizeEncryptorException;

	/**
	 * Decrypts given string and returns assistant class EncryptorReturn object
	 * with decrypted strings - to display and to save in file.
	 * 
	 * @param in
	 *            String to decrypt
	 * @return EncryptorReturn object with decrypted strings - to display and to
	 *         save in file.
	 * @throws CustomizeEncryptorException
	 */
	public EncryptorReturn decrytp(String in) throws CustomizeEncryptorException;

	/**
	 * Preprocessing of read from file string intended to be encrypted for
	 * making it available to display.
	 * 
	 * @param in
	 *            String which shoul be processed.
	 * @return Preprocessed string.
	 */
	public String preprocessingEncryptToDisplay(String in);

	/**
	 * Preprocessing of read from file string intended to be decrypted for
	 * making it available to display.
	 * 
	 * @param in
	 *            String which shoul be processed.
	 * @return Preprocessed string.
	 */
	public String preprocessingDecryptToDisplay(String in);

	/**
	 * Prepares JDialog widged for customizing this encryptor for being
	 * available to encrypt file, like providing a password.
	 * 
	 * @param master
	 *            Master frame on which widget will be displayed.
	 * @return JDialog with fields needed for customizing encryptor.
	 */
	public JDialog getParametersWidget(JFrame master);

}
