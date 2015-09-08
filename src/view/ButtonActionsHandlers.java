package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import controller.ActionPerformerViewInterface;
import controller.ActionsController;
import controller.ErrorDisplayInterface;
import encryptor.EncryptorInterface;

/**
 * Outer class for ActionListener's for all bvuttons displayed in main view.
 * 
 * @author kowalik
 *
 */
public class ButtonActionsHandlers {

	private ActionsController actionsController;

	/**
	 * Constructor of the class.
	 * 
	 * @param actionsController
	 *            ActionsController produced in Main class.
	 */
	public ButtonActionsHandlers(ActionsController actionsController) {
		this.actionsController = actionsController;
	}

	/**
	 * Class for handling actions on JComboBox for choosing encryptors.
	 * 
	 * @author kowalik
	 *
	 */
	public class ChooserBoxListener implements ActionListener {

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			actionsController.setChoosedEncryptor(
					(EncryptorInterface) (((JComboBox<EncryptorInterface>) e.getSource()).getSelectedItem()));
		}

	}

	/**
	 * Class for handling action of pushing 'Customize Encryptor'.
	 * 
	 * @author kowalik
	 *
	 */
	public class CustomizeEncryptorButtonListener implements ActionListener {

		JFrame mainFrame;

		/**
		 * Constructor of the class.
		 * 
		 * @param mainFrame
		 *            Master view for displaying JDialog widget for customizing
		 *            encryptor.
		 */
		public CustomizeEncryptorButtonListener(JFrame mainFrame) {
			this.mainFrame = mainFrame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			EncryptorInterface enc = actionsController.getChoosedEncryptor();
			JDialog widget = enc.getParametersWidget(mainFrame);
			widget.setLocationRelativeTo(mainFrame);
			widget.setVisible(true);
		}

	}

	/**
	 * Class for handling action of pushing 'Load orgnial file' button.
	 * 
	 * @author kowalik
	 *
	 */
	public class LoadOriginalFileButtonListener implements ActionListener {

		private JFrame mainFrame;
		private ActionPerformerViewInterface actionPerformer;
		private ErrorDisplayInterface errorDisplayer;

		/**
		 * Constructor of the class.
		 * 
		 * @param mainFrame
		 *            Main frame.
		 * @param actionPerformer
		 *            ActionPerformer object constructed in Main class.
		 * @param errorDisplayer
		 *            ErrorDisplayer object responsible for displaying error
		 *            widgets. Probably MainView.
		 */
		public LoadOriginalFileButtonListener(JFrame mainFrame, ActionPerformerViewInterface actionPerformer,
				ErrorDisplayInterface errorDisplayer) {
			this.mainFrame = mainFrame;
			this.actionPerformer = actionPerformer;
			this.errorDisplayer = errorDisplayer;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser chooser = new JFileChooser();

			if (chooser.showOpenDialog(mainFrame) != JFileChooser.APPROVE_OPTION)
				return;
			else {
				File file = chooser.getSelectedFile();
				actionsController.loadOrginalFile(actionPerformer, errorDisplayer, file);
			}
		}
	}

	/**
	 * Class for handling action of pushing 'Load encrypted file' button.
	 * 
	 * @author kowalik
	 *
	 */
	public class LoadEncryptedFileButtonListener implements ActionListener {

		private JFrame mainFrame;
		private ActionPerformerViewInterface actionPerformer;
		private ErrorDisplayInterface errorDisplayer;

		/**
		 * Constructor of the class.
		 * 
		 * @param mainFrame
		 *            Main frame.
		 * @param actionPerformer
		 *            ActionPerformer object constructed in Main class.
		 * @param errorDisplayer
		 *            ErrorDisplayer object responsible for displaying error
		 *            widgets. Probably MainView.
		 */
		public LoadEncryptedFileButtonListener(JFrame mainFrame, ActionPerformerViewInterface actionPerformer,
				ErrorDisplayInterface errorDisplayer) {
			this.mainFrame = mainFrame;
			this.actionPerformer = actionPerformer;
			this.errorDisplayer = errorDisplayer;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser chooser = new JFileChooser();

			if (chooser.showOpenDialog(mainFrame) != JFileChooser.APPROVE_OPTION)
				return;
			else {
				File file = chooser.getSelectedFile();
				actionsController.loadEncryptedFile(actionPerformer, errorDisplayer, file);
			}
		}
	}

	/**
	 * Class for handling action of pushing 'Encrypt file' button.
	 * 
	 * @author kowalik
	 *
	 */
	public class EncryptFileButtonListener implements ActionListener {

		private JFrame mainFrame;
		private ActionPerformerViewInterface actionPerformer;
		private ErrorDisplayInterface errorDisplayer;

		/**
		 * Constructor of the class.
		 * 
		 * @param mainFrame
		 *            Main frame.
		 * @param actionPerformer
		 *            ActionPerformer object constructed in Main class.
		 * @param errorDisplayer
		 *            ErrorDisplayer object responsible for displaying error
		 *            widgets. Probably MainView.
		 */
		public EncryptFileButtonListener(JFrame mainFrame, ActionPerformerViewInterface actionPerformer,
				ErrorDisplayInterface errorDisplayer) {
			this.mainFrame = mainFrame;
			this.actionPerformer = actionPerformer;
			this.errorDisplayer = errorDisplayer;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser chooser = new JFileChooser();

			if (chooser.showOpenDialog(mainFrame) != JFileChooser.APPROVE_OPTION)
				return;
			else {
				File file = chooser.getSelectedFile();
				actionsController.encrypt(actionPerformer, errorDisplayer, file);
			}

		}

	}

	/**
	 * Class for handling action of pushing 'Decrypt file' button.
	 * 
	 * @author kowalik
	 *
	 */
	public class DecryptFileButtonListener implements ActionListener {

		private JFrame mainFrame;
		private ActionPerformerViewInterface actionPerformer;
		private ErrorDisplayInterface errorDisplayer;

		/**
		 * Constructor of the class.
		 * 
		 * @param mainFrame
		 *            Main frame.
		 * @param actionPerformer
		 *            ActionPerformer object constructed in Main class.
		 * @param errorDisplayer
		 *            ErrorDisplayer object responsible for displaying error
		 *            widgets. Probably MainView.
		 */
		public DecryptFileButtonListener(JFrame mainFrame, ActionPerformerViewInterface actionPerformer,
				ErrorDisplayInterface errorDisplayer) {
			this.mainFrame = mainFrame;
			this.actionPerformer = actionPerformer;
			this.errorDisplayer = errorDisplayer;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser chooser = new JFileChooser();

			if (chooser.showOpenDialog(mainFrame) != JFileChooser.APPROVE_OPTION)
				return;
			else {
				File file = chooser.getSelectedFile();
				actionsController.decrypt(actionPerformer, errorDisplayer, file);
			}

		}
	}
}
