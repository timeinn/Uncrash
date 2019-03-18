package io.tooko.uncrash.logging.api;

public class LoggerDefine {

    private String action;

    private String describe;

    public LoggerDefine(String action, String describe) {
        this.action = action;
        this.describe = describe;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
