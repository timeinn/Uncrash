package net.uncrash.core.utils;

import net.uncrash.core.utils.id.IDGenerator;

import java.util.StringJoiner;

/**
 * @author Sendya
 */
public final class PasswordBuilder {

    private PasswordBuilder() {
    }

    /**
     * 创建一个 加密盐
     *
     * @return
     */
    public static String createSalt() {
        return IDGenerator.RANDOM.generate();
    }

    /**
     * 构建一个带盐的加密 MD5 密码
     *
     * @param password
     * @param salt
     * @return String
     */
    public static String builder(String password, String salt) {
        String str = new StringJoiner(password).add("-").add(salt).toString();
        return DecriptUtil.md5(str);
    }

    /**
     * 构建一个不带盐的加密 MD5 密码
     *
     * @param password
     * @return String
     */
    public static String builder(String password) {
        if (StringUtils.isEmpty(password)) {
            return "";
        }

        return DecriptUtil.md5(password);
    }

    /**
     * 未加密密码 与 加密密码比对
     *
     * @param password
     * @param lastPassword
     * @return
     */
    public static boolean check(String password, String lastPassword) {
        if (StringUtils.isEmpty(password)) {
            return false;
        }

        String hash = builder(password);

        if (StringUtils.isEmpty(hash)) {
            return false;
        }

        return hash.equals(lastPassword);
    }

    /**
     * 密码加密并检查是否相等
     *
     * @param password
     * @param salt
     * @param lastPassword
     * @return
     */
    public static boolean check(String password, String salt, String lastPassword) {
        String passwordHash = builder(password, salt);
        return passwordHash.equals(lastPassword);
    }
}
