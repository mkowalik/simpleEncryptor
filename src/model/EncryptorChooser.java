package model;

import java.util.ArrayList;
import java.util.List;

public class EncryptorChooser {
	
	List<Encryptor> list = new ArrayList<>();
	
	public void registerEncryptor(Encryptor enc){
		list.add(enc);
	}
	
	public List<Encryptor> getEncryptorList(){
		return list;
	}
	
}
