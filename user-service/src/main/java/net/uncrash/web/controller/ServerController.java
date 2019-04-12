package net.uncrash.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.uncrash.core.web.model.ResponseMessage;
import net.uncrash.logging.api.AccessLogger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/server")
@AccessLogger("服务器模块")
@RequiredArgsConstructor
public class ServerController {

    public ResponseMessage<>
}
