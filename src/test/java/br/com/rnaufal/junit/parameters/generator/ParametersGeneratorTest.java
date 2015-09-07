package br.com.rnaufal.junit.parameters.generator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.rnaufal.junit.parameters.annotation.ParameterProvider;
import br.com.rnaufal.junit.parameters.generator.ParameterGenerator;
import br.com.rnaufal.junit.parameters.runners.JUnitParameters;

/**
 * 
 * @author rnaufal
 * 
 */
@RunWith(JUnitParameters.class)
public class ParametersGeneratorTest {

    @Test
    public void shouldBehaviorLikeDefaultTest() {
    }

    @Test
    @ParameterProvider(TwoParametersGenerator.class)
    public void twoParametersInjection(String first,
                                       String second) {
        Map<String, List<String>> expected = expectedParams(new TwoParametersGenerator());
        List<String> actual = expected.get(first);
        if (actual == null || actual.isEmpty()) {
            fail("Not found parameters [" + first + "]," + "[" + second + "]");
        }
        assertThat(actual, hasSize(2));
        assertThat(first, is(equalTo(actual.get(0))));
        assertThat(second, is(equalTo(actual.get(1))));
    }
    
    @Test
    @ParameterProvider(OneParameterGenerator.class)
    public void oneParameterInjection(String parameter) {
        Map<String, List<String>> expected = expectedParams(new OneParameterGenerator());
        List<String> actual = expected.get(parameter);
        if (actual == null || actual.isEmpty()) {
            fail("Not found parameter [" + parameter + "]");
        }
        assertThat(actual, hasSize(1));
        assertThat(parameter, is(equalTo(actual.get(0))));
    }
    
    private Map<String, List<String>> expectedParams(ParameterGenerator generator) {
        Map<String, List<String>> expected = new HashMap<String, List<String>>();
        for (Object[] params : generator.parameters()) {
            String first = String.class.cast(params[0]);
            String[] parameters = Arrays.copyOf(params, params.length, String[].class);
            expected.put(first, Arrays.asList(parameters));
        }
        return expected;
    }
}
