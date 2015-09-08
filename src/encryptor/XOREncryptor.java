package encryptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Implementation of EncryptorInterface. Simply makes XOR of concatenated with
 * itself many times given password with encrypting text.
 * 
 * @author kowalik
 *
 */
public class XOREncryptor implements EncryptorInterface {

	/**
	 * Assistant class for providing and XORing 6-bit parts of the password.
	 * 
	 * @author kowalik
	 *
	 */
	private class PasswordXORer {

		private String password;
		private int actPointer = 0;

		/**
		 * Constructor of the class.
		 * 
		 * @param password
		 *            Password given by the user.
		 */
		public PasswordXORer(String password) {
			this.password = password;
		}

		/**
		 * Gives char (16-bit) with only 6 non-zero last bits (possible). It is
		 * XORed next 6 bits of the password and given compressed to 6-bit char.
		 * 
		 * @param toXOR
		 *            Char with only 6 last non-zero bits to XOR with next 6
		 *            bits of the password.
		 * @return Char with only 6 last non-zero bits XORed as written above.
		 */
		char getNext6Bits(char toXOR) {

			while (actPointer / 16 >= password.length())
				actPointer -= (password.length() * 16);
			int actPointerChar = actPointer / 16;
			int actPointerBit = actPointer % 16;

			char actPassChar = password.charAt(actPointerChar);
			// actPassChar = (char) (actPassChar >>> (10-actPointerBit));

			if (10 - actPointerBit <= 0) {
				actPassChar = (char) (actPassChar << (actPointerBit - 10));
				int actPointerCharNext = (actPointerChar + 1) % password.length();
				char actPassCharNext = password.charAt(actPointerCharNext);
				actPassCharNext = (char) (actPassCharNext >>> (10 - actPointerBit + 16));
				actPassChar |= actPassCharNext;
			} else {
				actPassChar = (char) (actPassChar >>> (10 - actPointerBit));
			}

			actPointer += 6;

			return (char) ((toXOR ^ actPassChar) & 0x003F);
		}

	}

	private static final String encryptorName = "XOR Encryptor";
	private JDialog customizeEncryptorDialog = null;
	private String password = "";

	/**
	 * Prepares parameter widget for this encryptor.
	 * 
	 * @param owner
	 *            Master JFrame where widget will be dispalyed.
	 */
	private void prepareParameterWidget(JFrame owner) {

		customizeEncryptorDialog = new JDialog(owner, true);
		customizeEncryptorDialog.setSize(320, 180);

		JTextField passField = new JTextField();
		passField.setBounds(20, 40, 280, 20);
		customizeEncryptorDialog.add(passField);

		JLabel passLabel = new JLabel("Password:");
		passLabel.setBounds(20, 20, 280, 20);
		customizeEncryptorDialog.add(passLabel);

		JButton OKButton = new JButton("OK");
		OKButton.setBounds(20, 70, 60, 30);
		OKButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				password = passField.getText();
				customizeEncryptorDialog.dispose();
			}
		});

		customizeEncryptorDialog.add(OKButton);
		customizeEncryptorDialog.setLayout(null);

	}

	/**
	 * Removes signs which are not A-Z, a-z, 0-9 and ' ' and replaces it by ' '.
	 * 
	 * @param in
	 *            String to remove forbidden signs.
	 * @return Proper string.
	 */
	private static String removeForbiddenSings(String in) {
		StringBuilder builder = new StringBuilder("");
		for (int i = 0; i < in.length(); i++) {
			builder.append(mapChars(in.charAt(i)));
		}
		return builder.toString();
	}

	/**
	 * Compress chars to with last 6 non-zero bits. Space (' ') is mapped to 1,
	 * 0-9 are mapped to 2-11, A-Z are mapped to 12-37 and a-z are mapped to
	 * 38-63.
	 * 
	 * @param c
	 *            Char to map
	 * @return Char mapped to 6-bit.
	 */
	private static char compressChars(char c) {
		if (c == ' ')
			return 1;
		if (c >= '0' && c <= '9')
			return (char) (2 + (c - '0'));
		if (c >= 'A' && c <= 'Z')
			return (char) (12 + (c - 'A'));
		if (c >= 'a' && c <= 'z')
			return (char) (38 + (c - 'a'));
		return 1;
	}

	/**
	 * If char is in set: ' ', 0-9, A-Z, a-b then returns itself, else returns '
	 * '.
	 * 
	 * @param c
	 *            Char to map.
	 * @return Mapped char as above.
	 */
	private static char mapChars(char c) {
		if (c == ' ' || (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
			return c;
		else
			return ' ';
	}

	/**
	 * Makes reverse operation to compressChars function.
	 * 
	 * @param cc
	 *            Char to decompress.
	 * @return Decompressed char.
	 */
	private static char decompressChars(char cc) {
		int c = (int) cc;
		if (c == 1)
			return ' ';
		if (c >= 2 && c <= 11) {
			return (char) (c + (int) '0' - 2);
		}
		if (c >= 12 && c <= 37) {
			return (char) (c + (int) 'A' - 12);
		}
		if (c >= 38 && c <= 63) {
			return (char) (c + (int) 'a' - 38);
		}
		return ' ';
	}

	/**
	 * Static method which prepares two strings: string to save in file and
	 * sting to display in aplication. Signs in file are compressed to 6-bit
	 * signs and then XORed with next 6-bit parts of the password.
	 * 
	 * @param properIn
	 *            String to XOR with password without forbidden signs.
	 * @param xorer
	 *            PasswordXORer class instance.
	 * @return Assistant class EncryptorReturn object with String to display and
	 *         String to save in file.
	 */
	public static EncryptorReturn XORWithPassword6Bit(String properIn, PasswordXORer xorer) {

		StringBuilder builderToSave = new StringBuilder();
		StringBuilder builderToDisplay = new StringBuilder();

		int positionInNextChar = 0;
		char nextChar = 0;

		for (int i = 0; i < properIn.length(); i++) {
			char actChar = properIn.charAt(i);
			char actCharCompressed = compressChars(actChar);
			char actCharXORed = xorer.getNext6Bits(actCharCompressed);
			char actCharXORedDecompressed = decompressChars(actCharXORed);
			builderToDisplay.append(actCharXORedDecompressed);

			// nextChar |= ((0x3F &
			// actCharCompressed)<<(10-positionInNextChar));
			if (10 - positionInNextChar <= 0) { // JAK COS TO TU NIE BYLO
												// ROWNOSCI
				nextChar |= ((actCharXORed) >>> (positionInNextChar - 10));
				builderToSave.append(nextChar);
				nextChar = 0;
				nextChar |= actCharXORed << (16 + 10 - positionInNextChar);
			} else {
				nextChar |= ((0x3F & actCharXORed) << (10 - positionInNextChar));
			}
			positionInNextChar = (positionInNextChar + 6) % 16;
		}

		if (positionInNextChar > 0) {
			builderToSave.append(nextChar);
		}

		return new EncryptorReturn(builderToDisplay.toString(), builderToSave.toString());
	}

	@Override
	public EncryptorReturn encrypt(String in) throws CustomizeEncryptorException {
		if (password == null || password == "")
			throw new CustomizeEncryptorException("Empty password");
		String properIn = preprocessingEncryptToDisplay(in);
		PasswordXORer xorer = new PasswordXORer(password);
		return XORWithPassword6Bit(properIn, xorer);
	}

	@Override
	public EncryptorReturn decrytp(String in) throws CustomizeEncryptorException {
		if (password == null || password == "")
			throw new CustomizeEncryptorException("Empty password");

		in = decompressString(in);
		StringBuilder builder = new StringBuilder();

		PasswordXORer xorer = new PasswordXORer(password);
		for (int i = 0; i < in.length(); i++) {
			char actChar = in.charAt(i);
			actChar = xorer.getNext6Bits(actChar);
			actChar = decompressChars(actChar);
			builder.append(actChar);
		}

		return new EncryptorReturn(builder.toString(), builder.toString());

	}

	@Override
	public JDialog getParametersWidget(JFrame master) {

		if (customizeEncryptorDialog == null)
			prepareParameterWidget(master);
		return customizeEncryptorDialog;

	}

	@Override
	public String toString() {
		return encryptorName;
	}

	@Override
	public String preprocessingEncryptToDisplay(String in) {
		return removeForbiddenSings(in);
	}

	/**
	 * Static method which takes 6-bit parts of the in string and decompresses
	 * all signs as described in decompressChars method.
	 * 
	 * @param in
	 *            String to decompress.
	 * @return Decomrpessed string.
	 */
	private static String decompressString(String in) {

		StringBuilder builder = new StringBuilder();
		char actChar = 0;

		for (int i = 0; i + 6 < in.length() * 16; i += 6) {
			char inChar = in.charAt(i / 16);
			// actChar = (char) (inChar >>> (10 - (i%16)));
			if (10 - (i % 16) < 0 && ((i / 16) + 1) < in.length()) {
				actChar = (char) (inChar << ((i % 16) - 10));
				inChar = in.charAt((i / 16) + 1);
				actChar |= (char) (inChar >>> (10 - (i % 16) + 16));
			} else {
				actChar = (char) (inChar >>> (10 - (i % 16)));
			}
			builder.append((char) (actChar & 0x3F));
		}
		return builder.toString();

	}

	@Override
	public String preprocessingDecryptToDisplay(String in) {

		in = decompressString(in);

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < in.length(); i++) {
			builder.append(decompressChars(in.charAt(i)));
		}

		return builder.toString();
	}

}
