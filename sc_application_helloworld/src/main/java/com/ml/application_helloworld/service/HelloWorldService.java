package com.ml.application_helloworld.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class HelloWorldService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "serviceFailure")
    public String sayHello() {
        Map msg = restTemplate.getForObject("http://service-hello-world/ml/helloWorld", Map.class);
        if (msg.get("SUCCESS").equals("true")) {
            return msg.get("data").toString();
        } else {
            return "请求失败";
        }
    }

    public String serviceFailure() {
        return "hello world service is not available !";
    }


}
