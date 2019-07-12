package net.uncrash.web.handler;

import lombok.extern.slf4j.Slf4j;
import net.uncrash.core.exception.BusinessException;
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

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ResponseEntity handleBusinessException(BusinessException exception) {
        if (exception.getCause() != null) {
            log.error("{}:{}", exception.getMessage(), exception.getCause());
        }
        return ResponseEntity.status(exception.getStatus())
            .body(ResponseMessage.error(exception.getStatus(), exception.getMessage()).setCode("-1"));
    }
}
