package br.com.rnaufal.junit.parameters.exception;

/**
 * @author rsilva
 */
public class NotFoundParameterGeneratorException extends RuntimeException {

    private static final long serialVersionUID = -6861760846530503805L;

    public NotFoundParameterGeneratorException(final String message) {
        super(message);
    }
}
