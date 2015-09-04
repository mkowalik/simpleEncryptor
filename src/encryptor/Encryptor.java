package encryptor;
import java.io.File;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

public interface Encryptor {

	public String encrypt(String in);
	public String decrytp(String in);
	
	public JDialog getParametersWidget(JFrame master);
	
	public void setParameters(Object[] parameters);
	
}
