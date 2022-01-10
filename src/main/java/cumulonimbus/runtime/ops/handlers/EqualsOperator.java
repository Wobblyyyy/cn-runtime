package cumulonimbus.runtime.ops.handlers;

import cumulonimbus.runtime.Expr;
import cumulonimbus.runtime.Value;
import cumulonimbus.runtime.ops.OperationHandler;
import cumulonimbus.runtime.ops.Operator;

public class EqualsOperator implements OperationHandler {
    @Override
    public Operator operator() {
        return Operator.EQUALS;
    }

    @Override
    public Value operate(Expr leftExpr,
                         Expr rightExpr) {
        Value left = leftExpr.evaluate();
        Value right = rightExpr.evaluate();

        if (left.equals(right)) {
            return Value.TRUE;
        } else {
            return Value.FALSE;
        }
    }
}
