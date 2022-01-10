package cumulonimbus.runtime.ops;

import cumulonimbus.runtime.Expr;
import cumulonimbus.runtime.Value;

public interface OperationHandler {
    Operator operator();

    Value operate(Expr leftExpr,
                  Expr rightExpr);
}
