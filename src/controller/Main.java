package controller;

import java.lang.System;

import javax.swing.SwingUtilities;

import encryptor.EncryptorChooser;
import encryptor.XOREncryptor;
import misc.Config;
import model.CurrentFileModel;
import view.ButtonActionsHandlers;
import view.MainView;

/**
 * Main class with 'main' function responsible for running application.
 * Constructs ActionsController, ButtonActionsHandlers, EncryptorChooser,
 * CurrentFileModel. Registers all encryptors in EncryptorChooser and runs GUI
 * in new thread.
 * 
 * @author kowalik
 *
 */
public class Main {

	private static ActionsController actionsController = null;
	private static ButtonActionsHandlers buttonActionsHandlers = null;
	private static EncryptorChooser encryptorChooser = null;
	private static CurrentFileModel currentFileModel = null;

	/**
	 * Main function. Entry point to aplication. Constructs ActionsController,
	 * ButtonActionsHandlers, EncryptorChooser, CurrentFileModel. Registers all
	 * encryptors in EncryptorChooser and runs GUI in new thread.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(Config.appName);

		encryptorChooser = new EncryptorChooser();
		encryptorChooser.registerEncryptor(new XOREncryptor());

		currentFileModel = new CurrentFileModel();
		actionsController = new ActionsController(currentFileModel, encryptorChooser);
		buttonActionsHandlers = new ButtonActionsHandlers(actionsController);

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainView(buttonActionsHandlers, actionsController, encryptorChooser.getEncryptorList());
			}
		});

	}
}
