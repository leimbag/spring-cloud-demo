package com.leimbag.demo.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class DataGatewayApplication {
    private static final Logger logger = LoggerFactory.getLogger(DataGatewayApplication.class);

    public static void main(String[] args) {
        new SpringApplicationBuilder(DataGatewayApplication.class).web(true).run(args);
        logger.error("Data Demo gateway start!");
    }
}
