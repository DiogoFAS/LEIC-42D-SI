package routine_manager.routine;

import jakarta.persistence.ParameterMode;


public record RoutineParameter(Class<?> clazz, ParameterMode mode) {
}
