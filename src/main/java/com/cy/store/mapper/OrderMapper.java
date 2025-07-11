package com.cy.store.mapper;

import com.cy.store.entity.Order;
import com.cy.store.entity.OrderItem;

public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order
     * @return
     */
    Integer insertOrder(Order order);

    /**
     * 插入某一个订单中商品数据
     * @param orderItem
     * @return
     */
    Integer insertOrderItem(OrderItem orderItem);
}
