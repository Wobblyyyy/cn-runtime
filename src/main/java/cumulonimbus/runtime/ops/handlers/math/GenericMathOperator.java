package cumulonimbus.runtime.ops.handlers.math;

import cumulonimbus.runtime.Expr;
import cumulonimbus.runtime.Type;
import cumulonimbus.runtime.Value;
import cumulonimbus.runtime.ops.OperationHandler;
import cumulonimbus.runtime.ops.Operator;

import java.util.function.BiFunction;

public class GenericMathOperator implements OperationHandler {
    private final Operator operator;
    private final BiFunction<Integer, Integer, Value> intSolver;
    private final BiFunction<Number, Number, Value> numSolver;

    public GenericMathOperator(Operator operator,
                               BiFunction<Integer, Integer, Value> intSolver,
                               BiFunction<Number, Number, Value> numSolver) {
        this.operator = operator;
        this.intSolver = intSolver;
        this.numSolver = numSolver;
    }

    @Override
    public Operator operator() {
        return operator;
    }

    @Override
    public Value operate(Expr leftExpr,
                         Expr rightExpr) {
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
                            intSolver.apply(leftInt, rightInt),
                            leftValue,
                            rightValue
                    );
                }

                Number leftNumber = (Number) leftValue.getValue();
                Number rightNumber = (Number) rightValue.getValue();

                return new Value(
                        Type.NUM,
                        numSolver.apply(leftNumber, rightNumber),
                        leftValue,
                        rightValue
                );
            }
        }

        return Value.NULL;
    }
}
