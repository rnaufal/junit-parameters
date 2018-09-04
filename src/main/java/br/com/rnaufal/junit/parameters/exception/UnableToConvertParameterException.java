package br.com.rnaufal.junit.parameters.exception;

/**
 * @author rsilva
 */
public class UnableToConvertParameterException extends RuntimeException {

    private static final long serialVersionUID = -8022293114956261445L;

    public UnableToConvertParameterException(final Throwable cause) {
        super(cause);
    }
}
