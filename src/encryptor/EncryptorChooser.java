package encryptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to register and hold handlers to encryptors possible to use in
 * aplication.
 * 
 * @author kowalik
 *
 */
public class EncryptorChooser {

	private List<EncryptorInterface> list = new ArrayList<>();
	private EncryptorInterface actualEncryptor = null;

	/**
	 * Adds/registers encryptor to chooser object.
	 * 
	 * @param enc
	 *            Encryptor to register.
	 */
	public void registerEncryptor(EncryptorInterface enc) {
		list.add(enc);
	}

	/**
	 * Returns list of registered encryptors.
	 * 
	 * @return List<EncryptorInterface> of registered encryptors.
	 */
	public List<EncryptorInterface> getEncryptorList() {
		return list;
	}

	/**
	 * Sets actually using encryptor.
	 * 
	 * @param actualEncryptor
	 *            Actually using encryptor.
	 */
	public void setActualEncryptor(EncryptorInterface actualEncryptor) {
		this.actualEncryptor = actualEncryptor;
	}

	/**
	 * Getter for actually using encryptor.
	 * 
	 * @return Actually using encryptor.
	 */
	public EncryptorInterface getActualEncryptor() {
		return actualEncryptor;
	}

}
