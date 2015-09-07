package br.com.rnaufal.junit.parameters.generator;

import java.util.Arrays;

import br.com.rnaufal.junit.parameters.generator.ParameterGenerator;

/**
 * 
 * @author rsilva
 * 
 */
public class BooleanParameterGenerator implements ParameterGenerator {

    @Override
    public Iterable<Object[]> parameters() {
        return Arrays.asList(new Object[][] { { "false", "true" }, { "true", "true" }, { "false", "false" } });
    }
}