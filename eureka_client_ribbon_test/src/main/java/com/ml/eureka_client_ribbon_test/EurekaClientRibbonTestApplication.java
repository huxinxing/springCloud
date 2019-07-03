package com.ml.eureka_client_ribbon_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientRibbonTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientRibbonTestApplication.class, args);
    }

}
