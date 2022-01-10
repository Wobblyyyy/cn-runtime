package cumulonimbus.runtime;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    public static final Scope GLOBAL = new Scope("global", null, 0);

    private final String name;
    private final Scope parent;
    private final int nestLevel;

    private final Map<String, Value> values;
    private final Map<String, Func> funcs;

    public Scope(String name) {
        this(name, null, 0);
    }

    public Scope(String name,
                 Scope parent,
                 int nestLevel) {
        this.name = name;
        this.parent = parent;
        this.nestLevel = nestLevel;

        this.values = new HashMap<>();
        this.funcs = new HashMap<>();
    }

    public static Scope minRequiredScope(Scope... scopes) {
        Scope scope = scopes[0];

        for (int i = 1; i < scopes.length; i++) {
            if (scope.nestLevel < scopes[i].nestLevel) {
                scope = scopes[i];
            }
        }

        return scope;
    }

    public static Scope minRequiredScope(Value... values) {
        Scope[] scopes = new Scope[values.length];

        for (int i = 0; i < values.length; i++) {
            scopes[i] = values[i].scope();
        }

        return minRequiredScope(scopes);
    }

    public <T> T resolve(Map<String, T> map,
                         String name) {
        if (map.containsKey(name)) {
            return map.get(name);
        }

        if (parent != null) {
            return parent.resolve(map, name);
        } else {
            throw new RuntimeException();
        }
    }

    public Value resolveValue(String name) {
        return resolve(values, name);
    }

    public Func resolveFunc(String name) {
        return resolve(funcs, name);
    }

    public String getName() {
        return name;
    }

    public Scope getParent() {
        return parent;
    }

    public int getNestLevel() {
        return nestLevel;
    }
}
