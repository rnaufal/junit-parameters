package br.com.rnaufal.junit.parameters.generator;

import java.util.Arrays;

import br.com.rnaufal.junit.parameters.generator.ParameterGenerator;

/**
 * 
 * @author rsilva
 * 
 */
public class OneParameterGenerator implements ParameterGenerator {

    @Override
    public Iterable<Object[]> parameters() {
        return Arrays.asList(new Object[][] { { "foo" }, { "bar" }, { "baz" }, { "code" }, { "java" } });
    }
}