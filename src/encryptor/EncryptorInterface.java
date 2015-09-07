package encryptor;
import javax.swing.JDialog;
import javax.swing.JFrame;

public interface EncryptorInterface {

	public EncryptorReturn encrypt(String in) throws CustomizeEncryptorException;
	public EncryptorReturn decrytp(String in) throws CustomizeEncryptorException;
	
	public String preprocessingEncryptToDisplay(String in);
	public String preprocessingDecryptToDisplay(String in);
	
	public JDialog getParametersWidget(JFrame master);
	
}
