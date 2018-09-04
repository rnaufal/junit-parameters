package br.com.rnaufal.junit.parameters.converter;

/**
 * Created by rsilva
 */
@FunctionalInterface
public interface ParameterConverter<T, R> {

    R convert(T t);
}
