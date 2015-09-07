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

public class ButtonActionsHandlers {

	private ActionsController actionsController;

	public ButtonActionsHandlers(ActionsController actionsController) { // TODO do zmiany
		this.actionsController = actionsController;
	}

	public class ChooserBoxListener implements ActionListener {

		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			actionsController
					.setChoosedEncryptor((EncryptorInterface) (((JComboBox<EncryptorInterface>) e.getSource()).getSelectedItem()));
		}

	}

	public class CustomizeEncryptorButtonListener implements ActionListener {

		JFrame mainFrame;

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

	public class LoadOriginalFileButtonListener implements ActionListener {

		private JFrame mainFrame;
		private ActionPerformerViewInterface actionPerformer;
		private ErrorDisplayInterface errorDisplayer;

		public LoadOriginalFileButtonListener(JFrame mainFrame, ActionPerformerViewInterface actionPerformer, ErrorDisplayInterface errorDisplayer) {
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

	public class LoadEncryptedFileButtonListener implements ActionListener {

		private JFrame mainFrame;
		private ActionPerformerViewInterface actionPerformer;
		private ErrorDisplayInterface errorDisplayer;

		public LoadEncryptedFileButtonListener(JFrame mainFrame, ActionPerformerViewInterface actionPerformer, ErrorDisplayInterface errorDisplayer) {
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

	public class EncryptFileButtonListener implements ActionListener {
		
		private JFrame mainFrame;
		private ActionPerformerViewInterface actionPerformer;
		private ErrorDisplayInterface errorDisplayer;

		public EncryptFileButtonListener(JFrame mainFrame, ActionPerformerViewInterface actionPerformer, ErrorDisplayInterface errorDisplayer) {
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

	public class DecryptFileButtonListener implements ActionListener {
		
		private JFrame mainFrame;
		private ActionPerformerViewInterface actionPerformer;
		private ErrorDisplayInterface errorDisplayer;

		public DecryptFileButtonListener(JFrame mainFrame, ActionPerformerViewInterface actionPerformer, ErrorDisplayInterface errorDisplayer) {
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
