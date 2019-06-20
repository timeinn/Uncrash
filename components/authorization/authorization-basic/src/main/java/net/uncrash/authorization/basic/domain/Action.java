package net.uncrash.authorization.basic.domain;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
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
            e.printStackTrace();
            return Collections.emptySet();
        }
    }

    public static final Action add = Action.builder().action("add").checked(true).name("新增").build();
    public static final Action update = Action.builder().action("update").checked(true).name("更新").build();
    public static final Action query = Action.builder().action("query").checked(true).name("查询").build();
    public static final Action delete = Action.builder().action("delete").checked(false).name("删除").build();
}
