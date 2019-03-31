package io.tooko.uncrash.authorization.api.dict;

public enum TokenTypeEnum {
    /**
     * Basic
     */
    BASIC((byte) 0, "基本"),
    /**
     * JSON Web Token
     */
    JWT((byte) 1, "JWT"),
    /**
     * Session
     */
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
