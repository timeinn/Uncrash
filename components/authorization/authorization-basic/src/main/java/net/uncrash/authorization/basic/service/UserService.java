package net.uncrash.authorization.basic.service;

import net.uncrash.authorization.User;
import net.uncrash.authorization.api.web.GeneratedToken;
import net.uncrash.authorization.basic.domain.DefaultUser;

/**
 * @author Sendya
 */
public interface UserService {

    User selectByUserNameAndPassword(String username, String password);

    User register(String email, String username, String password);
}
