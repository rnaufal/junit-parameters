package br.com.rnaufal.junit.parameters.exception;

/**
 * 
 * @author rsilva
 * 
 */
public class UnableToConvertParameterException extends RuntimeException {

	private static final long serialVersionUID = -8022293114956261445L;

	public UnableToConvertParameterException(String message) {
		super(message);
	}

	public UnableToConvertParameterException(Throwable cause) {
		super(cause);
	}

	public UnableToConvertParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public UnableToConvertParameterException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
