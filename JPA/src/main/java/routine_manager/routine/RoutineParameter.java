package routine_manager.routine;

import jakarta.persistence.ParameterMode;


public record RoutineParameter(String paramName, Class<?> clazz, ParameterMode mode) {
}
