package net.uncrash.web.controller;

import lombok.RequiredArgsConstructor;
import net.uncrash.logging.api.AccessLogger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User servers controller
 *
 * @author Sendya
 */
@AccessLogger("服务器模块")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/servers")
public class ServerController {

}
