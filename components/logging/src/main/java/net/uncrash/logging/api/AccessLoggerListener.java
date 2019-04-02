package net.uncrash.logging.api;

public interface AccessLoggerListener {

    void onLogger(AccessLoggerInfo loggerInfo);

    default void onLogBefore(AccessLoggerInfo loggerInfo) {
    }
}
