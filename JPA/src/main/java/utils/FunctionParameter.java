package utils;

import jakarta.persistence.ParameterMode;

public record FunctionParameter(Class<?> clazz, ParameterMode mode) {
}
