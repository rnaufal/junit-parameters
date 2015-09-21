# junit-parameters

junit-parameters enables adding parameters to JUnit test methods.

## Code example

* Implement the interface `ParameterGenerator`:

```java
public class OneParameterGenerator implements ParameterGenerator {

    @Override
    public Iterable<Object[]> parameters() {
        return Arrays.asList(new Object[][] { { "foo" }, { "bar" }, 
                                              { "baz" }, { "code" }, { "java" } });
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
