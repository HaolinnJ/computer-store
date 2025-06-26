package com.cy.store.service.impl;

import com.cy.store.entity.Product;
import com.cy.store.mapper.ProductMapper;
import com.cy.store.service.IProductService;
import com.cy.store.service.ex.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> list =  productMapper.findHotList();
        for (Product product:list){
            product.setPriority(null);
            product.setCreatedUser(null);
            product.setCreatedTime(null);
            product.setModifiedUser(null);
            product.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public Product findById(Integer id) {
        Product result = productMapper.findById(id);
        if(result==null){
            throw new ProductNotFoundException("Product does not exist.");
        }
        result.setPriority(null);
        result.setCreatedUser(null);
        result.setCreatedTime(null);
        result.setModifiedUser(null);
        result.setModifiedTime(null);

        return result;
    }
}
