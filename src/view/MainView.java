package view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.ActionPerformerViewInterface;
import controller.ActionsController;
import encryptor.Encryptor;
import misc.Config;


final class FilePreviewRet {
	
	private JPanel panel;
	private JTextArea textArea;
	
	protected FilePreviewRet(JPanel panel, JTextArea textArea) {
		this.panel = panel;
		this.textArea = textArea;
	}
	
	protected JPanel getPanel(){
		return panel;
	}
	
	protected JTextArea getTextArea(){
		return textArea;
	}
	
}

final class EncryptorPanelRet {
	
	private JPanel panel;
	private JComboBox<Encryptor> comboBox;
	
	protected EncryptorPanelRet(JPanel panel, JComboBox<Encryptor> comboBox) {
		this.panel = panel;
		this.comboBox = comboBox;
	}
	
	protected JPanel getPanel(){
		return panel;
	}
	
	protected JComboBox<Encryptor> getComboBox(){
		return comboBox;
	}
	
}

public class MainView implements ActionPerformerViewInterface {
	
	private JFrame mainFrame = new JFrame();
	private ButtonActionsHandlers buttonActionsHandlers;
	private JTextArea orginalTextArea;
	private JTextArea encryptedTextArea;
	
	private static FilePreviewRet prepareFilePreviewFrame(String bottomButtonDescription, String loadButtonDescription, String topLabelString, JFrame mainFrame, ActionListener loadButtonListener, ActionListener bottomButtonListener){
		
		JPanel ret = new JPanel();
		ret.setSize(520, 585);
		ret.setLayout(null);
		
		JLabel topLabel = new JLabel(topLabelString);
		topLabel.setBounds(0, 0, 480, 30);
		topLabel.setVerticalAlignment(JLabel.CENTER);
		
		JButton loadButton = new JButton(loadButtonDescription);
		loadButton.setBounds(300, 0, 180, 30);
		loadButton.setHorizontalAlignment(JButton.CENTER);
		loadButton.addActionListener(loadButtonListener);
		
		JTextArea textFrame = new JTextArea();
		textFrame.setLineWrap(true);
		textFrame.setAutoscrolls(true);
		
		JScrollPane decodedFrameScrolled = new JScrollPane(textFrame);
		decodedFrameScrolled.setBounds(0, 35, 480, 510);
		decodedFrameScrolled.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JButton bottomButton = new JButton(bottomButtonDescription);
		bottomButton.setBounds(330, 555, 150, 30);
		bottomButton.setHorizontalAlignment(JButton.CENTER);
		bottomButton.addActionListener(bottomButtonListener);

		ret.add(topLabel);
		ret.add(decodedFrameScrolled);
		ret.add(loadButton);
		ret.add(bottomButton);
		
		return (new FilePreviewRet(ret, textFrame));
		
	}
	
	private static EncryptorPanelRet prepareEncryptorPanel(List<Encryptor> encryptorList, JFrame mainFrame, ActionListener chooserBoxListener, ActionListener custmizeEncryptorButtonListener){
		
		JPanel ret = new JPanel();
		ret.setSize(984, 80);
		ret.setLayout(null);
		
		JLabel encryptorLabel = new JLabel("Encryptor type");
		encryptorLabel.setBounds(0, 0, 984, 30);
		encryptorLabel.setVerticalAlignment(JLabel.TOP);
		
		JComboBox<Encryptor> encryptorChooserBox = new JComboBox<Encryptor>();
		encryptorChooserBox.setBounds(0, 20, 480, 20);
		for (Encryptor enc: encryptorList){
			encryptorChooserBox.addItem(enc);
		}	
		encryptorChooserBox.addActionListener(chooserBoxListener);
		
		JButton customizeEncryptorButton = new JButton("Customize Encryptor");
		customizeEncryptorButton.setBounds(0, 50, 180, 30);
		customizeEncryptorButton.setHorizontalAlignment(JButton.CENTER);
		customizeEncryptorButton.addActionListener(custmizeEncryptorButtonListener);
		
		
		ret.add(encryptorChooserBox);
		ret.add(customizeEncryptorButton);
		ret.add(encryptorLabel);

		return (new EncryptorPanelRet(ret, encryptorChooserBox));
		
	}
	
	public MainView(ButtonActionsHandlers buttonActionsHanlers, ActionsController actionsController, List<Encryptor> encryptorsList) {
		
		this.buttonActionsHandlers = buttonActionsHanlers;
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle(Config.appName);
		mainFrame.setSize(1024, 768);
		mainFrame.setLayout(null);
		
		mainFrame.setLocationRelativeTo(null);
		
		FilePreviewRet tmp = null;
		
		tmp = prepareFilePreviewFrame("Encrypt File", "Load original file", "Orginal text", mainFrame, buttonActionsHandlers.new LoadOriginalFileButtonListener(mainFrame, this), buttonActionsHanlers.new EncryptFileButtonListener(mainFrame, this));
		JPanel orginialPanel = tmp.getPanel();
		this.orginalTextArea = tmp.getTextArea();
		orginialPanel.setBounds(20, 115, 480, 585);
		mainFrame.add(orginialPanel);
		
		tmp = prepareFilePreviewFrame("Decrypt File", "Load encrypted file", "Encrypted text", mainFrame, buttonActionsHandlers.new LoadEncryptedFileButtonListener(mainFrame, this), buttonActionsHanlers.new DecryptFileButtonListener(mainFrame, this));
		JPanel encryptedPanel = tmp.getPanel();
		this.encryptedTextArea = tmp.getTextArea();
		encryptedPanel.setBounds(524, 115, 480, 585);
		mainFrame.add(encryptedPanel);
		
		EncryptorPanelRet tmp2 = null;
		tmp2 = prepareEncryptorPanel(encryptorsList, mainFrame, buttonActionsHanlers.new ChooserBoxListener(), buttonActionsHanlers.new CustomizeEncryptorButtonListener(mainFrame));
		JPanel encryptorPanel = tmp2.getPanel();
		encryptorPanel.setBounds(20, 20, 984, 80);
		actionsController.setChoosedEncryptor((Encryptor)tmp2.getComboBox().getSelectedItem());
		mainFrame.add(encryptorPanel);
		
		mainFrame.setVisible(true);
		
	}

	@Override
	public void showOrginalFile(String file) {
		orginalTextArea.setText(file);
	}

	@Override
	public void showEncryptedFile(String file) {
		System.out.println("PRE SHOW");
		encryptedTextArea.setText(file);
		System.out.println(file);
		System.out.println("DONE SHOW");
	}

}
