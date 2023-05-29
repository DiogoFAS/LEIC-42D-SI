package routine_manager.functions;

import jakarta.persistence.ParameterMode;

public record FunctionParameter(Class<?> clazz, ParameterMode mode) {
}
