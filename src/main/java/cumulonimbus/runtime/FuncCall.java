package cumulonimbus.runtime;

public class FuncCall implements Expr {
    private final Func func;
    private final Value[] parameters;
    private final Scope scope;

    public FuncCall(Func func,
                    Value[] parameters,
                    Scope scope) {
        this.func = func;
        this.parameters = parameters;
        this.scope = scope;
    }

    @Override
    public Object evaluateRaw() {
        return evaluate().evaluateRaw();
    }

    @Override
    public Value evaluate() {
        return func.invoke(parameters);
    }

    @Override
    public Scope scope() {
        return scope;
    }
}
