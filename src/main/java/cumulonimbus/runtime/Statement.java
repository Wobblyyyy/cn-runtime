package cumulonimbus.runtime;

import cumulonimbus.runtime.ops.Operator;

public class Statement {
    private final Expr[] exprs;
    private final Operator[] operators;

    public Statement(Expr expr) {
        this(
                new Expr[] {
                        expr
                },
                new Operator[0]
        );
    }

    public Statement(Expr[] exprs,
                     Operator[] operators) {
        this.exprs = exprs;
        this.operators = operators;
    }
}
