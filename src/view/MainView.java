package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import misc.Config;
import model.EncryptorChooser;
import model.Encryptor;
import model.XOREncryptor;

public class MainView {
	
	private JFrame mainFrame = new JFrame();
	
	private static JPanel prepareFilePreviewFrame(String bottomButtonDescription, String loadButtonDescription, String topLabelString){
		
		JPanel ret = new JPanel();
		ret.setSize(520, 585);
		ret.setLayout(null);
		
		JLabel topLabel = new JLabel(topLabelString);
		topLabel.setBounds(0, 0, 480, 30);
		topLabel.setVerticalAlignment(JLabel.CENTER);
		
		JButton loadButton = new JButton(loadButtonDescription);
		loadButton.setBounds(300, 0, 180, 30);
		loadButton.setHorizontalAlignment(JButton.CENTER);
		
		JTextArea textFrame = new JTextArea();
		textFrame.setLineWrap(true);
		textFrame.setAutoscrolls(true);
		
		JScrollPane decodedFrameScrolled = new JScrollPane(textFrame);
		decodedFrameScrolled.setBounds(0, 35, 480, 510);
		decodedFrameScrolled.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton bottomButton = new JButton(bottomButtonDescription);
		bottomButton.setBounds(330, 555, 150, 30);
		bottomButton.setHorizontalAlignment(JButton.CENTER);

		ret.add(topLabel);
		ret.add(decodedFrameScrolled);
		ret.add(loadButton);
		ret.add(bottomButton);
		
		return ret;
		
	}
	
	private static JPanel prepareEncryptorPanel(EncryptorChooser encryptorChooser, JFrame mainFrame){
		
		JPanel ret = new JPanel();
		ret.setSize(984, 80);
		ret.setLayout(null);
		
		JLabel encryptorLabel = new JLabel("Encryptor type");
		encryptorLabel.setBounds(0, 0, 984, 30);
		encryptorLabel.setVerticalAlignment(JLabel.TOP);
		
		JComboBox<Encryptor> encryptorChooserBox = new JComboBox<Encryptor>();
		encryptorChooserBox.setBounds(0, 20, 480, 20);
		
		for (Encryptor enc: encryptorChooser.getEncryptorList()){
			encryptorChooserBox.addItem(enc);
		}		
		
		JButton customizeEncryptorButton = new JButton("Customize Encryptor");
		customizeEncryptorButton.setBounds(0, 50, 180, 30);
		customizeEncryptorButton.setHorizontalAlignment(JButton.CENTER);
		customizeEncryptorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Encryptor enc = (Encryptor)encryptorChooserBox.getSelectedItem();
				JDialog widget = enc.getParametersWidget(mainFrame);
				widget.setLocationRelativeTo(mainFrame);
				widget.setVisible(true);
				
			}
		});
		
		
		ret.add(encryptorChooserBox);
		ret.add(customizeEncryptorButton);
		ret.add(encryptorLabel);

		return ret;
		
	}
	
	public MainView(EncryptorChooser encryptorChooser){
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle(Config.appName);
		mainFrame.setSize(1024, 768);
		mainFrame.setLayout(null);
		
		mainFrame.setLocationRelativeTo(null);
		
		JPanel orginialPanel = prepareFilePreviewFrame("Encrypt File", "Load original file", "Orginal text");
		orginialPanel.setBounds(20, 115, 480, 585);
		mainFrame.add(orginialPanel);
		
		JPanel decryptedPanel = prepareFilePreviewFrame("Decrypt File", "Load encrypted file", "Encrypted text");
		decryptedPanel.setBounds(524, 115, 480, 585);
		mainFrame.add(decryptedPanel);

		JPanel encryptorPanel = prepareEncryptorPanel(encryptorChooser, mainFrame);
		encryptorPanel.setBounds(20, 20, 984, 80);
		mainFrame.add(encryptorPanel);
		
		

		


		
		mainFrame.setVisible(true);
		
	}

}
