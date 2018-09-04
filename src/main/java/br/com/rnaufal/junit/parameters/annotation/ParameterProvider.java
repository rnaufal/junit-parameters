package br.com.rnaufal.junit.parameters.annotation;

import br.com.rnaufal.junit.parameters.generator.ParameterGenerator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author rsilva
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterProvider {

    Class<? extends ParameterGenerator> value();
}
