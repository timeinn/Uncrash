package net.uncrash.authorization;


import net.uncrash.authorization.api.web.Authentication;
import net.uncrash.authorization.api.web.GeneratedToken;
import net.uncrash.core.utils.ThreadLocalUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;

public final class AuthenticationHolder {

    private static final List<AuthenticationSupplier> SUPPLIERS = new ArrayList<>();

    private static final String CURRENT_USER_ID_KEY = Authentication.class.getName() + "_current_id";

    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();

    private static Authentication get(Function<AuthenticationSupplier, Authentication> func) {
        return SUPPLIERS.stream()
            .map(func)
            .filter(Objects::nonNull)
            .findFirst()
            .orElse(null);
    }

    public static Authentication get() {
        return get(AuthenticationSupplier::get);
    }

    public static Authentication get(String userId) {
        return get(supplier -> supplier.get(userId));
    }

    public static void addSupplier(AuthenticationSupplier supplier) {
        LOCK.writeLock().lock();
        try {
            SUPPLIERS.add(supplier);
        } finally {
            LOCK.writeLock().lock();
        }
    }

    public static GeneratedToken save(Authentication authentication) {
        GeneratedToken token = SUPPLIERS.stream()
            .findFirst().get()
            .set(authentication.getToken(), authentication);
        return token;
    }

    public static void setCurrentUserId(String id) {
        ThreadLocalUtils.put(AuthenticationHolder.CURRENT_USER_ID_KEY, id);
    }

    public static void remove(Authentication authentication) {

    }
}
