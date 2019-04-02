package net.uncrash.logging.api.events;

import net.uncrash.logging.api.AccessLoggerInfo;

public class AccessLoggerBeforeEvent {

    private AccessLoggerInfo logger;

    public AccessLoggerBeforeEvent(AccessLoggerInfo logger) {
        this.logger = logger;
    }

    public AccessLoggerInfo getLogger() {
        return logger;
    }
}
