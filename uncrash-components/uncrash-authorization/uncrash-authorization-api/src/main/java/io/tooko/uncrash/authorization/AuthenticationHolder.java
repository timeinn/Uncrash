package io.tooko.uncrash.authorization;


import io.tooko.core.utils.ThreadLocalUtils;
import io.tooko.uncrash.authorization.api.web.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Function;

public final class AuthenticationHolder {

    private static final List<AuthenticationSupplier> suppliers = new ArrayList<>();

    private static final String CURRENT_USER_ID_KEY = Authentication.class.getName() + "_current_id";

    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    private static Authentication get(Function<AuthenticationSupplier, Authentication> func) {
        lock.readLock().lock();
        try {
            return suppliers.stream()
                .map(func)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse(null);
        } finally {
            lock.readLock().unlock();
        }
    }

    public static Authentication get() {
        String currentId = ThreadLocalUtils.get(CURRENT_USER_ID_KEY);
        if (currentId != null) {
            return get(currentId);
        }
        return get(AuthenticationSupplier::get);
    }

    public static Authentication get(String userId) {
        return get(supplier -> supplier.get(userId));
    }

    public static void addSupplier(AuthenticationSupplier supplier) {
        lock.writeLock().lock();
        try {
            suppliers.add(supplier);
        } finally {
            lock.writeLock().lock();
        }
    }

    public static void setCurrentUserId(String id) {
        ThreadLocalUtils.put(AuthenticationHolder.CURRENT_USER_ID_KEY, id);
    }
}
