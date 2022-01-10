package cumulonimbus.runtime.ops;

import cumulonimbus.runtime.Type;
import cumulonimbus.runtime.ops.handlers.AssignOperator;
import cumulonimbus.runtime.ops.handlers.EqualsOperator;
import cumulonimbus.runtime.ops.handlers.NotEqualsOperator;
import cumulonimbus.runtime.ops.handlers.math.*;

import java.util.ArrayList;
import java.util.List;

public class OperationResolver {
    public static final List<OperationHandler> DEFAULT_HANDLERS =
            new ArrayList<>() {{
                add(new AssignOperator());
                add(new EqualsOperator());
                add(new NotEqualsOperator());
                add(new AddOperator());
                add(new SubtractOperator());
                add(new MultiplyOperator());
                add(new DivideOperator());
            }};

    public OperationHandler resolve(Type leftType,
                                    Type rightType,
                                    Operator operator) {
        for (OperationHandler handler : DEFAULT_HANDLERS) {
            if (handler.operator() == operator) return handler;
        }

        return null;
    }
}
