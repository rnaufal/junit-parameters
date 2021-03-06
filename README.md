# junit-parameters

[![Build Status](https://travis-ci.org/rnaufal/junit-parameters.svg?branch=master)](https://travis-ci.org/rnaufal/junit-parameters)

junit-parameters enables adding parameters to JUnit 4 test methods.

## Code examples

* Annotate your test class with the `JUnitParameters` test runner:

```java
@RunWith(JUnitParameters.class)
public class ParametersGeneratorTest {
}
```

* Implement the interface `ParameterGenerator`:

```java
public class OneParameterGenerator implements ParameterGenerator {

    @Override
    public Iterable<Object[]> parameters() {
        return Arrays.asList(new Object[][] { { "foo" }, { "bar" }, 
                                              { "baz" }, { "code" },
                                              { "java" } });
    }
}
```

* Use it in your test with the `@ParameterProvider` annotation

```java
@Test
@ParameterProvider(OneParameterGenerator.class)
public void injectSingleParam(String parameter) {
}
```

The JUnit test method will be invoked with each parameter created by the `ParameterGenerator` factory used in the test.

## Tests

There are some tests available at `src/test/java`. It's possible to run the `JUnitParametersTest` test class and see the results.

## License

junit-parameters is licensed under the [The MIT License (MIT)](http://choosealicense.com/licenses/mit/)
