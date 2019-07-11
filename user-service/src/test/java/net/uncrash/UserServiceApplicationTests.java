package net.uncrash;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import net.uncrash.authorization.Permission;
import net.uncrash.authorization.basic.domain.Action;
import net.uncrash.authorization.basic.domain.DefaultPermission;
import net.uncrash.authorization.basic.domain.PermissionRole;
import net.uncrash.authorization.basic.service.PermissionRoleService;
import net.uncrash.authorization.basic.service.PermissionService;
import net.uncrash.core.utils.JSONUtil;
import net.uncrash.core.utils.id.IDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
public class UserServiceApplicationTests {

    @Resource
    private PermissionService permissionService;
    @Resource
    private PermissionRoleService permissionRoleService;

    @Test
    public void test() {

        String roleId = IDGenerator.UUID2.generate();
        String permissionId = IDGenerator.UUID2.generate();

        Action add = Action.builder().action("add").checked(true).name("新增").build();
        Action update = Action.builder().action("update").checked(true).name("更新").build();
        Action query = Action.builder().action("query").checked(true).name("查询").build();
        Action delete = Action.builder().action("delete").checked(false).name("删除").build();

        List<Action> actions = Lists.newArrayList(add, update, query, delete);

        PermissionRole pr = PermissionRole.builder()
            .permissionId(permissionId)
            .roleId(roleId)
            .actions(JSONUtil.toJSON(actions))
            .build();

        // permissionRoleService.saveAndFlush(pr);

        List<PermissionRole> permissionRoleList = permissionService.findAllByRoleId("be12fa74d78b4d728123d38eacc8a27f");
        log.info("PermissionRole: {}", JSONUtil.toJSON(permissionRoleList));

    }

    @Test
    public void testAddPermission() {
        String permissionId = IDGenerator.UUID2.generate();

        Set<Action> actionSet = Sets.newHashSet(Action.add, Action.update, Action.delete, Action.query);

        DefaultPermission permission = DefaultPermission.builder()
            .id(permissionId)
            .name("系统管理")
            .actions(null)
            .describe("")
            .parent(null)
            .component("PageView")
            .icon("system")
            .path("/system")
            .sort(0)
            .build();

        DefaultPermission permission1 = permissionService.saveAndFlush(permission);


    }
}
