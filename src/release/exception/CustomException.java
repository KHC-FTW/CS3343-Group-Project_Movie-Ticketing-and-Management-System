package release.exception;

public class CustomException extends Exception{
	public CustomException(String message) {
		super("\n[Exception] " + message + "\n");
	}
}
