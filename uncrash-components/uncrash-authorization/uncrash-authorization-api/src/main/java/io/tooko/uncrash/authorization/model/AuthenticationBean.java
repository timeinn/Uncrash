package io.tooko.uncrash.authorization.model;

import lombok.Data;

@Data
public class AuthenticationBean {

    private String username;

    private String password;

    private String captcha;
}
