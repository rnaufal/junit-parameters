package br.com.rnaufal.junit.parameters.exception;

/**
 * 
 * @author rsilva
 *
 */
public class NotFoundParameterGeneratorException extends RuntimeException {

    private static final long serialVersionUID = -6861760846530503805L;

    public NotFoundParameterGeneratorException(String message) {
        super(message);
    }

    public NotFoundParameterGeneratorException(Throwable cause) {
        super(cause);
    }

    public NotFoundParameterGeneratorException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundParameterGeneratorException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
