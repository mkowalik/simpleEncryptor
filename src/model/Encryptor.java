package model;
import java.io.File;
import java.util.Iterator;

import javax.swing.JDialog;
import javax.swing.JFrame;

public interface Encryptor {

	public void encrypt(File in, File out, Object[] parameters); //TODO moze zwracac status z zapisu
	public void decrytp(File in, File out, Object[] parameters);
	
	public Iterator<Byte> getDecryptedIterator();
	public Iterator<Byte> getEncryptedIterator();
	
	public JDialog getParametersWidget(JFrame master);
	
}
