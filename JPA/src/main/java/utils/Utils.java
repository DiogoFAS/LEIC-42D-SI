package utils;

import table_returns.JogadorPontos;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.lang.reflect.Field;


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

    public static <T> void printTable(List<T> table, Class<?> clazz) {
        // TODO: horizontally align content
        Field[] fields = clazz.getDeclaredFields();

        System.out.println();
        // Print column headers
        for (Field field : fields) {
            System.out.print(field.getName() + "\t\t"); // Add extra tab space
        }
        System.out.println();

        // Print rows
        for (T instance : table) {
            for (Field field : fields) {
                try {
                    // Set accessible to true to access non-public fields
                    field.setAccessible(true);
                    Object value = field.get(clazz.cast(instance));

                    // Format the output with a fixed width of 10 characters
                    String formattedValue = String.format("%-10s", value);
                    System.out.print(formattedValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }
}
