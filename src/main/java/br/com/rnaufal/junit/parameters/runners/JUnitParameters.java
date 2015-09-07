package br.com.rnaufal.junit.parameters.runners;

import java.util.List;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import br.com.rnaufal.junit.parameters.annotation.ParameterProvider;
import br.com.rnaufal.junit.parameters.exception.UnableToCreateParameterProviderException;
import br.com.rnaufal.junit.parameters.generator.ParameterGenerator;
import br.com.rnaufal.junit.parameters.statement.MethodParameterStatement;

/**
 * 
 * @author rsilva
 * 
 */
public class JUnitParameters extends BlockJUnit4ClassRunner {

    public JUnitParameters(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected void validateTestMethods(List<Throwable> errors) {
        List<FrameworkMethod> testMethods = computeTestMethods();
        validateNonZeroParametersMethod(errors, testMethods);
        validateZeroParamsMethod(errors, testMethods);
    }

    @Override
    protected Statement methodInvoker(final FrameworkMethod method, final Object test) {
        Statement base = super.methodInvoker(method, test);
        ParameterProvider provider = hasDataProvider(method);
        if (provider != null) {
            ParameterGenerator generator = createDataGeneratorInstance(provider);
            return new MethodParameterStatement(test, method, generator);
        }
        return base;
    }

    private ParameterProvider hasDataProvider(FrameworkMethod method) {
        return method.getAnnotation(ParameterProvider.class);
    }

    private ParameterGenerator createDataGeneratorInstance(ParameterProvider provider) {
        Class<? extends ParameterGenerator> clazz = provider.value();
        ParameterGenerator generator;
        try {
            generator = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new UnableToCreateParameterProviderException(
                    "Unable to create DataGenerator with no args constructor", e);
        }
        return generator;
    }

    private void validateNonZeroParamsMethod(FrameworkMethod method, List<Throwable> errors) {
        validatePublicVoid(method, errors);
        checkForZeroParams(method, errors);
    }

    private void checkForZeroParams(FrameworkMethod method, List<Throwable> errors) {
        if (!hasParameters(method)) {
            errors.add(new Exception("Method " + method.getName() + "() should have params"));
        }
    }

    private void validatePublicVoid(FrameworkMethod method, List<Throwable> errors) {
        method.validatePublicVoid(false, errors);
    }

    private boolean hasParameters(FrameworkMethod method) {
        return method.getMethod().getParameterTypes().length > 0;
    }
    
    private void validateNonZeroParametersMethod(List<Throwable> errors, List<FrameworkMethod> testMethods) {
        testMethods
            .stream()
            .filter(each -> each.getAnnotation(ParameterProvider.class) != null)
            .forEach(method -> validateNonZeroParamsMethod(method, errors));
    }

    private void validateZeroParamsMethod(List<Throwable> errors, List<FrameworkMethod> testMethods) {
        testMethods
            .stream()
            .filter(each -> null == each.getAnnotation(ParameterProvider.class))
            .forEach(method -> method.validatePublicVoidNoArg(false, errors));
    }
}
