package view;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controller.ActionPerformerViewInterface;
import controller.ActionsController;
import controller.ErrorDisplayInterface;
import encryptor.EncryptorInterface;
import misc.Config;

/**
 * Assistant class for returning JPanel and containing it JTExtArea from one
 * method.
 * 
 * @author kowalik
 *
 */
final class FilePreviewRet {

	private JPanel panel;
	private JTextArea textArea;

	/**
	 * Saves arguments in private fields.
	 * 
	 * @param panel
	 *            JPanel to save.
	 * @param textArea
	 *            JTextArea to save.
	 */
	protected FilePreviewRet(JPanel panel, JTextArea textArea) {
		this.panel = panel;
		this.textArea = textArea;
	}

	/**
	 * Getter for saved JPanel.
	 * 
	 * @return saved JPanel.
	 */
	protected JPanel getPanel() {
		return panel;
	}

	/**
	 * Getter for saved JTextArea
	 * 
	 * @return save JTextArea
	 */
	protected JTextArea getTextArea() {
		return textArea;
	}

}

/**
 * Assistant class for returning JPanel and containing it JComboBox
 * <EncryptorInterface> from one method.
 * 
 * @author kowalik
 *
 */
final class EncryptorPanelRet {

	private JPanel panel;
	private JComboBox<EncryptorInterface> comboBox;

	/**
	 * Saves arguments in private fields.
	 * 
	 * @param panel
	 *            JPanel to save.
	 * @param comboBox
	 *            JComboBox<EncryptorInterface> to save.
	 */
	protected EncryptorPanelRet(JPanel panel, JComboBox<EncryptorInterface> comboBox) {
		this.panel = panel;
		this.comboBox = comboBox;
	}

	/**
	 * Getter for saved JPanel.
	 * 
	 * @return saved JPanel.
	 */
	protected JPanel getPanel() {
		return panel;
	}

	/**
	 * Getter for saved JComboBox<EncryptorInterface>.
	 * 
	 * @return saved JComboBox<EncryptorInterface>.
	 */
	protected JComboBox<EncryptorInterface> getComboBox() {
		return comboBox;
	}

}

/**
 * Main class responsible for GUI. This is main view holding two areas with
 * JTextArea displaying orginal and encrypted text, buttons for loading files,
 * encrypting/decrypting files. On the top ther is JComboBox for choosing
 * encryptor and button for customizing encryptor.
 * 
 * @author kowalik
 *
 */
public class MainView implements ActionPerformerViewInterface, ErrorDisplayInterface {

	private JFrame mainFrame = new JFrame();
	private ButtonActionsHandlers buttonActionsHandlers;
	private JTextArea orginalTextArea;
	private JTextArea encryptedTextArea;

	/**
	 * Private method for preparing JPanel with JTextArea for dispalying text
	 * and two buttons - one on the top, and second on the bottom. Returns
	 * assistant class with JPanel and containing JTextArea.
	 * 
	 * @param bottomButtonDescription
	 *            Description for button placed on the bottom. It's
	 *            encrypt/decrypt button.
	 * @param loadButtonDescription
	 *            Description for button placed on the top. Load file button.
	 * @param topLabelString
	 *            Label on the top of the JPanel.
	 * @param mainFrame
	 *            MainFrame where it will be placed returning JPanel
	 * @param loadButtonListener
	 *            ActionListener for button on the top.
	 * @param bottomButtonListener
	 *            ActionListener for button on the bottom.
	 * @return Returns assistant class with prepared JPanel and JTextArea in
	 *         this JPanel.
	 */
	private static FilePreviewRet prepareFilePreviewFrame(String bottomButtonDescription, String loadButtonDescription,
			String topLabelString, JFrame mainFrame, ActionListener loadButtonListener,
			ActionListener bottomButtonListener) {

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

	/**
	 * Private method for preparing JPanel with JComboBox for choosing wanted to
	 * use encryptor and button for running customizeWidget from encryptor
	 * implementation.
	 * 
	 * @param encryptorList
	 *            List of EncryporInterface for choosing proper encryptor.
	 * @param mainFrame
	 *            Main JFrame where it will be placed prepared JPanel.
	 * @param chooserBoxListener
	 *            ActionListener catching actions on placed JComboBox.
	 * @param custmizeEncryptorButtonListener
	 *            ActionListener catching actions on placed button.
	 * @return assistant class EncryptorPanelRet object with prepared JPanel and
	 *         JComboBox inside this JPanel.
	 */
	private static EncryptorPanelRet prepareEncryptorPanel(List<EncryptorInterface> encryptorList, JFrame mainFrame,
			ActionListener chooserBoxListener, ActionListener custmizeEncryptorButtonListener) {

		JPanel ret = new JPanel();
		ret.setSize(984, 80);
		ret.setLayout(null);

		JLabel encryptorLabel = new JLabel("Encryptor type");
		encryptorLabel.setBounds(0, 0, 984, 30);
		encryptorLabel.setVerticalAlignment(JLabel.TOP);

		JComboBox<EncryptorInterface> encryptorChooserBox = new JComboBox<EncryptorInterface>();
		encryptorChooserBox.setBounds(0, 20, 480, 20);
		for (EncryptorInterface enc : encryptorList) {
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

	/**
	 * Costructor of main GUI view. Prepares the JFrame to show and makes it
	 * visible. Contains Jpanel with encryptor chooser, and two areas for
	 * displaying encrypting and encrypted text with buttons for loading files
	 * and encrypting them.
	 * 
	 * @param buttonActionsHanlers
	 *            Object of ButtonActionsHandlers with ActionListener's for all
	 *            buttons.
	 * @param actionsController
	 *            Object of ActionsController handling all actions.
	 * @param encryptorsList
	 *            List of available encryptors registerd in EncryptorChooser
	 *            constructed in Main class.
	 */
	public MainView(ButtonActionsHandlers buttonActionsHanlers, ActionsController actionsController,
			List<EncryptorInterface> encryptorsList) {

		this.buttonActionsHandlers = buttonActionsHanlers;
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle(Config.appName);
		mainFrame.setSize(1024, 768);
		mainFrame.setLayout(null);

		mainFrame.setLocationRelativeTo(null);

		FilePreviewRet tmp = null;

		tmp = prepareFilePreviewFrame("Encrypt File", "Load original file", "Orginal text", mainFrame,
				buttonActionsHandlers.new LoadOriginalFileButtonListener(mainFrame, this, this),
				buttonActionsHanlers.new EncryptFileButtonListener(mainFrame, this, this));
		JPanel orginialPanel = tmp.getPanel();
		this.orginalTextArea = tmp.getTextArea();
		orginialPanel.setBounds(20, 115, 480, 585);
		mainFrame.add(orginialPanel);

		tmp = prepareFilePreviewFrame("Decrypt File", "Load encrypted file", "Encrypted text", mainFrame,
				buttonActionsHandlers.new LoadEncryptedFileButtonListener(mainFrame, this, this),
				buttonActionsHanlers.new DecryptFileButtonListener(mainFrame, this, this));
		JPanel encryptedPanel = tmp.getPanel();
		this.encryptedTextArea = tmp.getTextArea();
		encryptedPanel.setBounds(524, 115, 480, 585);
		mainFrame.add(encryptedPanel);

		EncryptorPanelRet tmp2 = null;
		tmp2 = prepareEncryptorPanel(encryptorsList, mainFrame, buttonActionsHanlers.new ChooserBoxListener(),
				buttonActionsHanlers.new CustomizeEncryptorButtonListener(mainFrame));
		JPanel encryptorPanel = tmp2.getPanel();
		encryptorPanel.setBounds(20, 20, 984, 80);
		actionsController.setChoosedEncryptor((EncryptorInterface) tmp2.getComboBox().getSelectedItem());
		mainFrame.add(encryptorPanel);

		mainFrame.setVisible(true);

	}

	@Override
	public void showOrginalFile(String file) {
		orginalTextArea.setText(file);
	}

	@Override
	public void showEncryptedFile(String file) {
		encryptedTextArea.setText(file);
	}

	@Override
	public void noSuchFileWarning(String message) {
		JOptionPane.showMessageDialog(mainFrame, "No such file.\n" + message);
	}

	@Override
	public void readingFileProblemWarning(String message) {
		JOptionPane.showMessageDialog(mainFrame, "Problem while reading file occured.\n" + message);
	}

	@Override
	public void customizeEncryptorWarning(String message) {
		JOptionPane.showMessageDialog(mainFrame, "Please, customize choosed encryptor.\n" + message);
	}

}
