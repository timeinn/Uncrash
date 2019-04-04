package net.uncrash.web.handler;

import lombok.extern.slf4j.Slf4j;
import net.uncrash.core.web.model.ResponseMessage;
import net.uncrash.exception.BusinessException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * RestController exception handler
 * @author Sendya
 */
@ControllerAdvice(annotations = {RestController.class, ResponseBody.class})
@Slf4j
@Order(1)
public class RestControllerExceptionTranslator {

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    ResponseEntity handleBusinessException(BusinessException exception) {
        if (exception.getCause() != null) {
            log.error("{}:{}", exception.getMessage(), exception.getCause());
        }
        return ResponseEntity.status(exception.getStatus())
            .body(ResponseMessage.error(exception.getStatus(), exception.getMessage()));
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    ResponseEntity handleException(Exception exception) {
        if (exception.getCause() != null) {
            log.error("{}:{}", exception.getMessage(), exception.getCause());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ResponseMessage.error(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage()));
    }
}
