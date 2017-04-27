package com.leimbag.data.demo.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients({
        "com.leimbag.data.demo.service.client"
})
public class DataDemoApiApplication implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataDemoApiApplication.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(DataDemoApiApplication.class).web(true).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.error("Data Demo Api start!");
        Thread.currentThread().join();
    }
}
