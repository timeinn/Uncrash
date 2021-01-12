package net.uncrash.core.conf;

import net.uncrash.core.utils.id.IDWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IDWorkerConfiguration {

    @Bean
    public IDWorker idWorker() {
        return new IDWorker(1, 1, 1);
    }
}
