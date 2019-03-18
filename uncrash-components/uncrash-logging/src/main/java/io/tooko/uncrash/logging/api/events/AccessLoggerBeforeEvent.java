package io.tooko.uncrash.logging.api.events;

import io.tooko.uncrash.logging.api.AccessLoggerInfo;

public class AccessLoggerBeforeEvent {

    private AccessLoggerInfo logger;

    public AccessLoggerBeforeEvent(AccessLoggerInfo logger) {
        this.logger = logger;
    }

    public AccessLoggerInfo getLogger() {
        return logger;
    }
}
