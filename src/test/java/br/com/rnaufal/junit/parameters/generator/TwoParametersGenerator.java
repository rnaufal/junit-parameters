package br.com.rnaufal.junit.parameters.generator;

import java.util.Arrays;

import br.com.rnaufal.junit.parameters.generator.ParameterGenerator;

/**
 * 
 * @author rsilva
 * 
 */
public class TwoParametersGenerator implements ParameterGenerator {

    @Override
    public Iterable<Object[]> parameters() {
        return Arrays.asList(new Object[][] { { "a", "b" }, { "c", "d" }, { "e", "f" } });
    }
}