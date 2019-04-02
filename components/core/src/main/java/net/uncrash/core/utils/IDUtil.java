package net.uncrash.core.utils;

public class IDUtil {

    public static String generate() {
        return generate("1");
    }

    public static String generate(String machineId) {
        return machineId == null ? "1" : machineId +
            String.valueOf(System.currentTimeMillis()).substring(1) +
            String.valueOf(System.nanoTime()).substring(7, 10);
    }

    public static String longID() {
        return "";
    }

}
