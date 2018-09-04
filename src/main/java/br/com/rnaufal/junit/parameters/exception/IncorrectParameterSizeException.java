package br.com.rnaufal.junit.parameters.exception;

/**
 * @author rsilva
 */
public class IncorrectParameterSizeException extends RuntimeException {

    private static final long serialVersionUID = -4940939697610157532L;

    public IncorrectParameterSizeException(final String message) {
        super(message);
    }
}
