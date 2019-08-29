package net.uncrash;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import net.uncrash.authorization.Permission;
import net.uncrash.authorization.basic.domain.*;
import net.uncrash.authorization.basic.jwt.JwtConfig;
import net.uncrash.authorization.basic.service.*;
import net.uncrash.core.exception.NotFoundException;
import net.uncrash.core.utils.JSONUtil;
import net.uncrash.core.utils.PasswordBuilder;
import net.uncrash.core.utils.id.IDGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
public class UserServiceApplicationTests {

    @Resource
    private PermissionService     permissionService;
    @Resource
    private PermissionRoleService permissionRoleService;
    @Resource
    private DefaultRoleService    roleService;
    @Resource
    private DefaultUserService    userService;
    @Resource
    private JwtConfig             jwtConfig;

    @Test
    public void testAddPermissionRole() {

        // String roleId = IDGenerator.UUID2.generate();
        // String permissionId = IDGenerator.UUID2.generate();
        String roleId = "2c9087a56cdb56e0016cdb56ff1d0000";
        String permissionId = "2c9087a56cdb683e016cdb686c2e0000";

        List<Action> actions = Lists.newArrayList(Action.add, Action.update, Action.query, Action.delete);

        PermissionRole pr = PermissionRole.builder()
            .permissionId(permissionId)
            .roleId(roleId)
            .actions(JSONUtil.toJSON(actions))
            .build();

        permissionRoleService.saveAndFlush(pr);

        List<PermissionRole> permissionRoleList = permissionService.findAllByRoleId("be12fa74d78b4d728123d38eacc8a27f");
        log.info("PermissionRole: {}", JSONUtil.toJSON(permissionRoleList));

    }

    @Test
    public void testAddRole() {
        String roleId = IDGenerator.UUID2.generate();

        DefaultRole role = DefaultRole.builder()
            .id(roleId)
            .name("管理员")
            .describe("就是管理员")
            .build();
        roleService.saveAndFlush(role);
    }

    @Test
    public void testAddPermission() {
        String permissionId = IDGenerator.UUID2.generate();

        Set<Action> actionSet = Sets.newHashSet(Action.add, Action.update, Action.delete, Action.query);

        DefaultPermission permission = DefaultPermission.builder()
            .id(permissionId)
            .name("权限管理")
            .actions(JSONUtil.toJSON(actionSet))
            .describe("")
            .parent(null)
            .component("Permission.vue")
            .icon(null)
            .path("/system/permission")
            .hideChildrenInMenu(false)
            .showInMenu(true)
            .sort(0)
            .build();

        DefaultPermission permission1 = permissionService.saveAndFlush(permission);


    }

    @Test
    public void testPassword() {

        String userId = "8a80cb816b549901016b54a1a5720000";
        String password = PasswordBuilder.builder("yladmxa", "TCnKpa8K");
        DefaultUser user = userService.findOne(userId)
            .orElseThrow(() -> new NotFoundException("账户或密码错误"));
        user.setPassword(password);
        userService.saveAndFlush(user);
        log.info("updated user: {}", user);
    }

    @Test
    public void testJWT() {
        log.info("Config: {}", jwtConfig);
        long ttl = jwtConfig.getTtl();
        long now = System.currentTimeMillis();
        Date nowDate = new Date();
        long exprieMillis = now + ttl;
        Date exprie = new Date(exprieMillis);

        SecretKey key = jwtConfig.generalKey();
        log.info("SecretKey: {}", key);

        JwtBuilder builder = Jwts.builder()
            .setId(jwtConfig.getId())
            .setIssuedAt(nowDate)
            .setSubject("abc")
            .signWith(key)
            .setExpiration(exprie);
        String jwtToken = builder.compact();
        log.info("jwt-token: {}", jwtToken);

        log.info("SecretKey: {}", jwtConfig.generalKey());
        Claims claims = Jwts.parser()
            .setSigningKey(jwtConfig.generalKey())
            .parseClaimsJws(jwtToken)
            .getBody();

        log.info("Claims: {}", claims);


    }
}
