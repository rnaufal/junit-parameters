package br.com.rnaufal.junit.parameters.generator;

/**
 * 
 * @author rsilva
 * 
 */
public interface ParameterGenerator {

    Iterable<Object[]> parameters();
}
