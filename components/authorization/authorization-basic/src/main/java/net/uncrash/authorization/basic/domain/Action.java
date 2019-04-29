package net.uncrash.authorization.basic.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.Data;
import net.uncrash.core.utils.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

/**
 * 操作权限
 *
 * @author Sendya
 */
@Builder
@Data
public class Action {

    private String action;

    private String name;

    private Boolean checked;

    protected static Set<Action> json2Actions(String json) {
        if (StringUtils.isEmpty(json)) {
            return Collections.emptySet();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, new TypeReference<Set<Action>>() { });
        } catch (IOException e) {
            return Collections.emptySet();
        }
    }
}
