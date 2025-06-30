package com.cy.store.service;

import com.cy.store.entity.Order;

public interface IOrderService {
    /**
     *
     * @param aid 用于查询地址信息
     * @param cids 用于计算总价格
     * @param uid
     * @param username
     * @return
     */
    Order create(Integer aid, Integer[] cids, Integer uid, String username);


}
