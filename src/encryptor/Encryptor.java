package encryptor;
import javax.swing.JDialog;
import javax.swing.JFrame;

public interface Encryptor {

	public EncryptorReturn encrypt(String in) throws CustomizeEncryptorException;
	public EncryptorReturn decrytp(String in) throws CustomizeEncryptorException;
	
	public String preprocessingEncryptToDisplay(String in);
	public String preprocessingDecryptToDisplay(String in);
	
	public JDialog getParametersWidget(JFrame master);
	
}
