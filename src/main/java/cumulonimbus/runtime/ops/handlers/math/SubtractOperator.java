package cumulonimbus.runtime.ops.handlers.math;

import cumulonimbus.runtime.Type;
import cumulonimbus.runtime.Value;
import cumulonimbus.runtime.ops.Operator;

public class SubtractOperator extends GenericMathOperator {
    public SubtractOperator() {
        super(
                Operator.SUBTRACT,
                (a, b) -> new Value(Type.INT, a - b),
                (a, b) -> new Value(Type.NUM, a.doubleValue() - b.doubleValue())
        );
    }
}
