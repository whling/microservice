package com.whl.microservice.service;

import com.whl.microservice.pojo.Item;
import com.whl.microservice.vo.OrderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author: whling
 * @date: Created in 20:25 2017/11/5
 */
@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderProperties orderProperties;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${microservice.item.address}")
    private String itemUrl;

    public Item queryItemById(Long id) {
        logger.info("id:{}", id);
        String serviceName = "microservice-item";
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (null == instances) {
            return null;
        }
        ServiceInstance serviceInstance = instances.get(0); //模拟获取第一个
        //String url = itemUrl + id;
        String url ="http://"+ serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/item/" + id;
        return restTemplate.getForObject(url, Item.class);
    }
    //public Item queryItemById(Long id) {
    //    logger.info("id:{}", id);
    //    //String url = itemUrl + id;  //使用硬编码方式
    //    String url = orderProperties.getItem().getAddress() + id;  //使用对象读取属性文件获取配置信息
    //    return restTemplate.getForObject(url, Item.class);
    //}
}
