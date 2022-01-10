package cumulonimbus.runtime;

public class Value implements Expr {
    public static final Value NULL = new Value(Type.NULL);
    public static final Value TRUE = new Value(Type.BOOL, true);
    public static final Value FALSE = new Value(Type.BOOL, false);

    private final Type type;
    private Object value;
    private final Scope scope;

    public Value(Type type) {
        this(type, null);
    }

    public Value(Type type,
                 Object value) {
        this(type, value, Scope.GLOBAL);
    }

    public Value(Type type,
                 Object value,
                 Value... parents) {
        this(type, value, Scope.minRequiredScope(parents));
    }

    public Value(Type type,
                 Object value,
                 Scope scope) {
        this.type = type;
        this.value = value;
        this.scope = scope;
    }

    public Type getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public Object evaluateRaw() {
        return value;
    }

    @Override
    public Value evaluate() {
        return this;
    }

    @Override
    public Scope scope() {
        return scope;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Value) {
            Value v = (Value) obj;
            return v.getValue().equals(getValue());
        } else {
            return getValue().equals(obj);
        }
    }
}
