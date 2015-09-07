package encryptor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class XOREncryptor implements Encryptor {
	
	private class PasswordXORer {
		
		private String password;
		private int actPointer = 0;

		public PasswordXORer(String password) {
			this.password = password;
		}
		
		char getNext6Bits(char toXOR){
			
			while (actPointer/16 >= password.length()) actPointer -= (password.length() * 16);
			int actPointerChar = actPointer/16;
			int actPointerBit = actPointer%16;
			
			char actPassChar = password.charAt(actPointerChar);
			System.out.println("First actPassChar: " + (int)actPassChar);
//			actPassChar = (char) (actPassChar >>> (10-actPointerBit));
			
			if (10-actPointerBit<0){
				actPassChar = (char) (actPassChar << (actPointerBit-10));
				int actPointerCharNext = (actPointerChar + 1) % password.length();
				char actPassCharNext = password.charAt(actPointerCharNext);
				actPassCharNext = (char) (actPassCharNext >>> (10-actPointerBit+16));
				actPassChar |= actPassCharNext;
			} else {
				actPassChar = (char) (actPassChar >>> (10-actPointerBit));
			}
			
			System.out.println("toXOR:" + (int)toXOR);
			System.out.println("actPassChar:" + (int)actPassChar);
			System.out.println("actPointerChar:" + actPointerChar);
			System.out.println("actPointerBit:" + actPointerBit);
			
			actPointer += 6;			
			
			return (char) ((toXOR ^ actPassChar) & 0x003F);
		}
		
	}
	
	private static final String encryptorName = "XOR Encryptor";
	private JDialog customizeEncryptorDialog = null;
	private String password = "";
	
	private void prepareParameterWidget(JFrame owner){

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
	
	private static String removeForbiddenSings(String in){
		StringBuilder builder = new StringBuilder("");
		for (int i = 0; i < in.length(); i++) {
			builder.append(mapChars(in.charAt(i)));
		}
		return builder.toString();
	}
	
	private static char compressChars(char c){
		if (c == ' ')
			return 1;
		if (c >= '0' && c <= '9')
			return (char) (2 + (c-'0'));
		if (c >= 'A' && c <= 'Z')
			return (char) (12 + (c-'A'));
		if (c >= 'a' && c <= 'z')
			return (char) (38 + (c-'a'));
		return 1;
	}
	
	private static char mapChars(char c){
		if (c == ' ' || (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
			return c;
		else
			return ' ';
	}
	
	private static char decompressChars(char cc){
		int c = (int) cc;
		if (c == 1)
			return ' ';
		if (c >=2 && c <=11){
			return (char) (c + '0' - 2);
		}
		if (c >= 12 && c <= 37){
			return (char) (c + 'A' - 12);	
		}
		if (c >= 38 && c <= 63){
			return (char) (c + 'a' - 38);
		}
		return ' ';
	}
	
	public static EncryptorReturn XORWithPassword6Bit(String properIn, String password, PasswordXORer xorer){
		
		StringBuilder builderToSave = new StringBuilder();
		StringBuilder builderToDisplay = new StringBuilder();
		
		int positionInNextChar = 0;
		char nextChar = 0;
		
		for (int i=0; i<properIn.length(); i++){
			char actChar = properIn.charAt(i);
			char actCharCompressed = compressChars(actChar);
			char actCharXORed = xorer.getNext6Bits(actCharCompressed);
			char actCharXORedDecompressed = decompressChars(actCharXORed);
			builderToDisplay.append(actCharXORedDecompressed);
			
			System.out.println((int)actCharXORed);
			
//			nextChar |= ((0x3F & actCharCompressed)<<(10-positionInNextChar));
			if (10-positionInNextChar < 0){
				nextChar |= ((0x3F & actCharXORed)>>>(positionInNextChar-10));
				builderToSave.append(nextChar);
				nextChar = 0;
				nextChar |= actCharXORed<<(16 + 10-positionInNextChar);
			} else {
				nextChar |= ((0x3F & actCharXORed)<<(10-positionInNextChar));
			}
			positionInNextChar = (positionInNextChar+6)%16;
		}
		
		if (positionInNextChar>0){
			builderToSave.append(nextChar);
		}
		
		return new EncryptorReturn(builderToDisplay.toString(), builderToSave.toString());
	}

	public EncryptorReturn encrypt(String in) throws CustomizeEncryptorException {
		if (password == "") throw new CustomizeEncryptorException("Empty password");
		for (int i=0; i<in.length(); i++){
			System.out.println((int)in.charAt(i));
		}
		System.out.println("DONE IN");
		String properIn = preprocessingEncryptToDisplay(in);
		for (int i=0; i<properIn.length(); i++){
			System.out.println((int)properIn.charAt(i));
		}
		System.out.println("DONE PRPOERIN");
		PasswordXORer xorer = new PasswordXORer(password);
		return XORWithPassword6Bit(properIn, password, xorer);
	}
	
	public EncryptorReturn decrytp(String in) throws CustomizeEncryptorException{
		if (password == "") throw new CustomizeEncryptorException("Empty password");
		
		
		in = decompressString(in);
		StringBuilder builder = new StringBuilder();
		
		PasswordXORer xorer = new PasswordXORer(password);
		for (int i=0; i<in.length(); i++){
			char actChar = in.charAt(i);
			actChar = xorer.getNext6Bits(actChar);
			actChar = decompressChars(actChar);
			builder.append(actChar);
		}
		
		return new EncryptorReturn(builder.toString(), builder.toString());
		
	}

	@Override
	public JDialog getParametersWidget(JFrame master) {
		
		if (customizeEncryptorDialog==null)
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

	
	private static String decompressString(String in){
		
		StringBuilder builder = new StringBuilder();
		char actChar = 0;
		
		for (int i=0; i<in.length() * 16; i+=6){
			char inChar = in.charAt(i/16);
//			actChar = (char) (inChar >>> (10 - (i%16)));
			if (10 - (i%16) < 0){
				actChar = (char) (inChar << ((i%16) - 10));
				inChar = in.charAt((i/16) + 1);
				actChar |= (char)(inChar >>> (10 - (i%16) + 16));
			} else {
				actChar = (char) (inChar >>> (10 - (i%16)));
			}
			builder.append(actChar);
		}
		
		return builder.toString();
		
	}
	
	@Override
	public String preprocessingDecryptToDisplay(String in) {
		
		in = decompressString(in);
		
		StringBuilder builder = new StringBuilder();
		
		for (int i=0; i<in.length(); i++){
			builder.append(decompressChars(in.charAt(i)));
		}
		
		return builder.toString();
	}

}
