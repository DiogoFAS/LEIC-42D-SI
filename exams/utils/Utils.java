package utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


public class Utils {

    public static <T> void printTable(List<T> table, Class<?> clazz) throws IllegalAccessException {
        Field[] fields = clazz.getDeclaredFields();
        int[] columnWidths = new int[fields.length];

        // Calculate max column widths
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
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
