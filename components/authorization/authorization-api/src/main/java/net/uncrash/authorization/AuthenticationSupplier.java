package net.uncrash.authorization;

import net.uncrash.authorization.api.web.Authentication;
import net.uncrash.authorization.api.web.GeneratedToken;

import java.util.function.Supplier;

public interface AuthenticationSupplier extends Supplier<Authentication> {

    Authentication get(String userId);

    GeneratedToken set(String userId, Authentication authentication);

}
