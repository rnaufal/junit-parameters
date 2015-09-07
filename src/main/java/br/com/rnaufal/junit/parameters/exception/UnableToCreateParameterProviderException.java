package br.com.rnaufal.junit.parameters.exception;

/**
 * 
 * @author rsilva
 * 
 */
public class UnableToCreateParameterProviderException extends RuntimeException {

	private static final long serialVersionUID = -7671524562483687866L;

	public UnableToCreateParameterProviderException(String message) {
		super(message);
	}

	public UnableToCreateParameterProviderException(Throwable cause) {
		super(cause);
	}

	public UnableToCreateParameterProviderException(String message,
			Throwable cause) {
		super(message, cause);
	}

	public UnableToCreateParameterProviderException(String message,
			Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
