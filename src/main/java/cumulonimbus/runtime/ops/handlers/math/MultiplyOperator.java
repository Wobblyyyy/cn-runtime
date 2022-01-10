package cumulonimbus.runtime.ops.handlers.math;

import cumulonimbus.runtime.Type;
import cumulonimbus.runtime.Value;
import cumulonimbus.runtime.ops.Operator;

public class MultiplyOperator extends GenericMathOperator {
    public MultiplyOperator() {
        super(
                Operator.MULTIPLY,
                (a, b) -> new Value(Type.INT, a * b),
                (a, b) -> new Value(Type.NUM, a.doubleValue() * b.doubleValue())
        );
    }
}
