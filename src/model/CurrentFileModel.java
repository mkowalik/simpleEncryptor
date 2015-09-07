package model;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
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
	
	private static String readBinaryFileToString(File file){
		String ret = null;
		try {
			DataInputStream stream = new DataInputStream(new FileInputStream(file));
			StringBuilder builder = new StringBuilder();
			while (stream.available()>=2){
				char c = stream.readChar();
				builder.append(c);
			}
			stream.close();
			ret = builder.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	private static String readTextFileToString(File file){
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

	private void saveBinaryStringToFile(String text, File file) {
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
	
	private void saveTextStringToFile(String text, File file){
		try {
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			writer.write(text);
			writer.close();
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
			orginalFileString = readTextFileToString(orginalFile);
		return orginalFileString;
	}
	
	public String getEncryptedFileString(){
		if (encryptedFileString == null)
			encryptedFileString = readBinaryFileToString(encryptedFile);
		return encryptedFileString;
	}
	
	public void saveToFileEncryptedString(String text){
		encryptedFileString = text;
		saveBinaryStringToFile(text, encryptedFile);
	}
	
	public void saveToFileOrginalString(String text){
		orginalFileString = text;
		saveTextStringToFile(text, orginalFile);
	}

}
