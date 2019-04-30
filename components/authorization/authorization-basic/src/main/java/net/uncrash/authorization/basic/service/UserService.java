package net.uncrash.authorization.basic.service;

import net.uncrash.authorization.api.web.GeneratedToken;
import net.uncrash.authorization.basic.domain.DefaultUser;

/**
 * @author Sendya
 */
public interface UserService {

    GeneratedToken selectByUserNameAndPassword(String username, String password);
}
