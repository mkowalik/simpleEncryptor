package encryptor;

import java.util.ArrayList;
import java.util.List;

public class EncryptorChooser {
	
	private List<Encryptor> list = new ArrayList<>();
	private Encryptor actualEncryptor = null;
	
	public void registerEncryptor(Encryptor enc){
		list.add(enc);
	}
	
	public List<Encryptor> getEncryptorList(){
		return list;
	}
	
	public void setActualEncryptor(Encryptor actualEncryptor){
		this.actualEncryptor = actualEncryptor;
	}
	
	public Encryptor getActualEncryptor(){
		return this.actualEncryptor;
	}
	
}
