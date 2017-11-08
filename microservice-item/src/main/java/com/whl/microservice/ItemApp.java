package com.whl.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author: whling
 * @date: Created in 19:35 2017/11/5
 */
@EnableDiscoveryClient   //申明这是一个Eureka的客户端
@SpringBootApplication
public class ItemApp {

    public static void main(String[] args) {
        SpringApplication.run(ItemApp.class, args);
    }
}
