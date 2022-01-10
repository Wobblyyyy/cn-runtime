package cumulonimbus.runtime;

public enum Type {
    NUM,
    INT,
    STR,
    CHAR,
    BOOL,
    NULL,
    STRUCT;

    public boolean isNumeric() {
        return this == NUM || this == INT;
    }
}
