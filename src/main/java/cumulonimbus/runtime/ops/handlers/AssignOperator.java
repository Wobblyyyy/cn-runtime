package cumulonimbus.runtime.ops.handlers;

import cumulonimbus.runtime.Expr;
import cumulonimbus.runtime.Value;
import cumulonimbus.runtime.ops.OperationHandler;
import cumulonimbus.runtime.ops.Operator;

public class AssignOperator implements OperationHandler {
    @Override
    public Operator operator() {
        return Operator.ASSIGN;
    }

    @Override
    public Value operate(Expr leftExpr,
                         Expr rightExpr) {
        if (leftExpr instanceof Value) {
            Value leftValue = (Value) leftExpr;
            leftValue.setValue(rightExpr.evaluateRaw());
        }
        
        return Value.NULL;
    }
}
