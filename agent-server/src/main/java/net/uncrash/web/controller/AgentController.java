package net.uncrash.web.controller;

import net.uncrash.agent.service.ServerAgentLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Autowired
    private ServerAgentLogService serverAgentLogService;

}
