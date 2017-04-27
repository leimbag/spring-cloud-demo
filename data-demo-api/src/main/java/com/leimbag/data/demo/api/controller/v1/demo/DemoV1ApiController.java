package com.leimbag.data.demo.api.controller.v1.demo;

import com.leimbag.data.demo.service.client.DemoServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/demo")
public class DemoV1ApiController {
    @Autowired
    private DemoServiceClient demoServiceClient;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam("name") String name) {
        return demoServiceClient.hello(name);
    }
}
