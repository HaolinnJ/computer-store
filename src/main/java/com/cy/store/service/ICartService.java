package com.cy.store.service;


import java.util.List;

/** 收货地址模块业务层接口*/
public interface ICartService {
    /**
     * 将商品添加到购物车
     * @param uid
     * @param pid
     * @param amount
     * @param username
     */
    void addToCart(Integer uid,Integer pid, Integer amount, String username);
}
