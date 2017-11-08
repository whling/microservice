package com.whl.microservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.whl.microservice.feign.ItemFeignService;
import com.whl.microservice.pojo.Item;
import com.whl.microservice.vo.OrderProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private ItemFeignService itemFeignService;

    @Value("${microservice.item.address}")
    private String itemUrl;

    @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") //进行容错处理
    public Item queryItemById(Long id) {
        logger.info("id:{}", id);
        return itemFeignService.queryItemById(id);
    }

    //@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") //进行容错处理
    //public Item queryItemById(Long id) {
    //    logger.info("id:{}", id);
    //    String serviceName = "microservice-item";
    //    String url = "http://" + serviceName + "/item/" + id; //通过serviceName（面向服务），生成代理获取服务地址，客户端负载均衡
    //    return restTemplate.getForObject(url, Item.class);
    //}

    /**此方法与被容错方法参数及返回值要相同**/
    public Item queryItemByIdFallbackMethod(Long id){
        return new Item(id, "查询商品信息出错!", null, null, null);
    }

    //public Item queryItemById(Long id) {
    //    logger.info("id:{}", id);
    //    String serviceName = "microservice-item";
    //    List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
    //    if (null == instances) {
    //        return null;
    //    }
    //    ServiceInstance serviceInstance = instances.get(0); //模拟获取第一个
    //    //String url = itemUrl + id;
    //    String url ="http://"+ serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/item/" + id;
    //    return restTemplate.getForObject(url, Item.class);
    //}

    //public Item queryItemById(Long id) {
    //    logger.info("id:{}", id);
    //    //String url = itemUrl + id;  //使用硬编码方式
    //    String url = orderProperties.getItem().getAddress() + id;  //使用对象读取属性文件获取配置信息
    //    return restTemplate.getForObject(url, Item.class);
    //}
}
