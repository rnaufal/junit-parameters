package br.com.rnaufal.junit.parameters.generator;

import java.util.Arrays;

/**
 * 
 * @author rsilva
 * 
 */
public class InvalidTwoParameterGenerator implements ParameterGenerator {

    private final String param;

    public InvalidTwoParameterGenerator(String param) {
        this.param = param;
    }

    @Override
    public Iterable<Object[]> parameters() {
        return Arrays.asList(new Object[][] { { param, "b" }, { "c", "d" }, { "e", "f" } });
    }
}