package net.uncrash.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.uncrash.agent.domain.AgentData;
import net.uncrash.agent.domain.ServerAgentLog;
import net.uncrash.agent.service.ServerAgentLogService;
import net.uncrash.core.web.model.ResponseMessage;
import net.uncrash.exception.BadRequestException;
import net.uncrash.exception.NotFoundException;
import net.uncrash.logging.api.AccessLogger;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

/**
 * Uncrash agent(agent-shell or agent-go) controller
 *
 * @author Acris
 * @date 2019/04/02
 */
@Api(tags = "Agent API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/agent")
@AccessLogger("服务状态推送")
public class AgentController {

    private final ServerAgentLogService serverAgentLogService;

    /**
     * Save server agent status
     *
     * @param agentData Server Agent Data
     * @return Saved Server Agent Log
     */
    @ApiOperation("保存服务端 Agent 状态信息")
    @PostMapping("/stat")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage<ServerAgentLog> agentStat(AgentData agentData) {
        return ResponseMessage.ok(serverAgentLogService.save(agentData.builder()
            .orElseThrow(() -> new BadRequestException("Agent data could not be null"))
        ));
    }

    /**
     * Get server agent info
     *
     * @param id String
     * @return ServerAgentLog
     */
    @ApiOperation("根据 ID 查询详情")
    @GetMapping("/{id}")
    public ResponseMessage<ServerAgentLog> info(@PathVariable("id") String id) {
        return ResponseMessage.ok(serverAgentLogService.findOne(id)
            .orElseThrow(() -> new NotFoundException("Agent data not found")));
    }

    @ApiOperation("查询列表")
    @GetMapping("/")
    public ResponseMessage<Page<ServerAgentLog>> list(@NonNull Integer pageNo,
                                                      @NonNull Integer pageSize,
                                                      Long serverId, String cpuName) {
        return ResponseMessage.ok(serverAgentLogService.findAll(Example.of(ServerAgentLog.builder()
            .serverId(serverId)
            .cpuName(cpuName)
            .build(), ExampleMatcher.matching()
            .withMatcher("cpuName", ExampleMatcher.GenericPropertyMatchers.contains())
        ), PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "createTime"))));
    }
}
