package com.cy.store.mapper;

import com.cy.store.entity.Product;

import java.util.List;

public interface ProductMapper {

    /**
     * 查询热销商品的前四名
     * @return 前四名的list
     */
    List<Product> findHotList();

    /**
     * 通过商品id查找商品信息
     * @param id
     * @return
     */
    Product findById(Integer id);
}
