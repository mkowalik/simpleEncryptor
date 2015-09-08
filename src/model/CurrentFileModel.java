package model;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * Class representing encrypting/decrypting file model. Contains representation
 * of files (encrypted and decrypted) in memory and handlers to them.
 * 
 * @author kowalik
 *
 */
public class CurrentFileModel {

	String orginalFileString = null;
	String encryptedFileString = null;
	File orginalFile = null;
	File encryptedFile = null;

	/**
	 * Sets up path to orginal or decrypted file.
	 * 
	 * @param orginalFile
	 *            Path to set.
	 */
	public void setUpOrginalFile(File orginalFile) {
		this.orginalFile = orginalFile;
		this.orginalFileString = null;
	}

	/**
	 * Sets up path to encrypted file.
	 * 
	 * @param encryptedFile
	 *            Path to set.
	 */
	public void setUpEncryptedFile(File encryptedFile) {
		this.encryptedFile = encryptedFile;
		this.encryptedFileString = null;
	}

	/**
	 * Assistant static function which reads from memory to String file char by
	 * char and returns representation of file as it is without changing char
	 * encryption.
	 * 
	 * @param file
	 *            File path to read.
	 * @return String with file representation.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static String readBinaryFileToString(File file) throws FileNotFoundException, IOException {
		DataInputStream stream = new DataInputStream(new FileInputStream(file));
		StringBuilder builder = new StringBuilder();
		while (stream.available() >= 2) {
			char c = stream.readChar();
			builder.append(c);
		}
		stream.close();
		return builder.toString();
	}

	/**
	 * Assistant static function which reads from memory string as Unicode.
	 * 
	 * @param file
	 *            File path to read.
	 * @return String with file representation.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static String readTextFileToString(File file) throws FileNotFoundException, IOException {
		FileReader reader = new FileReader(file);
		StringBuilder builder = new StringBuilder();
		char[] buffer = new char[16];
		int read = 0;
		while ((read = reader.read(buffer)) >= 0) {
			builder.append(buffer, 0, read);
		}
		reader.close();
		return builder.toString();
	}

	/**
	 * Assistant static function to save binary string to file. Reads string
	 * char by char and saves it as it is in bits.
	 * 
	 * @param text
	 *            String to save in file.
	 * @param file
	 *            File handler where to save.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void saveBinaryStringToFile(String text, File file) throws FileNotFoundException, IOException {
		DataOutputStream stream = new DataOutputStream(new FileOutputStream(file));
		stream.writeChars(text);
		stream.close();
	}

	/**
	 * Assistant static function to save text file using Unicode representation.
	 * 
	 * @param text
	 *            String to save in file
	 * @param file
	 *            File handler where to save
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void saveTextStringToFile(String text, File file) throws FileNotFoundException, IOException {
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
		writer.write(text);
		writer.close();
	}

	/**
	 * Returns String representation of the file. Returns decrypted or orignal
	 * string.
	 * 
	 * @return String with orginal text.
	 * @throws IOException
	 */
	public String getOrginalFileString() throws IOException {
		if (orginalFileString == null)
			orginalFileString = readTextFileToString(orginalFile);
		return orginalFileString;
	}

	/**
	 * Returns String representation of the file. Return encrypted string.
	 * 
	 * @return String with encrypted text.
	 * @throws IOException
	 */
	public String getEncryptedFileString() throws IOException {
		if (encryptedFileString == null)
			encryptedFileString = readBinaryFileToString(encryptedFile);
		return encryptedFileString;
	}

	/**
	 * Saves holding encrypted string to file and remembers to string
	 * representation text in arguments. Uses binary representation of files.
	 * 
	 * @param text
	 *            String to remember and save in file.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void saveToFileEncryptedString(String text) throws FileNotFoundException, IOException {
		encryptedFileString = text;
		saveBinaryStringToFile(text, encryptedFile);
	}

	/**
	 * Saves holding decrypted or orginale string to file and remembers to
	 * string representation text in arguments. Uses unicode representation of
	 * files.
	 * 
	 * @param text
	 *            String to remember and save in file.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void saveToFileOrginalString(String text) throws FileNotFoundException, IOException {
		orginalFileString = text;
		saveTextStringToFile(text, orginalFile);
	}

}
