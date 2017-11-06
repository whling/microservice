package com.whl.microservice.service;


import com.whl.microservice.pojo.Item;
import com.whl.microservice.pojo.Order;
import com.whl.microservice.pojo.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: whling
 * @date: Created in 19:31 2017/11/5
 */
@Service
public class OrderService {
    private static final Map<String, Order> MAP = new HashMap<String, Order>();

    static {
        // 构造测试数据
        Order order = new Order();
        order.setOrderId("59193738268961441");
        order.setCreateDate(new Date());
        order.setUpdateDate(order.getCreateDate());
        order.setUserId(1L);
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

        Item item = new Item();// 此处并没有商品的数据，需要调用商品微服务获取
        item.setId(1L);
        orderDetails.add(new OrderDetail(order.getOrderId(), item));

        item = new Item(); // 构造第二个商品数据
        item.setId(2L);
        orderDetails.add(new OrderDetail(order.getOrderId(), item));

        order.setOrderDetails(orderDetails);

        MAP.put(order.getOrderId(), order);
    }

    @Autowired
    private ItemService itemService;


    public Order queryOrderById(String orderId) {
        Order order = MAP.get(orderId);
        if (null == order) {
            return null;
        }
        order.getOrderDetails().stream()
                .forEach(e -> {
                    Long id = e.getItem().getId();
                    Item item = itemService.queryItemById(id);
                    e.setItem(item);
                });
        return order;
    }

}
