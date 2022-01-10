package cumulonimbus.runtime;

public interface Expr {
    Object evaluateRaw();

    Value evaluate();

    default Scope scope() {
        return Scope.GLOBAL;
    }
}
