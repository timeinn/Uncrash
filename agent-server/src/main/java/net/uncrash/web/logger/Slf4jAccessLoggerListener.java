package net.uncrash.web.logger;

import net.uncrash.core.utils.JSONUtil;
import net.uncrash.logging.api.AccessLoggerInfo;
import net.uncrash.logging.api.events.AccessLoggerAfterEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.stream.Stream;

@Component
public class Slf4jAccessLoggerListener {

    private Logger logger = LoggerFactory.getLogger("access-logger");

    /**
     * 不进行 logger 监听的类
     */
    private static final Class[] EXCLUDES = {
        ServletRequest.class,
        ServletResponse.class,
        InputStream.class,
        OutputStream.class,
        MultipartFile.class
    };

    @EventListener
    public void onLogger(AccessLoggerAfterEvent event) {
        AccessLoggerInfo info = event.getLogger();

        if (logger.isInfoEnabled()) {
            try {
                logger.info(JSONUtil.toJSON(info.toSimpleMap(obj -> {
                    if (Stream.of(EXCLUDES).anyMatch(aClass -> aClass.isInstance(obj))) {
                        return obj.getClass().getName();
                    }
                    return JSONUtil.toJSON(obj);
                })));
            } catch (Exception e) {
                logger.error("onLogger Error: {}", e.getMessage());
            }
        }
    }
}
