package br.com.rnaufal.junit.parameters.generator;

import java.util.Arrays;

import br.com.rnaufal.junit.parameters.generator.ParameterGenerator;

/**
 * 
 * @author rsilva
 * 
 */
public class NumberParameterGenerator implements ParameterGenerator {

    @Override
    public Iterable<Object[]> parameters() {
        return Arrays.asList(new Object[][] { { "56", "12" }, { "78", "45" }, { "13", "70" } });
    }
}