package net.uncrash.authorization.basic.jwt.entity;

import lombok.Data;
import net.uncrash.core.utils.JSONUtil;

import java.util.Base64;

/**
 * @author Sendya
 */
@Data
public class JWTHeader {

    /**
     * JWT header type
     */
    private String typ;

    /**
     * JWT header signature algorithm
     */
    private String alg;

    public String toBase64() {
        String json = JSONUtil.toJSON(this);
        return Base64.getEncoder().encodeToString(json.getBytes());
    }
}
