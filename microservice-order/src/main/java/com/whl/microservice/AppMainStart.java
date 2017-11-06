package com.whl.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * @author: whling
 * @date: Created in 20:28 2017/11/5
 */
@EnableDiscoveryClient  //申明这是Eureka的客户端
@SpringBootApplication
public class AppMainStart {
    /**
     * 生成对象
     * @return
     */
    @Bean
    public RestTemplate newRestTemplate(){
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }

    public static void main(String[] args) {
        SpringApplication.run(AppMainStart.class, args);
    }
}
