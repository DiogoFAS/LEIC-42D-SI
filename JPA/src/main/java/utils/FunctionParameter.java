package utils;

import jakarta.persistence.ParameterMode;

public record FunctionParameter(
        Class<?> parameterClass,
        ParameterMode mode
) {}
