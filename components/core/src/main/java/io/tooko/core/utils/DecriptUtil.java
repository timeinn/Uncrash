package io.tooko.core.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 加密工具类
 * 目前支持：
 * 1. SHA1
 * 2. MD5
 * 3. AES en/de
 * 4. HMAC_SHA1
 */
public class DecriptUtil {

    private static final String HMAC_SHA1 = "HmacSHA1";

    public static String sha1(String decrypt) {
        try {
            MessageDigest digest = MessageDigest
                .getInstance("SHA-1");
            digest.update(decrypt.getBytes());
            byte[] messageDigest = digest.digest();
            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制 数
            for (byte aMessageDigest : messageDigest) {
                String shaHex = Integer.toHexString(aMessageDigest & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String md5(String input) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(input.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuilder hexString = new StringBuilder();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < md.length; i++) {
                String shaHex = Integer.toHexString(md[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static final class AES {

        static final String CIPHER_INSTANCE = "AES/CBC/PKCS5Padding";

        /**
         * 加密
         *
         * @param content  需要加密的内容
         * @param password 加密密码
         * @return byte[]
         */
        public static byte[] encrypt(String content, String password) {
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(128, new SecureRandom(password.getBytes()));
                SecretKey secretKey = keyGenerator.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
                // 创建密码器
                Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
                byte[] byteContent = content.getBytes(Charset.forName("UTF-8"));
                // 初始化
                cipher.init(Cipher.ENCRYPT_MODE, key);
                // 加密
                return cipher.doFinal(byteContent);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
                e.printStackTrace();
            }
            return new byte[]{};
        }

        /**
         * 加密并转换为 Base64 编码
         *
         * @param content  待加密内容
         * @param password 加密密钥
         * @return String
         */
        public static String encryptBase64(String content, String password) {
            return Base64.getEncoder().encodeToString(encrypt(content, password));
        }

        /**
         * 解密
         *
         * @param content  待解密内容
         * @param password 解密密钥
         * @return byte[]
         */
        public static byte[] decrypt(byte[] content, String password) {
            try {
                KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(128, new SecureRandom(password.getBytes()));
                SecretKey secretKey = keyGenerator.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
                // 创建密码器
                Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
                // 初始化
                cipher.init(Cipher.DECRYPT_MODE, key);
                // 加密
                return cipher.doFinal(content);
            } catch (NoSuchAlgorithmException | NoSuchPaddingException
                | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
                e.printStackTrace();
            }
            return new byte[]{};
        }

        /**
         * 解密
         *
         * @param content  待解密内容
         * @param password 解密密钥
         * @return String
         */
        public static String decryptBase64(String content, String password) {
            return new String(Base64.getDecoder().decode(decrypt(content.getBytes(), password)));
        }

    }

    /**
     * 生成签名数据
     *
     * @param data 待加密的数据
     * @param key  加密使用的key
     * @return 生成 BASE64 编码的字符串
     */
    public static String getSignature(byte[] data, byte[] key) {
        String encode = null;
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, HMAC_SHA1);
            Mac mac = Mac.getInstance(HMAC_SHA1);
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data);

            encode = Base64.getEncoder().encodeToString(rawHmac);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encode;
    }

}
