package br.com.rnaufal.junit.parameters.statement;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.runners.model.FrameworkMethod;

import br.com.rnaufal.junit.parameters.exception.IncorrectParameterSizeException;
import br.com.rnaufal.junit.parameters.exception.NotFoundParameterGeneratorException;
import br.com.rnaufal.junit.parameters.exception.UnableToConvertParameterException;

/**
 * 
 * @author rsilva
 *
 */
public class ParameterTypeCoverterFactory {

	private final FrameworkMethod method;
	
	public ParameterTypeCoverterFactory(FrameworkMethod method) {
		this.method = method;
	}

	public Iterable<Object[]> convertParameters(Iterable<Object[]> data) {
		validateSameSizeParamsAndArgs(data);
	    return convert(data);
	}

	private Iterable<Object[]> convert(Iterable<Object[]> data) {
		List<Object[]> parameters = new ArrayList<>();
	    for (Object[] args : data) {
	        Object[] params = new Object[args.length];
	        for (int i = 0; i < args.length; i++) {
	            Object param;
	            Class<?> parameterClazz = method.getMethod()
	                                            .getParameterTypes()[i];
	            if (parameterClazz.equals(String.class)) {
	                param = buildString(args[i]);
	            } else if (parameterClazz.equals(Integer.TYPE)) {
	                param = buildPrimitiveInt(args[i]);
	            } else if (parameterClazz.equals(Integer.class)) {
	                param = buildInteger(args[i]);
	            } else if (parameterClazz.equals(Long.TYPE)) {
	                param = buildPrimitiveLong(args[i]);
	            } else if (parameterClazz.equals(Long.class)) {
	                param = buildLong(args[i]);
	            } else if (parameterClazz.equals(Boolean.TYPE)) {
	                param = buildPrimitiveBoolean(args[i]);
	            } else if (parameterClazz.equals(Boolean.class)) {
	                param = buildBoolean(args[i]);
	            } else if (parameterClazz.equals(Short.TYPE)) {
	                param = buildPrimitiveShort(args[i]);
	            } else if (parameterClazz.equals(Short.class)) {
	                param = buildShort(args[i]);
	            } else if (parameterClazz.equals(Byte.TYPE)) {
	                param = buildPrimitiveByte(args[i]);
	            } else if (parameterClazz.equals(Byte.class)) {
	                param = buildByte(args[i]);
	            } else if (parameterClazz.equals(Character.TYPE)) {
	                param = buildPrimitiveChar(args[i]);
	            } else if (parameterClazz.equals(Character.class)) {
	                param = buildChar(args[i]);
	            } else if (parameterClazz.equals(Float.TYPE)) {
	                param = buildPrimitiveFloat(args[i]);
	            } else if (parameterClazz.equals(Float.class)) {
	                param = buildFloat(args[i]);
	            } else if (parameterClazz.equals(Double.TYPE)) {
	                param = buildPrimitiveDouble(args[i]);
	            } else if (parameterClazz.equals(Double.class)) {
	                param = buildDouble(args[i]);
	            } else {
	                throw new NotFoundParameterGeneratorException("Cannot convert " + args[i] + " to " + parameterClazz);
	            }
	            params[i] = param;
	        }
	        parameters.add(params);
	    }
	    return parameters;
	}

	private String buildString(Object arg) {
	    if (arg != null) {
	        return String.valueOf(arg);
	    }
	    return StringUtils.EMPTY;
	}

	private Float buildFloat(Object arg) {
	    if (arg != null) {
	        try {
	            return Float.parseFloat(arg.toString());
	        } catch (Exception e) {
	            throw new UnableToConvertParameterException(e);
	        }
	    }
	    return null;
	}

	private float buildPrimitiveFloat(Object arg) {
	    if (arg != null) {
	        try {
	            return Float.parseFloat(arg.toString());
	        } catch (Exception e) {
	            throw new UnableToConvertParameterException(e);
	        }
	    }
	    return Float.MIN_VALUE;
	}

	private double buildPrimitiveDouble(Object arg) {
	    if (arg != null) {
	        try {
	            return Double.parseDouble(arg.toString());
	        } catch (Exception e) {
	            throw new UnableToConvertParameterException(e);
	        }
	    }
	    return Double.MIN_VALUE;
	}

	private Double buildDouble(Object arg) {
	    if (arg != null) {
	        try {
	            return Double.parseDouble(arg.toString());
	        } catch (Exception e) {
	            throw new UnableToConvertParameterException(e);
	        }
	    }
	    return null;
	}

	private Character buildChar(Object arg) {
	    if (arg != null) {
	        String sequence = String.valueOf(arg);
	        return sequence.length() == 1 ? sequence.charAt(0) : null;
	    }
	    return null;
	}

	private char buildPrimitiveChar(Object arg) {
	    if (arg != null) {
	        String sequence = String.valueOf(arg);
	        return sequence.length() == 1 ? sequence.charAt(0) : Character.MIN_VALUE;
	    }
	    return Character.MIN_VALUE;
	}

	private Byte buildByte(Object arg) {
	    if (arg != null) {
	        try {
	            return Byte.parseByte(arg.toString());
	        } catch (Exception e) {
	            throw new UnableToConvertParameterException(e);
	        }
	    }
	    return null;
	}

	private byte buildPrimitiveByte(Object arg) {
	    if (arg != null) {
	        try {
	            return Byte.parseByte(arg.toString());
	        } catch (Exception e) {
	            throw new UnableToConvertParameterException(e);
	        }
	    }
	    return Byte.MIN_VALUE;
	}

	private Short buildShort(Object arg) {
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

	private boolean buildPrimitiveBoolean(Object arg) {
	    if (arg != null) {
	        try {
	            return Boolean.parseBoolean(arg.toString());
	        } catch (Exception e) {
	            throw new UnableToConvertParameterException(e);
	        }
	    }
	    return false;
	}

	private Boolean buildBoolean(Object arg) {
	    if (arg != null) {
	        try {
	            return Boolean.parseBoolean(arg.toString());
	        } catch (Exception e) {
	            throw new UnableToConvertParameterException(e);
	        }
	    }
	    return null;
	}

	private Long buildLong(Object arg) {
	    if (arg != null) {
	        try {
	            return Long.parseLong(arg.toString());
	        } catch (Exception e) {
	            throw new UnableToConvertParameterException(e);
	        }
	    }
	    return null;
	}

	private long buildPrimitiveLong(Object arg) {
	    if (arg != null) {
	        try {
	            return Long.parseLong(arg.toString());
	        } catch (Exception e) {
	            throw new UnableToConvertParameterException(e);
	        }
	    }
	    return Long.MIN_VALUE;
	}

	private Integer buildInteger(Object arg) {
	    if (arg != null) {
	        try {
	            return Integer.parseInt(arg.toString());
	        } catch (Exception e) {
	            throw new UnableToConvertParameterException(e);
	        }
	    }
	    return null;
	}

	private int buildPrimitiveInt(Object arg) {
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