package cumulonimbus.runtime.util;

import cumulonimbus.runtime.Type;

public class TypePair {
    private final Type left;
    private final Type right;

    public TypePair(Type left,
                    Type right) {
        this.left = left;
        this.right = right;
    }

    public Type left() {
        return left;
    }

    public Type right() {
        return right;
    }

    @Override
    public int hashCode() {
        return (left.hashCode() * 1_000_000) + right.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TypePair) {
            TypePair p = (TypePair) obj;
            return left == p.left && right == p.right;
        }

        return false;
    }
}
