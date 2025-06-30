package com.cy.store.mapper;

import com.cy.store.entity.Cart;
import com.cy.store.vo.CartVO;

import java.util.Date;
import java.util.List;

public interface CartMapper {
    /**
     * 插入购物车数据
     * @param cart 购物车数据
     * @return 受影响的行数
     */
    Integer insert(Cart cart);

    /**
     * 修改购物车数据中商品的数量
     * @param cid 购物车数据的id
     * @param num 新的数量
     * @param modifiedUser 修改执行人
     * @param modifiedTime 修改时间
     * @return 受影响的行数
     */
    Integer updateNumByCid(
            Integer cid,
            Integer num,
            String modifiedUser,
            Date modifiedTime);

    /**
     * 根据用户id和商品id查询购物车中的数据
     * @param uid 用户id
     * @param pid 商品id
     * @return 匹配的购物车数据，如果无则返回null
     */
    Cart findByUidAndPid(
            Integer uid,
            Integer pid);

    /**
     * 查询某用户的购物车数据
     * @param uid
     * @return 购物车列表
     */
    List<CartVO> findVOByUid(Integer uid);

    /**
     * 用cid查找购物车数据
     * @param cid
     * @return
     */
    Cart findByCid(Integer cid);

    /**
     * 通过cid删除数据
     * @param cid
     * @return
     */
    Integer deleteByCid(Integer cid);

    /**
     * 通过查询cid来返回选中的购物车中的商品
     * @param cids
     * @return
     */
    List<CartVO> findVOByCids (Integer[] cids);
}
