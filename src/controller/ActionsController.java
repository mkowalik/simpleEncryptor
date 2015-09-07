package controller;

import java.io.File;

import encryptor.CustomizeEncryptorException;
import encryptor.Encryptor;
import encryptor.EncryptorChooser;
import encryptor.EncryptorReturn;
import model.CurrentFileModel;

public class ActionsController {
	
	private CurrentFileModel currentFileModel;
	private EncryptorChooser encryptoChooser;
	private Encryptor actualEncryptor = null; 

	public ActionsController(CurrentFileModel currentFileModel, EncryptorChooser encrytporChooser){
		this.currentFileModel = currentFileModel;
		this.encryptoChooser = encrytporChooser;
	}
	
	public void loadOrginalFile(ActionPerformerViewInterface view, File file){
		currentFileModel.setUpOrginalFile(file);
		String orginalFileString = currentFileModel.getOrginalFileString();
		orginalFileString = actualEncryptor.preprocessingEncryptToDisplay(orginalFileString);
		view.showOrginalFile(orginalFileString);
		view.showEncryptedFile("");
	}
	
	public void loadEncryptedFile(ActionPerformerViewInterface view, File file){
		currentFileModel.setUpEncryptedFile(file);
		String encryptedFileString = currentFileModel.getEncryptedFileString();
		encryptedFileString = actualEncryptor.preprocessingDecryptToDisplay(encryptedFileString);
		view.showEncryptedFile(encryptedFileString);
		view.showOrginalFile("");
	}
	
	public void encrypt(ActionPerformerViewInterface view, File outFile){
		EncryptorReturn encrypted;
		try {
			encrypted = actualEncryptor.encrypt(currentFileModel.getOrginalFileString());
			currentFileModel.setUpEncryptedFile(outFile);
			currentFileModel.saveToFileEncryptedString(encrypted.getToSaveInFIle());
			String str = encrypted.getToSaveInFIle();
			for (int i=0; i<str.length(); i++){
				System.out.println("aa:" + (int)str.charAt(i));
			}
			view.showEncryptedFile(encrypted.getToDisplay());
		} catch (CustomizeEncryptorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void decrypt(ActionPerformerViewInterface view, File outFile){
		EncryptorReturn orginal;
		try {
			orginal = actualEncryptor.decrytp(currentFileModel.getEncryptedFileString());
			currentFileModel.setUpOrginalFile(outFile);
			currentFileModel.saveToFileOrginalString(orginal.getToSaveInFIle());
			view.showOrginalFile(orginal.getToDisplay());
		} catch (CustomizeEncryptorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
