package model;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class CurrentFileModel {
	
	String orginalFileString = null;
	String encryptedFileString = null;
	File orginalFile = null;
	File encryptedFile = null;
	
	public void setUpOrginalFile(File orginalFile){
		this.orginalFile = orginalFile;
		this.orginalFileString = null;
	}
	
	public void setUpEncryptedFile(File encryptedFile){
		this.encryptedFile = encryptedFile;
		this.encryptedFileString = null;
	}
	
	private static String readFileToString(File file){
		String ret = null;
		try {
			FileReader reader = new FileReader(file);
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[16];
			int read = 0;
			while ((read = reader.read(buffer))>=0){
				builder.append(buffer, 0, read);
			}
			reader.close();
			ret = builder.toString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	private void saveStringToFile(String text, File file) {
		try {
			DataOutputStream stream = new DataOutputStream(new FileOutputStream(file));
			stream.writeChars(text);
			stream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getOrginalFileString(){
		if (orginalFileString == null)
			orginalFileString = readFileToString(orginalFile);
		return orginalFileString;
	}
	
	public String getEncryptedFileString(){
		if (encryptedFileString == null)
			encryptedFileString = readFileToString(encryptedFile);
		return encryptedFileString;
	}
	
	public void saveToFileEncryptedString(String text){
		encryptedFileString = text;
		saveStringToFile(text, encryptedFile);
	}
	
	public void saveToFileOrginalString(String text){
		orginalFileString = text;
		saveStringToFile(text, orginalFile);
	}

}
