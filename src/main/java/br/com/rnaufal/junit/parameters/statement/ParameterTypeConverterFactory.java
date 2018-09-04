package br.com.rnaufal.junit.parameters.statement;

import br.com.rnaufal.junit.parameters.converter.ParameterConverter;
import br.com.rnaufal.junit.parameters.exception.IncorrectParameterSizeException;
import br.com.rnaufal.junit.parameters.exception.NotFoundParameterGeneratorException;
import br.com.rnaufal.junit.parameters.exception.UnableToConvertParameterException;
import com.google.common.collect.ImmutableMap;
import org.apache.commons.lang3.StringUtils;
import org.junit.runners.model.FrameworkMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author rsilva
 */
public class ParameterTypeConverterFactory {

    private final FrameworkMethod method;

    private final Map<Class<?>, ParameterConverter<Object, ?>> convertersByClass = new ImmutableMap.Builder<Class<?>, ParameterConverter<Object, ?>>()
            .put(String.class, this::buildString)
            .put(Integer.TYPE, this::buildPrimitiveInt)
            .put(Integer.class, this::buildInteger)
            .put(Long.TYPE, this::buildPrimitiveLong)
            .put(Long.class, this::buildLong)
            .put(Boolean.TYPE, this::buildPrimitiveBoolean)
            .put(Boolean.class, this::buildBoolean)
            .put(Short.TYPE, this::buildPrimitiveShort)
            .put(Short.class, this::buildShort)
            .put(Byte.TYPE, this::buildPrimitiveByte)
            .put(Byte.class, this::buildByte)
            .put(Character.TYPE, this::buildPrimitiveChar)
            .put(Character.class, this::buildChar)
            .put(Float.TYPE, this::buildPrimitiveFloat)
            .put(Float.class, this::buildFloat)
            .put(Double.TYPE, this::buildPrimitiveDouble)
            .put(Double.class, this::buildDouble)
            .build();

    public ParameterTypeConverterFactory(final FrameworkMethod method) {
        this.method = method;
    }

    public Iterable<Object[]> convertParameters(final Iterable<Object[]> data) {
        validateSameSizeParamsAndArgs(data);
        return convert(data);
    }

    private Iterable<Object[]> convert(final Iterable<Object[]> data) {
        List<Object[]> parameters = new ArrayList<>();
        for (Object[] args : data) {
            Object[] params = new Object[args.length];
            for (int i = 0; i < args.length; i++) {
                final Class<?> parameterClazz = method.getMethod()
                        .getParameterTypes()[i];
                params[i] = convert(args[i], parameterClazz);
            }
            parameters.add(params);
        }
        return parameters;
    }

    private Object convert(final Object parameter,
                           final Class<?> parameterClass) {
        return getParameterConverter(parameterClass)
                .map(converter -> converter.convert(parameter))
                .orElseThrow(() -> new NotFoundParameterGeneratorException("Cannot convert " + parameter + " to " + parameterClass));
    }

    private Optional<? extends ParameterConverter<Object, ?>> getParameterConverter(final Class<?> parameterClass) {
        return Optional.ofNullable(convertersByClass.get(parameterClass));
    }

    private String buildString(final Object arg) {
        if (arg != null) {
            return String.valueOf(arg);
        }
        return StringUtils.EMPTY;
    }

    private Float buildFloat(final Object arg) {
        if (arg != null) {
            try {
                return Float.parseFloat(arg.toString());
            } catch (Exception e) {
                throw new UnableToConvertParameterException(e);
            }
        }
        return null;
    }

    private float buildPrimitiveFloat(final Object arg) {
        if (arg != null) {
            try {
                return Float.parseFloat(arg.toString());
            } catch (Exception e) {
                throw new UnableToConvertParameterException(e);
            }
        }
        return Float.MIN_VALUE;
    }

    private double buildPrimitiveDouble(final Object arg) {
        if (arg != null) {
            try {
                return Double.parseDouble(arg.toString());
            } catch (Exception e) {
                throw new UnableToConvertParameterException(e);
            }
        }
        return Double.MIN_VALUE;
    }

    private Double buildDouble(final Object arg) {
        if (arg != null) {
            try {
                return Double.parseDouble(arg.toString());
            } catch (Exception e) {
                throw new UnableToConvertParameterException(e);
            }
        }
        return null;
    }

    private Character buildChar(final Object arg) {
        if (arg != null) {
            String sequence = String.valueOf(arg);
            return sequence.length() == 1 ? sequence.charAt(0) : null;
        }
        return null;
    }

    private char buildPrimitiveChar(final Object arg) {
        if (arg != null) {
            String sequence = String.valueOf(arg);
            return sequence.length() == 1 ? sequence.charAt(0) : Character.MIN_VALUE;
        }
        return Character.MIN_VALUE;
    }

    private Byte buildByte(final Object arg) {
        if (arg != null) {
            try {
                return Byte.parseByte(arg.toString());
            } catch (Exception e) {
                throw new UnableToConvertParameterException(e);
            }
        }
        return null;
    }

    private byte buildPrimitiveByte(final Object arg) {
        if (arg != null) {
            try {
                return Byte.parseByte(arg.toString());
            } catch (Exception e) {
                throw new UnableToConvertParameterException(e);
            }
        }
        return Byte.MIN_VALUE;
    }

    private Short buildShort(final Object arg) {
        if (arg != null) {
            try {
                return Short.parseShort(arg.toString());
            } catch (Exception e) {
                throw new UnableToConvertParameterException(e);
            }
        }
        return null;
    }

    private short buildPrimitiveShort(Object arg) {
        if (arg != null) {
            try {
                return Short.parseShort(arg.toString());
            } catch (Exception e) {
                throw new UnableToConvertParameterException(e);
            }
        }
        return Short.MIN_VALUE;
    }

    private boolean buildPrimitiveBoolean(final Object arg) {
        if (arg != null) {
            try {
                return Boolean.parseBoolean(arg.toString());
            } catch (Exception e) {
                throw new UnableToConvertParameterException(e);
            }
        }
        return false;
    }

    private Boolean buildBoolean(final Object arg) {
        if (arg != null) {
            try {
                return Boolean.parseBoolean(arg.toString());
            } catch (Exception e) {
                throw new UnableToConvertParameterException(e);
            }
        }
        return null;
    }

    private Long buildLong(final Object arg) {
        if (arg != null) {
            try {
                return Long.parseLong(arg.toString());
            } catch (Exception e) {
                throw new UnableToConvertParameterException(e);
            }
        }
        return null;
    }

    private long buildPrimitiveLong(final Object arg) {
        if (arg != null) {
            try {
                return Long.parseLong(arg.toString());
            } catch (Exception e) {
                throw new UnableToConvertParameterException(e);
            }
        }
        return Long.MIN_VALUE;
    }

    private Integer buildInteger(final Object arg) {
        if (arg != null) {
            try {
                return Integer.parseInt(arg.toString());
            } catch (Exception e) {
                throw new UnableToConvertParameterException(e);
            }
        }
        return null;
    }

    private int buildPrimitiveInt(final Object arg) {
        if (arg != null) {
            try {
                return Integer.parseInt(arg.toString());
            } catch (Exception e) {
                throw new UnableToConvertParameterException(e);
            }
        }
        return Integer.MIN_VALUE;
    }

    private void validateSameSizeParamsAndArgs(Iterable<Object[]> data) {
        Class<?>[] parameterTypes = method.getMethod()
                .getParameterTypes();
        for (Object[] args : data) {
            if (args.length != parameterTypes.length) {
                throw new IncorrectParameterSizeException("Method params and args provided have different size");
            }
        }
    }
}