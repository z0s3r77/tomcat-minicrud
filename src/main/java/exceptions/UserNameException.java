package exceptions;

public class UserNameException extends Exception{

	private static final long serialVersionUID = 1L;

	public UserNameException(String errorMessage) {
		super(errorMessage);
	}

	
}