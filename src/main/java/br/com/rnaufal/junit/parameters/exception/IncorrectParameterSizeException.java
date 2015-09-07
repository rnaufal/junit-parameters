package br.com.rnaufal.junit.parameters.exception;

/**
 * 
 * @author rsilva
 * 
 */
public class IncorrectParameterSizeException extends RuntimeException {

	private static final long serialVersionUID = -4940939697610157532L;

	public IncorrectParameterSizeException(String message) {
		super(message);
	}

	public IncorrectParameterSizeException(Throwable cause) {
		super(cause);
	}

	public IncorrectParameterSizeException(String message, Throwable cause) {
		super(message, cause);
	}

	public IncorrectParameterSizeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
