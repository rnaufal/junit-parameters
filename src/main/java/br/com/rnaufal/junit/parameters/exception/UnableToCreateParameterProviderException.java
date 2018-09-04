package br.com.rnaufal.junit.parameters.exception;

/**
 * @author rsilva
 */
public class UnableToCreateParameterProviderException extends RuntimeException {

    private static final long serialVersionUID = -7671524562483687866L;

    public UnableToCreateParameterProviderException(final String message,
                                                    final Throwable cause) {
        super(message, cause);
    }
}
