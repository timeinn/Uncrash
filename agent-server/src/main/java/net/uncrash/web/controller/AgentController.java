package net.uncrash.web.controller;

import net.uncrash.agent.domain.ServerAgentLog;
import net.uncrash.agent.service.ServerAgentLogService;
import net.uncrash.core.web.model.ResponseMessage;
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
@RestController
@RequestMapping("/agent")
public class AgentController {
    private final ServerAgentLogService serverAgentLogService;

    public AgentController(ServerAgentLogService serverAgentLogService) {
        this.serverAgentLogService = serverAgentLogService;
    }

    @PostMapping("/stat")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseMessage<ServerAgentLog> agentStat(ServerAgentLog serverAgentLog) {
        ServerAgentLog saveResult = serverAgentLogService.save(serverAgentLog);
        return ResponseMessage.ok(saveResult);
    }
}
