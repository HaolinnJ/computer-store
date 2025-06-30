package com.cy.store.service;


import com.cy.store.vo.CartVO;

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

    /**
     * 查询某用户的购物车数据
     * @param uid
     * @return 购物车数据列表
     */
    List<CartVO> getVOByUid(Integer uid);

    /**
     * 增加用户购物车中某商品的数量
     * @param cid
     * @param uid
     * @param username
     * @return
     */
    Integer addNum(Integer cid, Integer uid, String username);

    /**
     * 减少用户购物车中某商品数量
     * @param cid
     * @param uid
     * @param username
     * @return
     */
    Integer reduceNum(Integer cid, Integer uid, String username);

    /**
     * 删除购物车中指定cid的商品
     * @param cid
     */
    void delCartItem(Integer cid, Integer uid);

    /**
     * 通过cids的数组查找购物车中符合的商品信息
     * @param cids
     * @param uid
     * @return
     */
    List<CartVO> getVOByCids(Integer[] cids, Integer uid);
}
