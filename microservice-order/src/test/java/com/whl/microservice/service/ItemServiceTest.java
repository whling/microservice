package com.whl.microservice.service;

import com.whl.microservice.OrderApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: whling
 * @date: Created in 22:57 2017/11/6
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest
@Import(value = {OrderApp.class})
public class ItemServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceTest.class);

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Test
    public void queryItemById() throws Exception {
        String serviceName = "microservice-item";
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceName);
        for (int i = 0; i < 100; i++) {
            logger.info("ip:{}:{}", serviceInstance.getHost(), serviceInstance.getPort());
        }
    }

}