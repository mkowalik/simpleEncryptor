package encryptor;

import java.util.ArrayList;
import java.util.List;

public class EncryptorChooser {
	
	private List<EncryptorInterface> list = new ArrayList<>();
	private EncryptorInterface actualEncryptor = null;
	
	public void registerEncryptor(EncryptorInterface enc){
		list.add(enc);
	}
	
	public List<EncryptorInterface> getEncryptorList(){
		return list;
	}
	
	public void setActualEncryptor(EncryptorInterface actualEncryptor){
		this.actualEncryptor = actualEncryptor;
	}
	
	public EncryptorInterface getActualEncryptor(){
		return this.actualEncryptor;
	}
	
}
