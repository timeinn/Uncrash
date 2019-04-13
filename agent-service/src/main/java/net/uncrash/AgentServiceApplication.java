package net.uncrash;

import net.uncrash.logging.aop.EnableAccessLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Agent Service for Uncrash
 *
 * @author Sendya
 * @author Acris
 * @date 2019/04/02
 */
@EnableAccessLogger
@SpringBootApplication
public class AgentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentServiceApplication.class, args);
    }

}
