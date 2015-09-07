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
import encryptor.Encryptor;

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
					.setChoosedEncryptor((Encryptor) (((JComboBox<Encryptor>) e.getSource()).getSelectedItem()));
			System.out.println("ChooserBoxListener");
		}

	}

	public class CustomizeEncryptorButtonListener implements ActionListener {

		JFrame mainFrame;

		public CustomizeEncryptorButtonListener(JFrame mainFrame) {
			this.mainFrame = mainFrame;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Encryptor enc = actionsController.getChoosedEncryptor();
			JDialog widget = enc.getParametersWidget(mainFrame);
			widget.setLocationRelativeTo(mainFrame);
			widget.setVisible(true);
		}

	}

	public class LoadOriginalFileButtonListener implements ActionListener {

		private JFrame mainFrame;
		private ActionPerformerViewInterface actionPerformer;

		public LoadOriginalFileButtonListener(JFrame mainFrame, ActionPerformerViewInterface actionPerformer) {
			this.mainFrame = mainFrame;
			this.actionPerformer = actionPerformer;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser chooser = new JFileChooser();

			if (chooser.showOpenDialog(mainFrame) != JFileChooser.APPROVE_OPTION)
				return;
			else {
				System.out.println("Load orginal file button");
				File file = chooser.getSelectedFile();
				actionsController.loadOrginalFile(actionPerformer, file);
			}
		}
	}

	public class LoadEncryptedFileButtonListener implements ActionListener {

		private JFrame mainFrame;
		private ActionPerformerViewInterface actionPerformer;

		public LoadEncryptedFileButtonListener(JFrame mainFrame, ActionPerformerViewInterface actionPerformer) {
			this.mainFrame = mainFrame;
			this.actionPerformer = actionPerformer;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser chooser = new JFileChooser();

			if (chooser.showOpenDialog(mainFrame) != JFileChooser.APPROVE_OPTION)
				return;
			else {
				File file = chooser.getSelectedFile();
				actionsController.loadEncryptedFile(actionPerformer, file);
			}
		}
	}

	public class EncryptFileButtonListener implements ActionListener {
		
		private JFrame mainFrame;
		private ActionPerformerViewInterface actionPerformer;

		public EncryptFileButtonListener(JFrame mainFrame, ActionPerformerViewInterface actionPerformer) {
			this.mainFrame = mainFrame;
			this.actionPerformer = actionPerformer;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser chooser = new JFileChooser();
			System.out.println("Encrypt 1");

			if (chooser.showOpenDialog(mainFrame) != JFileChooser.APPROVE_OPTION)
				return;
			else {
				System.out.println("Encrypt 2");
				File file = chooser.getSelectedFile();
				actionsController.encrypt(actionPerformer, file);
			}

		}

	}

	public class DecryptFileButtonListener implements ActionListener {
		
		private JFrame mainFrame;
		private ActionPerformerViewInterface actionPerformer;

		public DecryptFileButtonListener(JFrame mainFrame, ActionPerformerViewInterface actionPerformer) {
			this.mainFrame = mainFrame;
			this.actionPerformer = actionPerformer;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {

			JFileChooser chooser = new JFileChooser();

			if (chooser.showOpenDialog(mainFrame) != JFileChooser.APPROVE_OPTION)
				return;
			else {
				File file = chooser.getSelectedFile();
				actionsController.decrypt(actionPerformer, file);
			}

		}
	}
}
