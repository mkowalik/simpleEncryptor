package controller;

public interface ErrorDisplayInterface {

	public void notChoosenFileWarning(String message);
	public void noSuchFileWarning(String message);
	public void readingFileProblemWarning(String message);
	public void customizeEncryptorWarning(String message);
	
}
