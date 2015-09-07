package model;

import java.lang.System;

import javax.swing.SwingUtilities;

import controller.ActionsController;
import encryptor.EncryptorChooser;
import encryptor.XOREncryptor;
import misc.Config;
import view.ButtonActionsHandlers;
import view.MainView;

public class Main {

	private static ActionsController actionsController = null;
	private static ButtonActionsHandlers buttonActionsHandlers = null;
	private static EncryptorChooser encryptorChooser = null;
	private static CurrentFileModel currentFileModel = null;
	
	public static void main(String[] args){
		
		System.out.println(Config.appName);
		
		encryptorChooser = new EncryptorChooser();
		encryptorChooser.registerEncryptor(new XOREncryptor());
		
		currentFileModel = new CurrentFileModel();
		actionsController = new ActionsController(currentFileModel, encryptorChooser);
		buttonActionsHandlers = new ButtonActionsHandlers(actionsController);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				new MainView(buttonActionsHandlers, actionsController, encryptorChooser.getEncryptorList());
			}
		});
		
		
	}
}
