package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import encryptor.CustomizeEncryptorException;
import encryptor.EncryptorInterface;
import encryptor.EncryptorChooser;
import encryptor.EncryptorReturn;
import model.CurrentFileModel;

/**
 * Class to control actions called from event handlers. It is controller as in
 * MVC pattern controller.
 * 
 * @author kowalik
 *
 */
public class ActionsController {

	private CurrentFileModel currentFileModel;
	private EncryptorChooser encryptoChooser;
	private EncryptorInterface actualEncryptor = null;

	/**
	 * Constructor of the class.
	 * 
	 * @param currentFileModel
	 *            Model of the actually carrying file.
	 * @param encrytporChooser
	 *            Chooser of encryptors probably constructed in Main class.
	 */
	public ActionsController(CurrentFileModel currentFileModel, EncryptorChooser encrytporChooser) {
		this.currentFileModel = currentFileModel;
		this.encryptoChooser = encrytporChooser;
	}

	/**
	 * Method to load orginal text from given file. Loads String from file and
	 * displays it in GUI by ActionPerformerViewInterface view, and remembers
	 * representation of hte file in model object.
	 * 
	 * @param view
	 *            GUI view to dispaly loaded text.
	 * @param errorDisplayer
	 *            GUI view to display error popups.
	 * @param file
	 *            File to load.
	 */
	public void loadOrginalFile(ActionPerformerViewInterface view, ErrorDisplayInterface errorDisplayer, File file) {
		try {
			currentFileModel.setUpOrginalFile(file);
			String orginalFileString = currentFileModel.getOrginalFileString();
			orginalFileString = actualEncryptor.preprocessingEncryptToDisplay(orginalFileString);
			view.showOrginalFile(orginalFileString);
			view.showEncryptedFile("");
		} catch (FileNotFoundException e) {
			errorDisplayer.noSuchFileWarning(e.getMessage());
		} catch (IOException e) {
			errorDisplayer.readingFileProblemWarning(e.getMessage());
		}
	}

	/**
	 * Method to load encrypted text from given file. Loads String from file and
	 * displays it in GUI by ActionPerformerViewInterface view, and remembers
	 * representation of hte file in model object.
	 * 
	 * @param view
	 *            GUI view to dispaly loaded text.
	 * @param errorDisplayer
	 *            GUI view to display error popups.
	 * @param file
	 *            File to load.
	 */
	public void loadEncryptedFile(ActionPerformerViewInterface view, ErrorDisplayInterface errorDisplayer, File file) {
		try {
			currentFileModel.setUpEncryptedFile(file);
			String encryptedFileString = currentFileModel.getEncryptedFileString();
			encryptedFileString = actualEncryptor.preprocessingDecryptToDisplay(encryptedFileString);
			view.showEncryptedFile(encryptedFileString);
			view.showOrginalFile("");
		} catch (FileNotFoundException e) {
			errorDisplayer.noSuchFileWarning(e.getMessage());
		} catch (IOException e) {
			errorDisplayer.readingFileProblemWarning(e.getMessage());
		}
	}

	/**
	 * Method called when encrypt button pushed.
	 * 
	 * @param view
	 *            GUI view to dispaly encrypted text.
	 * @param errorDisplayer
	 *            GUI view to display error popups.
	 * @param outFile
	 *            File to save encrypted text.
	 */
	public void encrypt(ActionPerformerViewInterface view, ErrorDisplayInterface errorDisplayer, File outFile) {
		EncryptorReturn encrypted;
		try {
			encrypted = actualEncryptor.encrypt(currentFileModel.getOrginalFileString());
			currentFileModel.setUpEncryptedFile(outFile);
			currentFileModel.saveToFileEncryptedString(encrypted.getToSaveInFIle());
			view.showEncryptedFile(encrypted.getToDisplay());
		} catch (CustomizeEncryptorException e) {
			errorDisplayer.customizeEncryptorWarning(e.getMessage());
		} catch (FileNotFoundException e) {
			errorDisplayer.noSuchFileWarning(e.getMessage());
		} catch (IOException e) {
			errorDisplayer.readingFileProblemWarning(e.getMessage());
		}
	}

	/**
	 * Method called when decrypt button pushed.
	 * 
	 * @param view
	 *            GUI view to dispaly decrypted text.
	 * @param errorDisplayer
	 *            GUI view to display error popups.
	 * @param outFile
	 *            File to save decrypted text.
	 */
	public void decrypt(ActionPerformerViewInterface view, ErrorDisplayInterface errorDisplayer, File outFile) {
		EncryptorReturn orginal;
		try {
			orginal = actualEncryptor.decrytp(currentFileModel.getEncryptedFileString());
			currentFileModel.setUpOrginalFile(outFile);
			currentFileModel.saveToFileOrginalString(orginal.getToSaveInFIle());
			view.showOrginalFile(orginal.getToDisplay());
		} catch (CustomizeEncryptorException e) {
			errorDisplayer.customizeEncryptorWarning(e.getMessage());
		} catch (FileNotFoundException e) {
			errorDisplayer.noSuchFileWarning(e.getMessage());
		} catch (IOException e) {
			errorDisplayer.readingFileProblemWarning(e.getMessage());
		}
	}

	/**
	 * Getter for EncrypterChooser object given in constructor.
	 * 
	 * @return EncrypterChooser object given in constructor.
	 */
	public EncryptorChooser getEncryptorChooser() {
		return encryptoChooser;
	}

	/**
	 * Setter for actually choosed encryptor.
	 * 
	 * @param selectedEncryptor
	 *            Encryptor to set.
	 */
	public void setChoosedEncryptor(EncryptorInterface selectedEncryptor) {
		this.actualEncryptor = selectedEncryptor;
	}

	/**
	 * Getter for actually choosed encryptor.
	 * 
	 * @return Set encryptor.
	 */
	public EncryptorInterface getChoosedEncryptor() {
		return actualEncryptor;
	}

}
