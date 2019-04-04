package net.uncrash.web.handler;

import lombok.extern.slf4j.Slf4j;
import net.uncrash.core.web.model.ResponseMessage;
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

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    ResponseEntity handleException(RuntimeException exception, HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        if (exception.getCause() != null) {
            log.error("{}:{}", exception.getMessage(), exception.getCause());
        }
        return ResponseEntity.ok().build();
    }
}
