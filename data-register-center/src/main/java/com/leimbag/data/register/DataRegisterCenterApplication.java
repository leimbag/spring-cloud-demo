package com.leimbag.data.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DataRegisterCenterApplication {
    private static final Logger logger = LoggerFactory.getLogger(DataRegisterCenterApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DataRegisterCenterApplication.class, args);
        logger.error("Register Center start!");
    }
}
