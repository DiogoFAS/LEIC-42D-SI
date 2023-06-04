package routine_manager.functions;

import jakarta.persistence.ParameterMode;

import java.util.Map;


public record FunctionParameter(String paramName, Class<?> clazz, ParameterMode mode) {
}
