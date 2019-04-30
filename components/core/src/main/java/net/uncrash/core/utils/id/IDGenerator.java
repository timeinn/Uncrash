package net.uncrash.core.utils.id;

import net.uncrash.core.utils.IDUtil;
import net.uncrash.core.utils.RandomUtil;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public interface IDGenerator<T> {

    T generate();

    IDGenerator<String> ID = IDUtil::generate;

    IDGenerator<String> UUID = () -> java.util.UUID.randomUUID().toString();

    IDGenerator<String> UUID_NO_SEPARATOR = () -> UUID.generate().replaceAll("-", "");

    IDGenerator<String> RANDOM = RandomUtil::randomChar;

    IDGenerator<String> PUSH_TOKEN = () -> Base64.getEncoder().encodeToString(IDGenerator.MD5.generate().getBytes());

    IDGenerator<String> MD5 = () -> {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(UUID.generate().concat(RandomUtil.randomChar()).getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    };
}
