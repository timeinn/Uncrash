package net.uncrash.model;

import lombok.Data;

@Data
public class UserRegisterBody {

    private String username;

    private String password;

    private String email;

    private String captcha;

}
