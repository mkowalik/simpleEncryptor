package controller;

public interface ErrorDisplayInterface {

	public void noSuchFileWarning(String message);
	public void readingFileProblemWarning(String message);
	public void customizeEncryptorWarning(String message);
	
}
