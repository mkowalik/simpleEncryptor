package model;

import java.lang.System;

import javax.swing.SwingUtilities;

import misc.Config;
import view.MainView;

public class Main {
	
	static MainView mainView = null;
	
	public static void main(String[] args){
		
		System.out.println(Config.appName);
		
		EncryptorChooser encoderChooser = new EncryptorChooser();
		encoderChooser.registerEncryptor(new XOREncryptor());
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run(){
				mainView = new MainView(encoderChooser);
			}
		});
		
		
	}
}
