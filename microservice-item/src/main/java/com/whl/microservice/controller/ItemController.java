package com.whl.microservice.controller;

import com.whl.microservice.pojo.Item;
import com.whl.microservice.pojo.JdbcConfigBean;
import com.whl.microservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: whling
 * @date: Created in 19:33 2017/11/5
 */
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private JdbcConfigBean jdbcConfigBean;

    @GetMapping("/item/{itemId}")
    public Item queryItemById(@PathVariable("itemId") Long itemId){
        return itemService.queryItemById(itemId);
    }

    @GetMapping(value = "test")
    public String test(){
        return this.jdbcConfigBean.toString();
    }

}
