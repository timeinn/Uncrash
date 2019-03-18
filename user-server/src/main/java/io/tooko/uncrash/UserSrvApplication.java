package io.tooko.uncrash;

import io.tooko.uncrash.logging.aop.EnableAccessLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableAccessLogger
@EnableDiscoveryClient
public class UserSrvApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserSrvApplication.class, args);
    }
}
