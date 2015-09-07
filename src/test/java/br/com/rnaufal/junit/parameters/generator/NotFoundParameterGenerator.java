package br.com.rnaufal.junit.parameters.generator;

import java.util.Arrays;

/**
 * 
 * @author rsilva
 * 
 */
public class NotFoundParameterGenerator implements ParameterGenerator {

    @Override
    public Iterable<Object[]> parameters() {
        return Arrays.asList(new Object[][] { { new Object(), "b" }, { "c", "d" }, { "e", "f" } });
    }
}