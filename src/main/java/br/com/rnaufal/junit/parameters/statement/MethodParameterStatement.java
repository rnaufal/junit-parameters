package br.com.rnaufal.junit.parameters.statement;

import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;

import br.com.rnaufal.junit.parameters.generator.ParameterGenerator;

/**
 * 
 * @author rnaufal
 * 
 */
public class MethodParameterStatement extends Statement {

    private final Object test;

    private final FrameworkMethod method;

    private final ParameterGenerator generator;

    public MethodParameterStatement(Object test, FrameworkMethod method, ParameterGenerator generator) {
        this.test = test;
        this.method = method;
        this.generator = generator;
    }

    @Override
    public void evaluate() throws Throwable {
        convertParameters().forEach(params -> {
            try {
                method.invokeExplosively(test, params);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Iterable<Object[]> convertParameters() {
        return new ParameterTypeCoverterFactory(method).convertParameters(generator.parameters());
    }
}
