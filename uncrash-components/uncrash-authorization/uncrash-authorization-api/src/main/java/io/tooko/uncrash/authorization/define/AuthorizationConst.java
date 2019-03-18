package io.tooko.uncrash.authorization.define;

import java.util.StringJoiner;

public class AuthorizationConst {

    public static final String LOGGER_NAME = "[SYSTEM]";

    public static final String REDIS_KEY = "{auth}:user";

    public static final String joiner(String token) {
        return new StringJoiner(":")
                .add(REDIS_KEY)
                .add(token).toString();
    }

}
