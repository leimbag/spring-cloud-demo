package com.leimbag.data.demo.service.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DataDemoServiceApplication {
    private static Logger logger = LoggerFactory.getLogger(DataDemoServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DataDemoServiceApplication.class, args);
        logger.error("Data Demo Service start!");
    }
}
