package utils;

import java.util.Map;
import java.util.function.Function;

public class Utils {

    public static void clearConsole() {
        for (int y = 0; y < 25; y++) { // clears 25 lines of console window.
            System.out.println("\n");
        }
    }

    public static Object parseObject(Class<?> clazz, String value) {
        String cName = clazz.getName();

        if (clazz == String.class) return value;

        if (!clazz.isPrimitive())
            throw new IllegalArgumentException("Cannot convert non-primitive type: " + cName);

        return primitiveToWrapper.get(clazz).apply(value);
    }

    public static final Map<Class<?>, Function<String, Object>> primitiveToWrapper = Map.of(
            int.class, Integer::parseInt,
            long.class, Long::parseLong,
            float.class, Float::parseFloat,
            double.class, Double::parseDouble,
            short.class, Short::parseShort,
            byte.class, Byte::parseByte,
            boolean.class, Boolean::parseBoolean);

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

