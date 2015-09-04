package controller;

import java.io.File;

import encryptor.Encryptor;
import encryptor.EncryptorChooser;
import model.CurrentFileModel;

public class ActionsController {
	
	private CurrentFileModel currentFileModel;
	private EncryptorChooser encryptoChooser;
	private Encryptor actualEncryptor = null; 

	public ActionsController(CurrentFileModel currentFileModel, EncryptorChooser encrytporChooser){
		this.currentFileModel = currentFileModel;
		this.encryptoChooser = encrytporChooser;
	}
	
	public void loadOrginaleFile(ActionPerformerViewInterface view, File file){
		currentFileModel.setUpOrginalFile(file);
		String orginalFileString = currentFileModel.getOrginalFileString();
		view.showOrginalFile(orginalFileString);
		view.showEncryptedFile("");
	}
	
	public void loadEncryptedFile(ActionPerformerViewInterface view, File file){
		currentFileModel.setUpEncryptedFile(file);
		String encryptedFileString = currentFileModel.getEncryptedFileString();
		view.showEncryptedFile(encryptedFileString);
		view.showOrginalFile("");
	}
	
	public void encrypt(ActionPerformerViewInterface view){
		
	}
	
	public void decrypt(ActionPerformerViewInterface view){
		
	}
	
	public void customizeEncryptor(ActionPerformerViewInterface view){
		
	}
	
	public EncryptorChooser getEncryptorChooser(){
		return encryptoChooser;
	}

	public void setChoosedEncryptor(Encryptor selectedEncryptor) {
		this.actualEncryptor = selectedEncryptor;
	}
	
	public Encryptor getChoosedEncryptor(){
		return actualEncryptor;
	}

}
