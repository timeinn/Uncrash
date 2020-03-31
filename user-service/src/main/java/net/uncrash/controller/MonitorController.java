package net.uncrash.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.uncrash.authorization.AuthenticationUser;
import net.uncrash.authorization.Permission;
import net.uncrash.authorization.annotation.Authorize;
import net.uncrash.core.exception.BadRequestException;
import net.uncrash.core.utils.id.IDGenerator;
import net.uncrash.core.web.model.ResponseMessage;
import net.uncrash.logging.api.AccessLogger;
import net.uncrash.server.consts.MonitorType;
import net.uncrash.server.domain.UserMonitor;
import net.uncrash.server.service.UserMonitorService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * User monitor controller
 *
 * @author Sendya
 */
@Api(tags = "UserMonitor Service")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user/servers", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@AccessLogger("监控模块")
@Authorize(permission = "servers")
public class MonitorController {

    private final UserMonitorService monitorService;

    @ApiOperation("获取监控信息")
    @GetMapping("/{id}")
    public ResponseMessage<UserMonitor> info(@PathVariable("id") String id) {
        return ResponseMessage.ok(monitorService.findOne(id)
            .orElseThrow(() -> new RuntimeException("Monitor not found")));
    }

    @ApiOperation("获取用户监控列表")
    @GetMapping("/")
    @Authorize
    public ResponseMessage<Page<UserMonitor>> list(AuthenticationUser user,
                                                   @RequestParam Integer pageNo,
                                                   @RequestParam Integer pageSize) {
        // 假设用户已经登录，并注入了用户对象到 controller

        // 假定是管理员
        boolean requiredUser = false;
        UserMonitor monitor = UserMonitor.builder().build();
        if (requiredUser) {
            monitor.setUserId(user.getUser().getId());
        }

        return ResponseMessage.ok(monitorService.findAll(Example.of(monitor), PageRequest.of(
            pageNo,
            pageSize,
            Sort.by(Sort.Direction.DESC, "createdTime")
        )));

    }

    @ApiOperation("增加一个监控")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Authorize(action = Permission.ACTION_ADD)
    public ResponseMessage<UserMonitor> add(AuthenticationUser user, @RequestBody UserMonitor monitor) {
        monitor.setId(IDGenerator.UUID_NO_SEPARATOR.generate());
        monitor.setUserId(user.getUser().getId());

        switch (MonitorType.fromValue(monitor.getType())) {
            case ICMP:

                break;
            case CURL:

                break;
            case PORT:
                if (monitor.getPort() == null || monitor.getPort() == 0) {
                    throw new BadRequestException("参数错误，端口不存在");
                }
                break;
            case AGENT:
            default:
                // 监控 3 分钟内丢失次数后通知
                if (monitor.getLossNotification() > 0) {
                    // 检查是否开启 附加 PING 监测
                }
                monitor.setPushToken(IDGenerator.PUSH_TOKEN.generate());
                break;
        }
        UserMonitor monitor1 = monitorService.saveAndFlush(monitor);
        return ResponseMessage.ok();
    }

    @ApiOperation("修改一个监控")
    @PutMapping("/{id}")
    public ResponseMessage<UserMonitor> update(@PathVariable("id") String id, @RequestBody UserMonitor monitor) {
        monitor.setId(id);
        return ResponseMessage.ok(monitorService.saveAndFlush(monitor));
    }

    @ApiOperation("删除一个监控")
    @DeleteMapping("/{id}")
    public ResponseMessage<Boolean> remove(@PathVariable("id") String id, AuthenticationUser user) {
        // 1. 判断是不是用户自己的监控 / 或者是管理员，直接跳过权限
        // "admin".equals(user.getRole().getId())

        // 2. 处理数据
        monitorService.deleteById(id);
        return ResponseMessage.ok();
    }
}