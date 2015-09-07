package encryptor;

public class EncryptorReturn {
	
	private String toDisplay;
	private String toSaveInFile;

	public EncryptorReturn(String toDisplay, String toSaveInFile){
		this.toDisplay = toDisplay;
		this.toSaveInFile = toSaveInFile;
	}
	
	public String getToDisplay(){
		return toDisplay;
	}
	
	public String getToSaveInFIle(){
		return toSaveInFile;
	}
	
}
