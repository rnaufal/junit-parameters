package br.com.rnaufal.junit.parameters.runners;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.rules.ExpectedException;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import br.com.rnaufal.junit.parameters.annotation.ParameterProvider;
import br.com.rnaufal.junit.parameters.exception.IncorrectParameterSizeException;
import br.com.rnaufal.junit.parameters.exception.NotFoundParameterGeneratorException;
import br.com.rnaufal.junit.parameters.exception.UnableToConvertParameterException;
import br.com.rnaufal.junit.parameters.exception.UnableToCreateParameterProviderException;
import br.com.rnaufal.junit.parameters.generator.BooleanParameterGenerator;
import br.com.rnaufal.junit.parameters.generator.CharacterParameterGenerator;
import br.com.rnaufal.junit.parameters.generator.InvalidTwoParameterGenerator;
import br.com.rnaufal.junit.parameters.generator.NotFoundParameterGenerator;
import br.com.rnaufal.junit.parameters.generator.NumberParameterGenerator;
import br.com.rnaufal.junit.parameters.generator.OneParameterGenerator;
import br.com.rnaufal.junit.parameters.generator.TwoParametersGenerator;
import br.com.rnaufal.junit.parameters.statement.MethodParameterStatement;

/**
 * 
 * @author rsilva
 * 
 */
public class JUnitParametersTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void validTestMethods() throws InitializationError {
        try {
            new JUnitParameters(ValidTestClass.class);
        } catch (Exception e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void invalidTestMethods() throws InitializationError {
        thrown.expect(InitializationError.class);
        new JUnitParameters(InjectedMethodWithoutParamTest.class);
    }

    @Test
    public void invokeTestMethodWithSingleParam() throws InitializationError, NoSuchMethodException, SecurityException {
        JUnitParameters jUnitParameters = new JUnitParameters(ValidTestClass.class);
        Method m = ValidTestClass.class.getDeclaredMethod("injectSingleParam", String.class);
        FrameworkMethod method = new FrameworkMethod(m);
        Statement statement = jUnitParameters.methodInvoker(method, new ValidTestClass());
        assertThat(statement, instanceOf(MethodParameterStatement.class));
    }

    @Test
    public void invokeTestMethodWithParams() throws InitializationError, NoSuchMethodException, SecurityException {
        JUnitParameters jUnitParameters = new JUnitParameters(ValidTestClass.class);
        Method m = ValidTestClass.class.getDeclaredMethod("injectParams", String.class, String.class);
        FrameworkMethod method = new FrameworkMethod(m);
        Statement statement = jUnitParameters.methodInvoker(method, new ValidTestClass());
        assertThat(statement, instanceOf(MethodParameterStatement.class));
    }

    @Test
    public void invokeDefaultTestMethod() throws InitializationError, NoSuchMethodException, SecurityException {
        JUnitParameters jUnitParameters = new JUnitParameters(ValidTestClass.class);
        Method m = ValidTestClass.class.getDeclaredMethod("defaultTest");
        FrameworkMethod method = new FrameworkMethod(m);
        Statement statement = jUnitParameters.methodInvoker(method, new ValidTestClass());
        assertThat(statement, instanceOf(InvokeMethod.class));
    }

    @Test(expected = UnableToCreateParameterProviderException.class)
    public void throwExceptionWhenProviderClassIsInvalid() throws InitializationError, NoSuchMethodException, SecurityException {
        JUnitParameters jUnitParameters = new JUnitParameters(InvalidParameterProviderTest.class);
        Method m = InvalidParameterProviderTest.class.getDeclaredMethod("tryInjectParams", String.class, String.class);
        FrameworkMethod method = new FrameworkMethod(m);
        jUnitParameters.methodInvoker(method, new InvalidParameterProviderTest());
    }

    @Test(expected = IncorrectParameterSizeException.class)
    public void throwExceptionWhenParamSizeIsDifferent() throws Throwable {
        JUnitParameters jUnitParameters = new JUnitParameters(IncorrectParameterSizeTest.class);
        Method m = IncorrectParameterSizeTest.class.getDeclaredMethod("shouldNotHaveTheSameSize", String.class, String.class, String.class);
        FrameworkMethod method = new FrameworkMethod(m);
        Statement statement = jUnitParameters.methodInvoker(method, new IncorrectParameterSizeTest());
        statement.evaluate();
    }

    @Test
    public void convertParameters() throws Throwable {
        JUnitParameters jUnitParameters = new JUnitParameters(NumberTypeConverterParameterTest.class);
        for (Method m : NumberTypeConverterParameterTest.class.getDeclaredMethods()) {
            FrameworkMethod method = new FrameworkMethod(m);
            Statement statement = jUnitParameters.methodInvoker(method, new NumberTypeConverterParameterTest());
            statement.evaluate();
        }
    }

    @Test
    public void convertCharacterParameters() throws Throwable {
        JUnitParameters jUnitParameters = new JUnitParameters(CharacterTypeConverterParameterTest.class);
        Method m = CharacterTypeConverterParameterTest.class.getDeclaredMethod("shouldConvertCharacterParameters", Character.class, Character.TYPE);
        FrameworkMethod method = new FrameworkMethod(m);
        Statement statement = jUnitParameters.methodInvoker(method, new CharacterTypeConverterParameterTest());
        statement.evaluate();
    }

    @Test
    public void convertBooleanParameters() throws Throwable {
        JUnitParameters jUnitParameters = new JUnitParameters(BooleanTypeConverterParameterTest.class);
        Method m = BooleanTypeConverterParameterTest.class.getDeclaredMethod("shouldConvertBooleanParameters", Boolean.class, Boolean.TYPE);
        FrameworkMethod method = new FrameworkMethod(m);
        Statement statement = jUnitParameters.methodInvoker(method, new BooleanTypeConverterParameterTest());
        statement.evaluate();
    }

    @Test(expected = NotFoundParameterGeneratorException.class)
    public void notFoundParameterGenerator() throws Throwable {
        JUnitParameters jUnitParameters = new JUnitParameters(NotFoundParameterGeneratorTest.class);
        Method m = NotFoundParameterGeneratorTest.class.getDeclaredMethod("notFoundGenerator", Object.class, String.class);
        FrameworkMethod method = new FrameworkMethod(m);
        Statement statement = jUnitParameters.methodInvoker(method, new NotFoundParameterGeneratorTest());
        statement.evaluate();
    }
    
    @Test(expected = UnableToConvertParameterException.class)
    public void throwUnableToConvertException() throws Throwable {
        JUnitParameters jUnitParameters = new JUnitParameters(UnableToConvertParameterTypeTest.class);
        Method m = UnableToConvertParameterTypeTest.class.getDeclaredMethod("unableToConvert", Integer.class, String.class);
        FrameworkMethod method = new FrameworkMethod(m);
        Statement statement = jUnitParameters.methodInvoker(method, new UnableToConvertParameterTypeTest());
        statement.evaluate();
    }

    public static class ValidTestClass {

        @Test
        public void defaultTest() {
        }

        @Test
        @ParameterProvider(OneParameterGenerator.class)
        public void injectSingleParam(String parameter) {
        }

        @Test
        @ParameterProvider(TwoParametersGenerator.class)
        public void injectParams(String first,
                                 String second) {
        }
    }

    public static class InjectedMethodWithoutParamTest {

        @Test
        @ParameterProvider(OneParameterGenerator.class)
        public void notInjectParameter() {
        }
    }

    public static class InvalidParameterProviderTest {

        @Test
        @ParameterProvider(InvalidTwoParameterGenerator.class)
        public void tryInjectParams(String first,
                                    String second) {
        }
    }

    public static class NumberTypeConverterParameterTest {

        @Test
        @ParameterProvider(NumberParameterGenerator.class)
        public void shouldConvertIntegerParameters(Integer first,
                                                   int second) {
        }

        @Test
        @ParameterProvider(NumberParameterGenerator.class)
        public void shouldConvertDoubleParameters(Double first,
                                                  double second) {
        }

        @Test
        @ParameterProvider(NumberParameterGenerator.class)
        public void shouldConvertFloatParameters(Float first,
                                                 float second) {
        }

        @Test
        @ParameterProvider(NumberParameterGenerator.class)
        public void shouldConvertShortParameters(Short first,
                                                 short second) {
        }

        @Test
        @ParameterProvider(NumberParameterGenerator.class)
        public void shouldConvertByteParameters(Byte first,
                                                byte second) {
        }

        @Test
        @ParameterProvider(NumberParameterGenerator.class)
        public void shouldConvertLongParameters(Long first,
                                                long second) {
        }
    }

    public static class CharacterTypeConverterParameterTest {

        @Test
        @ParameterProvider(CharacterParameterGenerator.class)
        public void shouldConvertCharacterParameters(Character first,
                                                     char second) {
        }
    }

    public static class BooleanTypeConverterParameterTest {

        @Test
        @ParameterProvider(BooleanParameterGenerator.class)
        public void shouldConvertBooleanParameters(Boolean first,
                                                   boolean second) {
        }
    }

    public static class IncorrectParameterSizeTest {

        @Test
        @ParameterProvider(OneParameterGenerator.class)
        public void shouldNotHaveTheSameSize(String first,
                                             String second,
                                             String three) {
        }
    }

    public static class NotFoundParameterGeneratorTest {

        @Test
        @ParameterProvider(NotFoundParameterGenerator.class)
        public void notFoundGenerator(Object first,
                                    String second) {
        }
    }
    
    public static class UnableToConvertParameterTypeTest {

        @Test
        @ParameterProvider(BooleanParameterGenerator.class)
        public void unableToConvert(Integer first,
                                    String second) {
        }
    }
}
