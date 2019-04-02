package io.tooko.uncrash.authorization.api.web;

import io.tooko.uncrash.authorization.api.dict.TokenTypeEnum;

public interface TokenGenerator {

    String TOKEN_ID = "sessionId";

    Byte TOKEN_TYPE = TokenTypeEnum.BASIC.getValue();

    Byte getSupportTokenType();

    GeneratedToken generate(Authentication authentication);

}
