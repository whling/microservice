package com.whl.microservice.feign;

import com.whl.microservice.pojo.Item;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author: whling
 * @date: Created in 12:56 2017/11/8
 */
@FeignClient(value = "microservice-item")
public interface ItemFeignService {

    @GetMapping("/item/{itemId}")
    Item queryItemById(@PathVariable("itemId") Long itemId);
}
