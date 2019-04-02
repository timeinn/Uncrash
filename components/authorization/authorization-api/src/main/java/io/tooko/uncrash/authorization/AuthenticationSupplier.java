package io.tooko.uncrash.authorization;

import io.tooko.uncrash.authorization.api.web.Authentication;

import java.util.function.Supplier;

public interface AuthenticationSupplier extends Supplier<Authentication> {

    Authentication get(String userId);

}
