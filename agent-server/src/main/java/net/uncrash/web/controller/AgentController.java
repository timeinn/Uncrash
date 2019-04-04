package net.uncrash.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import net.uncrash.agent.domain.ServerAgentLog;
import net.uncrash.agent.service.ServerAgentLogService;
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
@Api(tags = "Server Agent")
@RequiredArgsConstructor
@RestController
@RequestMapping("/agent")
public class AgentController {
    private final ServerAgentLogService serverAgentLogService;

    /**
     * Save server agent status
     *
     * @param serverAgentLog Server Agent Log
     * @return Saved Server Agent Log
     */
    @ApiOperation("保存状态信息")
    @PostMapping("/stat")
    @ResponseStatus(HttpStatus.CREATED)
    public ServerAgentLog agentStat(ServerAgentLog serverAgentLog) {
        return serverAgentLogService.save(serverAgentLog);
    }
}
