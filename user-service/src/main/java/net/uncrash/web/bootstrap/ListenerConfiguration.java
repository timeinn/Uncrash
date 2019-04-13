package net.uncrash.web.bootstrap;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Asynchronous listener configuration
 *
 * @author Sendya
 */
@Configuration
@EnableAsync
public class ListenerConfiguration implements AsyncConfigurer {
}
