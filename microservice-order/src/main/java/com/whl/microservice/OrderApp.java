package com.whl.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author: whling
 * @date: Created in 20:28 2017/11/5
 */
@EnableFeignClients
@EnableHystrix  //启动服务容错
@EnableDiscoveryClient  //申明这是Eureka的客户端
@SpringBootApplication
public class OrderApp {
    /**
     * 生成对象
     * @return
     */
    @LoadBalanced //负载均衡ribbon实现，默认轮询
    @Bean
    public RestTemplate newRestTemplate(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApp.class, args);
    }
}
