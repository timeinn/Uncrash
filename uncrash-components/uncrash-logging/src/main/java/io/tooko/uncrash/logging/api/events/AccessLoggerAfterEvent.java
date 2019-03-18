package io.tooko.uncrash.logging.api.events;

import io.tooko.uncrash.logging.api.AccessLoggerInfo;

public class AccessLoggerAfterEvent {

    private AccessLoggerInfo logger;

    public AccessLoggerAfterEvent(AccessLoggerInfo logger) {
        this.logger = logger;
    }

    public AccessLoggerInfo getLogger() {
        return logger;
    }

}
