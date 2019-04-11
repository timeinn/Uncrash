package net.uncrash.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.uncrash.agent.domain.AgentData;
import net.uncrash.agent.domain.AgentLog;
import net.uncrash.agent.service.AgentLogService;
import net.uncrash.core.web.model.ResponseMessage;
import net.uncrash.exception.BadRequestException;
import net.uncrash.exception.NotFoundException;
import net.uncrash.logging.api.AccessLogger;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Uncrash agent log controller
 *
 * @author Acris
 * @date 2019/04/02
 */
@Api(tags = "Agent Log API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/agent/logs")
@AccessLogger("服务状态推送")
public class AgentLogController {

    private final AgentLogService agentLogService;

    /**
     * Save server agent log
     *
     * @param agentData encrypted agent log
     * @return saved agent log
     */
    @ApiOperation("保存服务端 Agent Log 信息")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage<AgentLog> agentStat(@RequestBody AgentData agentData) {
        return ResponseMessage.ok(agentLogService.save(agentData.builder()
            .orElseThrow(() -> new BadRequestException("Agent data could not be null"))
        ));
    }

    /**
     * Get agent log by id
     *
     * @param id the identify of agent log
     * @return agent log detail
     */
    @ApiOperation("根据 ID 查询详情")
    @GetMapping("/{id}")
    public ResponseMessage<AgentLog> info(@PathVariable("id") String id) {
        return ResponseMessage.ok(agentLogService.findOne(id)
            .orElseThrow(() -> new NotFoundException("Agent log not found")));
    }

    /**
     * Get agent log list
     *
     * @param pageNo   page number
     * @param pageSize page size
     * @param serverId user server id
     * @param cpuName  cpu name
     * @return agent log list
     */
    @ApiOperation("查询 Agent Log 列表")
    @GetMapping("/")
    public ResponseMessage<Page<AgentLog>> list(@RequestParam Integer pageNo,
                                                @RequestParam Integer pageSize,
                                                @RequestParam Long serverId,
                                                @RequestParam String cpuName) {
        return ResponseMessage.ok(agentLogService.findAll(Example.of(AgentLog.builder()
            .serverId(serverId)
            .cpuName(cpuName)
            .build(), ExampleMatcher.matching()
            .withMatcher("cpuName", ExampleMatcher.GenericPropertyMatchers.endsWith())
        ), PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "createTime"))));
    }
}
