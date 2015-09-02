package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class XOREncryptor implements Encryptor {
	
	private static final String encryptorName = "XOR Encryptor";
	private JDialog customizeEncryptorDialog = null;
	
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
				customizeEncryptorDialog.dispose();
			}
		});
		
		customizeEncryptorDialog.add(OKButton);
		customizeEncryptorDialog.setLayout(null);
		
	}

	@Override
	public void encrypt(File in, File out, Object[] parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public void decrytp(File in, File out, Object[] parameters) {
		// TODO Auto-generated method stub

	}

	@Override
	public Iterator<Byte> getDecryptedIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Byte> getEncryptedIterator() {
		// TODO Auto-generated method stub
		return null;
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

}
