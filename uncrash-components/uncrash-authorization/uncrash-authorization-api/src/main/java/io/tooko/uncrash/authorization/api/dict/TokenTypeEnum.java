package io.tooko.uncrash.authorization.api.dict;

public enum TokenTypeEnum {
    BASIC((byte) 0, "基本"),
    JWT((byte) 1, "JWT"),
    SESSION((byte) 2, "SESSION");

    private Byte value;

    private String text;

    public Byte getValue() {
        return value;
    }

    TokenTypeEnum(Byte value, String text) {
        this.value = value;
        this.text = text;
    }
}
