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

    public static <T> void printTable(List<T> table, Class<?> clazz) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        int[] columnWidths = new int[fields.length];

        // Calculate max column widths
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            int headerWidth = field.getName().length();
            columnWidths[i] = headerWidth;

            for (T instance : table) {
                field.setAccessible(true);
                Object value = field.get(instance);
                int valueWidth = String.valueOf(value).length();
                columnWidths[i] = Math.max(columnWidths[i], valueWidth);
            }
        }

        // Print column headers with adjusted width
        System.out.println();
        for (int i = 0; i < fields.length; i++) {
            String header = fields[i].getName();
            System.out.printf("%-" + columnWidths[i] + "s\t\t", header);
        }
        System.out.println();

        // Print rows with adjusted width
        for (T instance : table) {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];

                field.setAccessible(true);
                Object value = field.get(instance);

                String formattedValue = String.format("%-" + columnWidths[i] + "s", value);
                System.out.printf("%-" + columnWidths[i] + "s\t\t", formattedValue);
            }
            System.out.println();
        }
    }
}
