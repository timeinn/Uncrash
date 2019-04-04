package net.uncrash.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.uncrash.agent.domain.AgentData;
import net.uncrash.agent.domain.ServerAgentLog;
import net.uncrash.agent.service.ServerAgentLogService;
import net.uncrash.core.web.model.ResponseMessage;
import net.uncrash.exception.BadRequestException;
import net.uncrash.logging.api.AccessLogger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    @ApiOperation("保存服务端Agent状态信息")
    @PostMapping("/stat")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage<ServerAgentLog> agentStat(AgentData agentData) {
        ServerAgentLog serverAgentLog = agentData.builder().orElseThrow(() -> new BadRequestException("Agent data could not be null"));
        return ResponseMessage.ok(serverAgentLogService.save(serverAgentLog));
    }
}
