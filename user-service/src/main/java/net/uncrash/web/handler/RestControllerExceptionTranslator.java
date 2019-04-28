package net.uncrash.web.handler;

import lombok.extern.slf4j.Slf4j;
import net.uncrash.authorization.exception.UnAuthorizedException;
import net.uncrash.core.web.model.ResponseMessage;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = {RestController.class, ResponseBody.class})
@Slf4j
@Order(1)
public class RestControllerExceptionTranslator {

    @ExceptionHandler(UnAuthorizedException.class)
    @ResponseBody
    public ResponseEntity handleBusinessException(UnAuthorizedException exception) {
        if (exception.getCause() != null) {
            log.error("{}:{}", exception.getMessage(), exception.getCause());
        }
        return ResponseEntity.status(exception.getState())
            .body(ResponseMessage.error(exception.getState(), exception.getMessage()).setCode("-1"));
    }
}
