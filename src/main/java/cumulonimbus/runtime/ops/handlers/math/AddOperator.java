package cumulonimbus.runtime.ops.handlers.math;

import cumulonimbus.runtime.Expr;
import cumulonimbus.runtime.Type;
import cumulonimbus.runtime.Value;
import cumulonimbus.runtime.ops.OperationHandler;
import cumulonimbus.runtime.ops.Operator;

public class AddOperator implements OperationHandler {
    @Override
    public Operator operator() {
        return Operator.ADD;
    }

    @Override
    public Value operate(Expr leftExpr, Expr rightExpr) {
        boolean isLeftExprValue = leftExpr instanceof Value;
        boolean isRightExprValue = rightExpr instanceof Value;

        if (isLeftExprValue && isRightExprValue) {
            Value leftValue = (Value) leftExpr;
            Value rightValue = (Value) rightExpr;

            Type leftType = leftValue.getType();
            Type rightType = rightValue.getType();

            if (leftType.isNumeric() && rightType.isNumeric()) {
                if (leftType == Type.INT && rightType == Type.INT) {
                    Integer leftInt = (Integer) leftValue.getValue();
                    Integer rightInt = (Integer) rightValue.getValue();

                    return new Value(
                            Type.INT,
                            leftInt + rightInt,
                            leftValue,
                            rightValue
                    );
                }

                Number leftNumber = (Number) leftValue.getValue();
                Number rightNumber = (Number) rightValue.getValue();

                return new Value(
                        Type.NUM,
                        leftNumber.doubleValue() + rightNumber.doubleValue(),
                        leftValue,
                        rightValue
                );
            }

            String rightString = rightValue.getValue().toString();
            String leftString = leftValue.getValue().toString();

            return new Value(
                    Type.STR,
                    rightString + leftString,
                    leftValue,
                    rightValue
            );
        }

        return Value.NULL;
    }
}
