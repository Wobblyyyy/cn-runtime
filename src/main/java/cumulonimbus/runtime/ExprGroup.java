package cumulonimbus.runtime;

import cumulonimbus.runtime.ops.OperationHandler;
import cumulonimbus.runtime.ops.OperationResolver;
import cumulonimbus.runtime.ops.Operator;

public class ExprGroup implements Expr {
    private final Expr[] exprs;
    private final Operator[] operators;
    private final OperationResolver resolver;

    public ExprGroup(Expr[] exprs,
                     Operator[] operators,
                     OperationResolver resolver) {
        this.exprs = exprs;
        this.operators = operators;
        this.resolver = resolver;
    }

    @Override
    public Object evaluateRaw() {
        return evaluate().evaluateRaw();
    }

    @Override
    public Value evaluate() {
        if (exprs.length != operators.length + 1) {
            throw new RuntimeException();
        }

        for (int i = 0; i < operators.length; i++) {
            Expr left = exprs[i];
            Expr right = exprs[i + 1];
            Operator operator = operators[i];

            OperationHandler handler = resolver.resolve(
                    left.evaluate().getType(),
                    right.evaluate().getType(),
                    operator
            );

            Expr result = handler.operate(left, right);

            exprs[i + 1] = result;
        }

        return exprs[exprs.length - 1].evaluate();
    }
}
