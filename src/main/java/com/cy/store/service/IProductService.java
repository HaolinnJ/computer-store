package com.cy.store.service;

import com.cy.store.entity.Product;

import java.util.List;

public interface IProductService {
    /**
     * 查询热销商品前四名
     * @return 前四名的集合
     */
    List<Product> findHotList();

    Product findById(Integer id);
}
