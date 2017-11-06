package com.whl.microservice.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: whling
 * @date: Created in 20:54 2017/11/5
 */
@Component
@ConfigurationProperties(prefix = "microservice")
public class OrderProperties {

    private ItemProperties item = new ItemProperties();

    public ItemProperties getItem() {
        return item;
    }

    public void setItem(ItemProperties item) {
        this.item = item;
    }
}
