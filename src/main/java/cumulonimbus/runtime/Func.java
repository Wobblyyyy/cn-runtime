package cumulonimbus.runtime;

import java.util.function.Function;

public class Func {
    private final Type returnType;
    private final Type[] parameterTypes;
    private final Function<Value[], Value> evaluator;

    public Func(Type returnType,
                Type[] parameterTypes,
                Function<Value[], Value> evaluator) {
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.evaluator = evaluator;
    }

    public Type getReturnType() {
        return returnType;
    }

    public Type[] getParameterTypes() {
        return parameterTypes;
    }

    public boolean validParameters(Value[] parameters) {
        if (parameterTypes.length != parameters.length) return false;

        for (int i = 0; i < parameters.length; i++) {
            if (parameterTypes[i] != parameters[i].getType()) return false;
        }

        return true;
    }

    public Value invoke(Value[] parameters) {
        if (validParameters(parameters)) {
            return evaluator.apply(parameters);
        } else {
            throw new RuntimeException();
        }
    }
}
