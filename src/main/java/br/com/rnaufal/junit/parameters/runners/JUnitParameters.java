package br.com.rnaufal.junit.parameters.runners;

import br.com.rnaufal.junit.parameters.annotation.ParameterProvider;
import br.com.rnaufal.junit.parameters.exception.UnableToCreateParameterProviderException;
import br.com.rnaufal.junit.parameters.generator.ParameterGenerator;
import br.com.rnaufal.junit.parameters.statement.MethodParameterStatement;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

/**
 * @author rsilva
 */
public class JUnitParameters extends BlockJUnit4ClassRunner {

    public JUnitParameters(final Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected void validateTestMethods(final List<Throwable> errors) {
        List<FrameworkMethod> testMethods = computeTestMethods();
        validateNonZeroParametersMethod(errors, testMethods);
        validateZeroParamsMethod(errors, testMethods);
    }

    @Override
    protected Statement methodInvoker(final FrameworkMethod method,
                                      final Object test) {
        Statement base = super.methodInvoker(method, test);
        ParameterProvider provider = hasDataProvider(method);
        if (provider != null) {
            ParameterGenerator generator = createDataGeneratorInstance(provider);
            return new MethodParameterStatement(test, method, generator);
        }
        return base;
    }

    private ParameterProvider hasDataProvider(final FrameworkMethod method) {
        return method.getAnnotation(ParameterProvider.class);
    }

    private ParameterGenerator createDataGeneratorInstance(final ParameterProvider provider) {
        Class<? extends ParameterGenerator> clazz = provider.value();
        ParameterGenerator generator;
        try {
            generator = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new UnableToCreateParameterProviderException(
                    "Unable to create DataGenerator with no args constructor", e);
        }
        return generator;
    }

    private void validateNonZeroParamsMethod(final FrameworkMethod method,
                                             final List<Throwable> errors) {
        validatePublicVoid(method, errors);
        checkForZeroParams(method, errors);
    }

    private void checkForZeroParams(FrameworkMethod method, List<Throwable> errors) {
        if (!hasParameters(method)) {
            errors.add(new Exception("Method " + method.getName() + "() should have params"));
        }
    }

    private void validatePublicVoid(final FrameworkMethod method,
                                    final List<Throwable> errors) {
        method.validatePublicVoid(false, errors);
    }

    private boolean hasParameters(final FrameworkMethod method) {
        return ArrayUtils.isNotEmpty(method.getMethod().getParameterTypes());
    }

    private void validateNonZeroParametersMethod(final List<Throwable> errors,
                                                 final List<FrameworkMethod> testMethods) {
        testMethods
                .stream()
                .filter(each -> Objects.nonNull(each.getAnnotation(ParameterProvider.class)))
                .forEach(method -> validateNonZeroParamsMethod(method, errors));
    }

    private void validateZeroParamsMethod(final List<Throwable> errors,
                                          final List<FrameworkMethod> testMethods) {
        testMethods
                .stream()
                .filter(each -> Objects.isNull(each.getAnnotation(ParameterProvider.class)))
                .forEach(method -> method.validatePublicVoidNoArg(false, errors));
    }
}
